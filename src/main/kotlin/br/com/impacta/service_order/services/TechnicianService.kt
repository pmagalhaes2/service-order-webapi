package br.com.impacta.service_order.services

import br.com.impacta.service_order.dtos.TechnicianResponse
import br.com.impacta.service_order.exceptions.NotFoundException
import br.com.impacta.service_order.mapper.TechnicianResponseMapper
import br.com.impacta.service_order.repositories.TechnicianRepository
import org.springframework.stereotype.Service

@Service
class TechnicianService(
    private val technicianRepository: TechnicianRepository,
    private val technicianResponseMapper: TechnicianResponseMapper
) {

    fun findById(id: Int): TechnicianResponse {
        return technicianRepository.findByid(id)?.let {
            technicianResponseMapper.map(it)
        }
            ?: throw NotFoundException("Técnico não encontrado! Tente listar todos os técnicos registrados para obter o ID específico.")
    }

    fun findAll(): List<TechnicianResponse> {
        return technicianRepository.findAll().map { t ->
            technicianResponseMapper.map(t)
        }
    }
}

