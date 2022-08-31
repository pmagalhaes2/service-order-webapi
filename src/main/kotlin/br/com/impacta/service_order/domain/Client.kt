package br.com.impacta.service_order.domain

import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Client(
    id: Int?,
    name: String,
    cpf: String,
    phoneNumber: String,
    @OneToMany(mappedBy = "client")
    val list: List<ServiceOrder> = ArrayList(),
) : Person(
    id,
    name,
    cpf,
    phoneNumber
) {

    fun getSOList(): List<ServiceOrder?> {
        return list
    }
}