package br.com.impacta.service_order.requests

import br.com.impacta.service_order.domain.Priority
import br.com.impacta.service_order.domain.Status
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

class ServiceOrderUpdateRequest(

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    val endingDate: LocalDateTime?,

    val priority: Priority = Priority.LOW,

    val notes: String,

    val status: Status = Status.OPENED,

    val technician: Int,

    val client: Int
)
