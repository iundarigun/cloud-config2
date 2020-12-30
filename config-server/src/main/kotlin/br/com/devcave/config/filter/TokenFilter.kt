package br.com.devcave.config.filter

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class TokenFilter(
    @Value("\${security.token-api}")
    private val tokenApi: String
) : OncePerRequestFilter() {

    private val notFilterApplication = listOf("actuator")

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val receivedToken = request.getHeader("x-config-token") ?: ""

        if (receivedToken == tokenApi) {
            filterChain.doFilter(request, response)
        } else {
            response.sendError(
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.reasonPhrase
            )
        }
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val application = request.servletPath.split("/")[1]
        return notFilterApplication.contains(application)
    }
}