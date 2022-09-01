package br.com.impacta.service_order.controller

import br.com.impacta.service_order.dtos.TechnicianResponse
import br.com.impacta.service_order.requests.TechnicianCreationRequest
import br.com.impacta.service_order.services.TechnicianService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/technicians")
class TechnicianController(
    val technicianService: TechnicianService
) {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getTechnicianById(@PathVariable id: Int): TechnicianResponse {
        return technicianService.findById(id)
    }

    @GetMapping
    fun getTechnicians(): List<TechnicianResponse> {
        return technicianService.findAll()
    }

    @PostMapping
    @Transactional
    fun createTechnician(@RequestBody @Valid technicianRequest: TechnicianCreationRequest): TechnicianResponse {
        return technicianService.create(technicianRequest)
    }
}