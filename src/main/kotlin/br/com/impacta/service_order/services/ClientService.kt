package br.com.impacta.service_order.services

import br.com.impacta.service_order.domain.Client
import br.com.impacta.service_order.dtos.ClientResponse
import br.com.impacta.service_order.exceptions.NotFoundException
import br.com.impacta.service_order.mapper.ClientResponseMapper
import br.com.impacta.service_order.repositories.ClientRepository
import br.com.impacta.service_order.repositories.PersonRepository
import br.com.impacta.service_order.requests.ClientCreationRequest
import br.com.impacta.service_order.requests.ClientUpdateRequest
import org.springframework.stereotype.Service
import javax.validation.Valid

@Service
class ClientService(
    private val clientRepository: ClientRepository,
    private val clientResponseMapper: ClientResponseMapper,
    private val personRepository: PersonRepository
) {

    val notFoundMessage =
        "Cliente não encontrado! Tente listar todos os clientes registrados para realizar a operação para um ID cadastrado."

    fun findById(id: Int): ClientResponse {
        return clientRepository.findByid(id)?.let {
            clientResponseMapper.map(it)
        }
            ?: throw NotFoundException(notFoundMessage)
    }

    fun findAll(): List<ClientResponse> {
        return clientRepository.findAll().map { t ->
            clientResponseMapper.map(t)
        }
    }

    fun create(clientRequest: ClientCreationRequest): ClientResponse {
        return clientRepository.findByCpf(clientRequest.cpf)?.let {
            throw Exception("CPF já cadastrado na base de dados!")
        } ?: clientRepository.save(
            Client(
                id = clientRequest.id,
                name = clientRequest.name,
                cpf = clientRequest.cpf,
                phoneNumber = clientRequest.phoneNumber
            )
        )?.let { client ->
            clientResponseMapper.map(client)
        }
    }

    fun update(id: Int, @Valid clientRequest: ClientUpdateRequest): ClientResponse {
        return clientRepository.findByid(id)?.let {
            val client = it

            clientRepository.save(
                Client(
                    id = client.id,
                    name = clientRequest.name,
                    cpf = personRepository.findByCpf(clientRequest.cpf)?.let {
                        if (it.cpf == clientRequest.cpf && it.id == client.id) {
                            clientRequest.cpf
                        } else {
                            throw Exception("CPF já cadastrado na base de dados!")
                        }
                    } ?: clientRequest.cpf,
                    phoneNumber = clientRequest.phoneNumber
                )
            ).let { updatedClient ->
                clientResponseMapper.map(updatedClient)
            }
        }
            ?: throw NotFoundException(notFoundMessage)
    }

    fun delete(id: Int) {
        clientRepository.findByid(id)?.let {
            if (it.soList.any { it.status.toString() != "FINISHED" }) {
                throw Exception("Não é possível realizar a operação. Cliente possui Ordens de Serviço ativas!")
            } else {
                clientRepository.delete(it)
            }
        }
            ?: throw NotFoundException(notFoundMessage)
    }
}

