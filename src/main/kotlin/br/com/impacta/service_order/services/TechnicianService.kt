package br.com.impacta.service_order.services

import br.com.impacta.service_order.domain.Technician
import br.com.impacta.service_order.dtos.TechnicianResponse
import br.com.impacta.service_order.exceptions.NotFoundException
import br.com.impacta.service_order.mapper.TechnicianResponseMapper
import br.com.impacta.service_order.repositories.TechnicianRepository
import br.com.impacta.service_order.requests.TechnicianCreationRequest
import br.com.impacta.service_order.requests.TechnicianUpdateRequest
import org.springframework.stereotype.Service
import javax.validation.Valid

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

    fun update(id: Int, @Valid technicianRequest: TechnicianUpdateRequest): TechnicianResponse {
        return technicianRepository.findByid(id)?.let {
            val technician = it

            technicianRepository.save(
                Technician(
                    id = technician.id,
                    name = technicianRequest.name,
                    cpf = technicianRepository.findByCpf(technicianRequest.cpf)?.let {
                        if (it.cpf == technicianRequest.cpf && it.id == technician.id) {
                            technicianRequest.cpf
                        } else {
                            throw Exception("CPF já cadastrado na base de dados!")
                        }
                    } ?: technicianRequest.cpf,
                    phoneNumber = technicianRequest.phoneNumber
                )
            ).let { updatedTechnician ->
                technicianResponseMapper.map(updatedTechnician)
            }
        }
            ?: throw NotFoundException("Técnico não encontrado! Tente listar todos os técnicos registrados para realizar a operação para um ID cadastrado.")
    }
}

