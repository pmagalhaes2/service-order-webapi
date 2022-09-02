package br.com.impacta.service_order.services

import br.com.impacta.service_order.dtos.ServiceOrderResponse
import br.com.impacta.service_order.exceptions.NotFoundException
import br.com.impacta.service_order.mapper.ServiceOrderResponseMapper
import br.com.impacta.service_order.repositories.ServiceOrderRepository
import org.springframework.stereotype.Service

@Service
class ServiceOrderService(
    private val serviceOrderRepository: ServiceOrderRepository,
    private val serviceOrderResponseMapper: ServiceOrderResponseMapper
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
}