package com.backend.dieta_anabolica.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "alimentos")
data class Alimento(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:NotBlank(message = "Nome não pode ser vazio")
    val nome: String,

    val calorias: Double
)
