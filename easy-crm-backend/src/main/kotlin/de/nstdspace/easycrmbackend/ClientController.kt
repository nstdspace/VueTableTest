package de.nstdspace.easycrmbackend

import ClientDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ClientController(private val clientService: ClientService) {
    @GetMapping("clients") fun getClients(): List<ClientDto> = clientService.getClients()
}
