package de.nstdspace.easycrmbackend

import io.mcarle.konvert.api.KonvertTo
import io.mcarle.konvert.api.Mapping
import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "address")
class AddressEntity {
    @Id @GeneratedValue var id: UUID? = null
    @Column(nullable = false) lateinit var city: String
    @Column(nullable = false) lateinit var zip: String
}

@Entity
@Table(name = "client")
@KonvertTo(
    ClientDto::class,
    mappings =
        [
            Mapping(target = "id", expression = "id!!"),
            Mapping(target = "city", expression = "address.city"),
            Mapping(target = "zip", expression = "address.zip"),
        ],
)
class ClientEntity {
    @Id @GeneratedValue val id: UUID? = null
    @Column var firstName: String? = null
    @Column var lastName: String? = null

    @Column var company: String? = null
    @Column lateinit var email: String

    @Column lateinit var createdAt: Instant

    @Column lateinit var userId: UUID

    @ManyToOne(optional = false, cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "address_id", nullable = false)
    lateinit var address: AddressEntity
}
