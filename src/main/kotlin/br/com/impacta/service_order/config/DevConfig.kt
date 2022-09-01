package br.com.impacta.service_order.config

import br.com.impacta.service_order.services.DBService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("dev")
class DevConfig {

    @Autowired
    lateinit var dbService: DBService

    @Value("""spring.jpa.hibernate.ddl-auto""")
    lateinit var ddl: String

    @Bean
    fun initialDB() {
//        if (ddl.equals("create")) {
            dbService.initialDB()
//        }
//        return false
    }
}