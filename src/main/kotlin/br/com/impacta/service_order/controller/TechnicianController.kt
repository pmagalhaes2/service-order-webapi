package br.com.impacta.service_order.controller

import br.com.impacta.service_order.dtos.TechnicianResponse
import br.com.impacta.service_order.services.TechnicianService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/technicians")
class TechnicianController(
    val technicianService: TechnicianService
) {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getTechnicianById(@PathVariable id: Int) : TechnicianResponse {
        return technicianService.findById(id)
    }

    @GetMapping
    fun getTechnicians() : List<TechnicianResponse> {
        return technicianService.findAll()
    }
}