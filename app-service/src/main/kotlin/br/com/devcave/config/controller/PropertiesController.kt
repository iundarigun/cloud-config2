package br.com.devcave.config.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("properties")
class PropertiesController(
    @Value("\${properties.property-two}")
    private val property: String
) {
    @GetMapping
    fun get(): String {
        return property
    }
}