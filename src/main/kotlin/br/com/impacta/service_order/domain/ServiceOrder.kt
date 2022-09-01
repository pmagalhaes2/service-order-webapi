package br.com.impacta.service_order.domain

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "service_order")
data class ServiceOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "opening_date")
    val openingDate: LocalDateTime = LocalDateTime.now(),

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "ending_date")
    val endingDate: LocalDateTime? = null,

    val priority: Priority = Priority.LOW,

    val notes: String,

    val status: Status = Status.OPENED,

    @ManyToOne
    @JoinColumn(name = "technician_id", referencedColumnName = "id")
    val technician: Technician,

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    val client: Client
)