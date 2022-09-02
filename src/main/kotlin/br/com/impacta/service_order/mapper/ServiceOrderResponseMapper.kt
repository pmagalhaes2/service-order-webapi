package br.com.impacta.service_order.mapper

import br.com.impacta.service_order.domain.ServiceOrder
import br.com.impacta.service_order.dtos.ServiceOrderResponse
import org.springframework.stereotype.Component

@Component
class ServiceOrderResponseMapper : Mapper<ServiceOrder, ServiceOrderResponse> {

    override fun map(s: ServiceOrder): ServiceOrderResponse {

        return ServiceOrderResponse(
            id = s.id,
            openingDate = s.openingDate,
            endingDate = s.endingDate,
            priority = s.priority,
            notes = s.notes,
            status = s.status,
            technician = s.technician.id,
            client = s.client.id
        )
    }

}