package br.com.impacta.service_order.controller

import br.com.impacta.service_order.dtos.ClientResponse
import br.com.impacta.service_order.requests.ClientCreationRequest
import br.com.impacta.service_order.requests.ClientUpdateRequest
import br.com.impacta.service_order.services.ClientService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.transaction.Transactional
import javax.validation.Valid

@CrossOrigin("*")
@RestController
@RequestMapping("/clients")
class ClientController(
    val clientService: ClientService
) {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getClientById(@PathVariable id: Int): ClientResponse {
        return clientService.findById(id)
    }

    @GetMapping
    fun getClients(): List<ClientResponse> {
        return clientService.findAll()
    }

    @PostMapping
    @Transactional
    fun createClient(@RequestBody @Valid clientRequest: ClientCreationRequest): ClientResponse {
        return clientService.create(clientRequest)
    }

    @PutMapping("/{id}")
    @Transactional
    fun updateClientById(
        @PathVariable id: Int,
        @RequestBody @Valid clientRequest: ClientUpdateRequest
    ): ClientResponse {
        return clientService.update(id, clientRequest)
    }

    @DeleteMapping("/{id}")
    @Transactional
    fun deleteClientById(
        @PathVariable id: Int
    ) {
        return clientService.delete(id)
    }
}