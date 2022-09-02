package br.com.impacta.service_order.services

import br.com.impacta.service_order.domain.ServiceOrder
import br.com.impacta.service_order.dtos.ServiceOrderResponse
import br.com.impacta.service_order.exceptions.NotFoundException
import br.com.impacta.service_order.mapper.ServiceOrderResponseMapper
import br.com.impacta.service_order.repositories.ClientRepository
import br.com.impacta.service_order.repositories.ServiceOrderRepository
import br.com.impacta.service_order.repositories.TechnicianRepository
import br.com.impacta.service_order.requests.ServiceOrderCreationRequest
import org.springframework.stereotype.Service

@Service
class ServiceOrderService(
    private val serviceOrderRepository: ServiceOrderRepository,
    private val serviceOrderResponseMapper: ServiceOrderResponseMapper,
    private val technicianRepository: TechnicianRepository,
    private val clientRepository: ClientRepository
) {

    val notFoundMessage =
        "Ordem de Serviço não encontrada! Tente listar todas as OS's registradas para realizar a operação para um ID cadastrado."

    fun findById(id: Int): ServiceOrderResponse {
        return serviceOrderRepository.findByid(id)?.let {
            serviceOrderResponseMapper.map(it)
        } ?: throw NotFoundException(notFoundMessage)
    }

    fun findAll(): List<ServiceOrderResponse> {
        return serviceOrderRepository.findAll().map { so ->
            serviceOrderResponseMapper.map(so)
        }
    }

    fun create(serviceOrderRequest: ServiceOrderCreationRequest): ServiceOrderResponse {
        return serviceOrderRepository.save(
            ServiceOrder(
                id = serviceOrderRequest.id,
                notes = serviceOrderRequest.notes,
                priority = serviceOrderRequest.priority,
                status = serviceOrderRequest.status,
                technician = technicianRepository.findByid(serviceOrderRequest.technician)
                    ?: throw NotFoundException("Não existe técnico cadastrado com esse ID!"),
                client = clientRepository.findByid(serviceOrderRequest.client)
                    ?: throw NotFoundException("Não existe cliente cadastrado com esse ID!")
            )
        ).let { serviceOrder ->
            serviceOrderResponseMapper.map(serviceOrder)
        }
    }
}
