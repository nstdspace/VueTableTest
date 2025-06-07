package de.nstdspace.easycrmbackend

import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository : JpaRepository<ClientEntity, UUID> {
    fun findByUserId(id: UUID): Set<ClientEntity>
}
