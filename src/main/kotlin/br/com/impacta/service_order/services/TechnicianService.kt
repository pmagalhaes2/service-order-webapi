package br.com.impacta.service_order.services

import br.com.impacta.service_order.domain.Technician
import br.com.impacta.service_order.dtos.TechnicianResponse
import br.com.impacta.service_order.exceptions.NotFoundException
import br.com.impacta.service_order.mapper.TechnicianResponseMapper
import br.com.impacta.service_order.repositories.TechnicianRepository
import br.com.impacta.service_order.requests.TechnicianCreationRequest
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

    fun create(technicianRequest: TechnicianCreationRequest): TechnicianResponse {
        return technicianRepository.findByCpf(technicianRequest.cpf)?.let {
            throw Exception("CPF já cadastrado na base de dados!")
        } ?: technicianRepository.save(
            Technician(
                id = technicianRequest.id,
                name = technicianRequest.name,
                cpf = technicianRequest.cpf,
                phoneNumber = technicianRequest.phoneNumber
            )
        )?.let { technician ->
            technicianResponseMapper.map(technician)
        }
    }
}

