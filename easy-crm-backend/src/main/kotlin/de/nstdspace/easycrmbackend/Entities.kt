package de.nstdspace.easycrmbackend

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "address")
class AddressEntity {
    @Id @GeneratedValue var id: UUID? = null
    @Column(nullable = false) lateinit var city: String
    @Column(nullable = false) lateinit var plz: String
}

@Entity
@Table(name = "client")
class ClientEntity {
    @Id @GeneratedValue val id: UUID? = null
    @Column var firstName: String? = null
    @Column var lastName: String? = null

    @Column var company: String? = null

    @ManyToOne(optional = false, cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "address_id", nullable = false)
    lateinit var address: AddressEntity
}
