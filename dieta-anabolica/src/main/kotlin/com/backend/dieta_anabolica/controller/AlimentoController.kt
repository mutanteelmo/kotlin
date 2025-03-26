package com.backend.dieta_anabolica.controller

import com.backend.dieta_anabolica.model.Alimento
import com.backend.dieta_anabolica.service.AlimentoService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/alimentos")
class AlimentoController(private val service: AlimentoService) {

    @GetMapping
    fun findAll(): ResponseEntity<List<Alimento>> = ResponseEntity.ok(service.findAll())

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Alimento> =
        ResponseEntity.ok(service.findById(id))

    @PostMapping
    fun save(@Valid @RequestBody alimento: Alimento): ResponseEntity<Alimento> =
        ResponseEntity.ok(service.save(alimento))

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody alimento: Alimento): ResponseEntity<Alimento> =
        ResponseEntity.ok(service.update(id, alimento))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
}
