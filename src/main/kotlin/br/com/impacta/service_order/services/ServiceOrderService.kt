package br.com.impacta.service_order.services

import br.com.impacta.service_order.domain.ServiceOrder
import br.com.impacta.service_order.dtos.ServiceOrderResponse
import br.com.impacta.service_order.exceptions.NotFoundException
import br.com.impacta.service_order.mapper.ServiceOrderResponseMapper
import br.com.impacta.service_order.repositories.ClientRepository
import br.com.impacta.service_order.repositories.ServiceOrderRepository
import br.com.impacta.service_order.repositories.TechnicianRepository
import br.com.impacta.service_order.requests.ServiceOrderCreationRequest
import br.com.impacta.service_order.requests.ServiceOrderUpdateRequest
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.validation.Valid

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
                endingDate =  when (serviceOrderRequest.status.toString()) {
                    "FINISHED" -> LocalDateTime.now()
                    else -> {
                        null
                    }
                },
                technician = technicianRepository.findByid(serviceOrderRequest.technician)
                    ?: throw NotFoundException("Não existe técnico cadastrado com esse ID!"),
                client = clientRepository.findByid(serviceOrderRequest.client)
                    ?: throw NotFoundException("Não existe cliente cadastrado com esse ID!")
            )
        ).let { serviceOrder ->
            serviceOrderResponseMapper.map(serviceOrder)
        }
    }

    fun update(id: Int, @Valid serviceOrderRequest: ServiceOrderUpdateRequest): ServiceOrderResponse {
        return serviceOrderRepository.findByid(id)?.let {
            val serviceOrder = it

            serviceOrderRepository.save(
                ServiceOrder(
                    id = serviceOrder.id,
                    openingDate = serviceOrder.openingDate,
                    endingDate = when (serviceOrder.status.toString()) {
                        "FINISHED" -> serviceOrder.endingDate
                        else -> {
                            if (serviceOrderRequest.status.toString() == "FINISHED") {
                                LocalDateTime.now()
                            } else {
                                serviceOrder.endingDate
                            }
                        }
                    },
                    priority = serviceOrderRequest.priority,
                    notes = serviceOrderRequest.notes,
                    status = serviceOrderRequest.status,
                    technician = technicianRepository.findByid(serviceOrderRequest.technician)
                        ?: throw NotFoundException("Não existe técnico cadastrado com esse ID!"),
                    client = clientRepository.findByid(serviceOrderRequest.client)
                        ?: throw NotFoundException("Não existe cliente cadastrado com esse ID!")
                )
            ).let { updatedServiceOrder ->
                serviceOrderResponseMapper.map(updatedServiceOrder)
            }
        }
            ?: throw NotFoundException(notFoundMessage)
    }
}
