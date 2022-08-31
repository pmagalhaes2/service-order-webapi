package br.com.impacta.service_order.domain

import org.hibernate.validator.constraints.br.CPF
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
abstract class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Int?,

    open val name: String,

    @CPF
    open val cpf: String,

    open val phoneNumber: String
)
