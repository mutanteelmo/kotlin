package com.backend.dieta_anabolica.controller

import com.backend.dieta_anabolica.model.Alimento
import com.backend.dieta_anabolica.service.AlimentoService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("auth")
class AuthController (private val restTemplate: RestTemplate) {

    @Value("\${spring.security.oauth2.client.provider.keycloak.issuer-uri}")
    private lateinit var kecloakIssuerUri: String

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<Any> {
        val url = "$kecloakIssuerUri/protocol/openid-connect/token"

        val params = LinkedMultiValueMap<String, String>()
        params.add("client_id", "\${spring.security.oauth2.client.registration.keycloak.client-id}")
        params.add("grant_type", "\${spring.security.oauth2.client.registration.keycloak.authorization-grant-type}")
        params.add("teste", request.username)
        params.add("123", request.password)

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED

        val entity = HttpEntity(params, headers)

        return try {
            val response = restTemplate.postForEntity(url, entity, Map::class.java)
            ResponseEntity.ok(response.body)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mapOf("error" to "Invalid credentials"))
        }
    }

}

data class LoginRequest(val username: String, val password: String)