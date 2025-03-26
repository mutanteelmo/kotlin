package com.backend.dieta_anabolica.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }  // Desativa proteção CSRF (para testes)
            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/**").permitAll() // Permite acesso total
            }
            .httpBasic { it.disable() } // Remove autenticação básica
            .formLogin { it.disable() } // Remove tela de login

        return http.build()
    }
}
