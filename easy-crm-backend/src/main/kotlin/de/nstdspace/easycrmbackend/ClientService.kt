package de.nstdspace.easycrmbackend

import org.springframework.stereotype.Service

@Service
class ClientService(private val clientRepository: ClientRepository) {
    fun getClients() = clientRepository.findAll().map { it.toClientDto() }
}
