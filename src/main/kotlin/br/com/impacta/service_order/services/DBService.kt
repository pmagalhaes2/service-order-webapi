package br.com.impacta.service_order.services


import br.com.impacta.service_order.domain.Client
import br.com.impacta.service_order.domain.Priority
import br.com.impacta.service_order.domain.ServiceOrder
import br.com.impacta.service_order.domain.Status
import br.com.impacta.service_order.domain.Technician
import br.com.impacta.service_order.repositories.ClientRepository
import br.com.impacta.service_order.repositories.ServiceOrderRepository
import br.com.impacta.service_order.repositories.TechnicianRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DBService {

    @Autowired
    lateinit var technicianRepository: TechnicianRepository

    @Autowired
    lateinit var clientRepository: ClientRepository

    @Autowired
    lateinit var serviceOrderRepository: ServiceOrderRepository

    fun initialDB() {
        val t1 = Technician(
            id = null,
            name = "Pedro Júnior",
            cpf = "319.671.319-96",
            phoneNumber = "(31) 3820-7992"
        )

        val t2 = Technician(
            id = null,
            name = "Luiza Arantes",
            cpf = "986.077.750-06",
            phoneNumber = "(11) 2222-2148"
        )

        val c1 = Client(
            id = null,
            name = "Erica Lopes",
            cpf = "342.086.555-42",
            phoneNumber = "(79) 98941-0806"
        )

        val so1 = ServiceOrder(
            id = null,
            priority = Priority.HIGH,
            notes = "Test create SO",
            status = Status.IN_PROGRESS,
            technician = t1,
            client = c1
        )

        t1.getSOList().plus(so1)
        c1.getSOList().plus(so1)

        technicianRepository.saveAll(listOf(t1))
        technicianRepository.saveAll(listOf(t2))
        clientRepository.saveAll(listOf(c1))
        serviceOrderRepository.saveAll(listOf(so1))
    }
}
