package br.com.impacta.service_order.repositories

import br.com.impacta.service_order.domain.ServiceOrder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServiceOrderRepository : JpaRepository<ServiceOrder, Int> {
}