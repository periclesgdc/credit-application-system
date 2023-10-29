package me.dio.creditapplicationsystem.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

  @Bean
  fun infoApi(): OpenAPI = OpenAPI().info(
    Info().title("My API").description("API Desc").version("1.0.0")
  )
}