package com.backend.dieta_anabolica.service

import com.backend.dieta_anabolica.model.Alimento
import com.backend.dieta_anabolica.repository.AlimentoRepository
import org.springframework.stereotype.Service

@Service
class AlimentoService(private val repository: AlimentoRepository) {

    fun findAll(): List<Alimento> = repository.findAll()

    fun findById(id: Long): Alimento =
        repository.findById(id).orElseThrow { RuntimeException("Food not found") }

    fun save(food: Alimento): Alimento = repository.save(food)

    fun update(id: Long, newFood: Alimento): Alimento {
        val existingFood = findById(id)
        val updatedFood = existingFood.copy(
            nome = newFood.nome,
            calorias = newFood.calorias
        )
        return repository.save(updatedFood)
    }

    fun delete(id: Long) {
        val food = findById(id)
        repository.delete(food)
    }
}
