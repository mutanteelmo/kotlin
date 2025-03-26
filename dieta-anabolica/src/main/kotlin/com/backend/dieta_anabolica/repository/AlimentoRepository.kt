package com.backend.dieta_anabolica.repository

import com.backend.dieta_anabolica.model.Alimento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AlimentoRepository : JpaRepository<Alimento, Long>
