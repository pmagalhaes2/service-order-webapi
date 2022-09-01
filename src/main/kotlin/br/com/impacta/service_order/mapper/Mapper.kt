package br.com.impacta.service_order.mapper

import br.com.impacta.service_order.domain.Client
import br.com.impacta.service_order.domain.Technician

interface Mapper<T, U> {
    fun map(t: T): U
}