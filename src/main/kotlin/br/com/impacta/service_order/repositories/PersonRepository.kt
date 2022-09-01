package br.com.impacta.service_order.repositories

import br.com.impacta.service_order.domain.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<Person, Int> {

    fun findByCpf(cpf: String): Person?
}