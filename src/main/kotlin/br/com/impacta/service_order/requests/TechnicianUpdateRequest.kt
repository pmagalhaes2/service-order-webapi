package br.com.impacta.service_order.requests

import org.hibernate.validator.constraints.br.CPF
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Pattern

data class TechnicianUpdateRequest(

    @field:Pattern(
        regexp = "^[\\pL\\pM\\p{Zs}]+\$",
        message = "O campo NOME deve conter apenas letras"
    )
    val name: String,

    @field:CPF(message = "Número de CPF inválido")
    val cpf: String,

    @field:NotEmpty(message = "O campo TELEFONE é requerido")
    val phoneNumber: String,
)