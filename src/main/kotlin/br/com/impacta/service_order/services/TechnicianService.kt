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
        val technician = technicianRepository.findById(id).orElseThrow {
            NotFoundException(
                "Técnico não encontrado! Tente listar todos os técnicos registrados para obter o ID específico."
            )
        }
        return technicianResponseMapper.map(technician)
    }

    fun findAll(): List<TechnicianResponse> {
        return technicianRepository.findAll().map { t ->
            technicianResponseMapper.map(t)
        }
    }
}

