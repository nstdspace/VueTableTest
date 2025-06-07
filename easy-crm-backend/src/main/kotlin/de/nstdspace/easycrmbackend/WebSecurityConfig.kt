package de.nstdspace.easycrmbackend

import java.util.UUID
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .cors {}
            .oauth2ResourceServer { it.jwt {} }
            .authorizeHttpRequests { it.anyRequest().authenticated() }
            .build()
    }
}

val Jwt.userId: UUID
    get() = UUID.fromString(claims["sub"].toString())
