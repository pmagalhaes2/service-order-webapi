package br.com.impacta.service_order.mapper

import br.com.impacta.service_order.domain.Client
import br.com.impacta.service_order.dtos.ClientResponse
import org.springframework.stereotype.Component

@Component
class ClientResponseMapper : Mapper<Client, ClientResponse> {
    override fun map(c: Client): ClientResponse {
        return ClientResponse(
            id = c.id,
            cpf = c.cpf,
            name = c.name,
            phoneNumber = c.phoneNumber
        )
    }

}