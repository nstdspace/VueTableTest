package de.nstdspace.easycrmbackend

import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ClientService(private val clientRepository: ClientRepository) {
    fun getClients(userId: UUID) = clientRepository.findByUserId(userId).map { it.toClientDto() }
}
