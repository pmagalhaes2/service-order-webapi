package br.com.impacta.service_order.controller

import br.com.impacta.service_order.dtos.ServiceOrderResponse
import br.com.impacta.service_order.requests.ServiceOrderCreationRequest
import br.com.impacta.service_order.services.ServiceOrderService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/service-order")
class ServiceOrderController(
    val serviceOrderService: ServiceOrderService
) {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getServiceOrderById(@PathVariable id: Int): ServiceOrderResponse {
        return serviceOrderService.findById(id)
    }

    @GetMapping
    fun getServiceOrders(): List<ServiceOrderResponse> {
        return serviceOrderService.findAll()
    }

    @PostMapping
    @Transactional
    fun createServiceOrder(@RequestBody @Valid serviceOrderRequest: ServiceOrderCreationRequest): ServiceOrderResponse {
        return serviceOrderService.create(serviceOrderRequest)
    }
}