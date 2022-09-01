package br.com.impacta.service_order.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Technician(
    id: Int?,
    name: String,
    cpf: String,
    phoneNumber: String,
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "technician")
    @JsonIgnore
    val soList: List<ServiceOrder> = ArrayList()
) : Person(
    id,
    name,
    cpf,
    phoneNumber
)