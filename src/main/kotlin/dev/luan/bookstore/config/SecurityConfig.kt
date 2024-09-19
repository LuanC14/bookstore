package dev.luan.bookstore.config

import dev.luan.bookstore.security.CustomAuthenticationEntryPoint
import dev.luan.bookstore.security.SecurityCustomerFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@EnableMethodSecurity
class SecurityConfig(
    private val securityCustomerFilter: SecurityCustomerFilter,
    private val customEntryPoint: CustomAuthenticationEntryPoint
) {

    private val SWAGGER_PUBLIC_MATCHERS = arrayOf(
        "/swagger-ui/**",
        "/v3/api-docs/**",
        "/swagger-resource/**"
    )

    private val APPLICATION_PUBLIC_MATCHERS = arrayOf(
        "/customer",
        "/auth",
    )

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        return http
            .csrf { csrf -> csrf.disable() }
            .sessionManagement { sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { req ->
                req
                    .requestMatchers(*SWAGGER_PUBLIC_MATCHERS).permitAll()
                    .requestMatchers(*APPLICATION_PUBLIC_MATCHERS).permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(securityCustomerFilter, BasicAuthenticationFilter::class.java)
            .exceptionHandling{it.authenticationEntryPoint(customEntryPoint)}
            .build()
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder();
    }
}