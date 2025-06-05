package de.nstdspace.easycrmbackend

import logger
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class AdminApi(val clientRepository: ClientRepository) {
    @PostMapping("/admin/seed")
    fun initData() {
        logger.info { "banana" }
        val client =
            ClientEntity().apply {
                firstName = "banana"
                lastName = "maracuja"
                address =
                    AddressEntity().apply {
                        city = "Bananenstadt"
                        plz = "34984"
                    }
            }

        clientRepository.save(client)
    }
}
