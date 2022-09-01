package br.com.impacta.service_order.requests

import org.hibernate.validator.constraints.br.CPF
import javax.validation.constraints.Pattern

data class ClientUpdateRequest(

    @field:Pattern(
        regexp = "^[\\pL\\pM\\p{Zs}]+\$",
        message = "O campo NOME deve conter apenas letras"
    )
    val name: String,

    @field:CPF(message = "Número de CPF inválido")
    val cpf: String,

    val phoneNumber: String,
)