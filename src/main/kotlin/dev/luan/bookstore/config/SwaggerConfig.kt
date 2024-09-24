package dev.luan.bookstore.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("!prod")
@Configuration
class SwaggerConfig {

    @Bean
    fun customApi(): OpenAPI {

        return OpenAPI()
            .info(Info().title("Bookstore API").version("0.0.1"))
    }
}