package br.com.impacta.service_order.dtos

import br.com.impacta.service_order.domain.Client
import br.com.impacta.service_order.domain.Priority
import br.com.impacta.service_order.domain.Status
import br.com.impacta.service_order.domain.Technician
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class ServiceOrderResponse(
    val id: Int?,

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    val openingDate: LocalDateTime = LocalDateTime.now(),

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    val endingDate: LocalDateTime? = null,

    val priority: Priority = Priority.LOW,

    val notes: String,

    val status: Status = Status.OPENED,

    val technician: Int?,

    val client: Int?
)