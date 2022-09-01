package br.com.impacta.service_order.mapper

import br.com.impacta.service_order.domain.Technician
import br.com.impacta.service_order.dtos.TechnicianResponse
import org.springframework.stereotype.Component

@Component
class TechnicianResponseMapper : Mapper<Technician, TechnicianResponse> {
    override fun map(t: Technician): TechnicianResponse {
        return TechnicianResponse(
            id = t.id,
            cpf = t.cpf,
            name = t.name,
            phoneNumber = t.phoneNumber
        )
    }
}