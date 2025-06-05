package de.nstdspace.easycrmbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication class EasyCrmBackendApplication

fun main(args: Array<String>) {
    runApplication<EasyCrmBackendApplication>(*args)
}
