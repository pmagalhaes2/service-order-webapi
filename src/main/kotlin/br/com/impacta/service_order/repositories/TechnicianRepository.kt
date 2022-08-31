package br.com.impacta.service_order.repositories

import br.com.impacta.service_order.domain.Technician
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TechnicianRepository : JpaRepository<Technician, Int> {
}