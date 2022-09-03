package br.com.impacta.service_order.controller

import br.com.impacta.service_order.dtos.TechnicianResponse
import br.com.impacta.service_order.requests.TechnicianCreationRequest
import br.com.impacta.service_order.requests.TechnicianUpdateRequest
import br.com.impacta.service_order.services.TechnicianService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.transaction.Transactional
import javax.validation.Valid

@CrossOrigin("*")
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

    @PutMapping("/{id}")
    @Transactional
    fun updateTechnicianById(
        @PathVariable id: Int,
        @RequestBody @Valid technicianRequest: TechnicianUpdateRequest
    ): TechnicianResponse {
        return technicianService.update(id, technicianRequest)
    }

    @DeleteMapping("/{id}")
    @Transactional
    fun deleteTechnicianById(
        @PathVariable id: Int
    ) {
        return technicianService.delete(id)
    }
}