package dev.luan.bookstore.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableMethodSecurity
class SecurityConfig {

    private val PUBLIC_MATCHERS = arrayOf(
        "/customer",
        "/swagger-ui/**",
        "/v3/api-docs/**",
        "/swagger-resource/**",
    )

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        return http
            .csrf { csrf -> csrf.disable() }
            .sessionManagement { sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { req ->
                req
                    .requestMatchers(*PUBLIC_MATCHERS).permitAll()
                    .anyRequest().authenticated()
            }
            .build()
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder();
    }
}