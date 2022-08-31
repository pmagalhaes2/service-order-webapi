package br.com.impacta.service_order.config

import br.com.impacta.service_order.services.DBService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("test")
class TestConfig {

    @Autowired
    lateinit var dbService: DBService

    @Bean
    fun initialDB() {
        dbService.initialDB()
    }
}