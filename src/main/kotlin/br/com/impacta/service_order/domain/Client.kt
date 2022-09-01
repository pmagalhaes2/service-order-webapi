package br.com.impacta.service_order.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Client(
    id: Int?,
    name: String,
    cpf: String,
    phoneNumber: String,
    @OneToMany(mappedBy = "client")
    @JsonIgnore
    val soList: List<ServiceOrder> = ArrayList(),
) : Person(
    id,
    name,
    cpf,
    phoneNumber
)