package de.nstdspace.easycrmbackend

import java.util.UUID
import org.springframework.stereotype.Service

@Service
class ClientService(private val clientRepository: ClientRepository) {
    fun getClients(userId: UUID) = clientRepository.findByUserId(userId).map { it.toClientDto() }
}
