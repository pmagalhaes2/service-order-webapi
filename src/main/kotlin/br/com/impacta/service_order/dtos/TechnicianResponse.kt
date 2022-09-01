package br.com.impacta.service_order.dtos

import org.hibernate.validator.constraints.br.CPF

data class TechnicianResponse(
    val id: Int?,
    val name: String,
    @CPF
    val cpf: String,
    val phoneNumber: String
)
