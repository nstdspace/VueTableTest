package de.nstdspace.easycrmbackend

import java.time.Instant
import logger
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AdminController(val clientRepository: ClientRepository) {
    @PostMapping("/admin/seed")
    fun initData(@RequestBody clientsXlsx: ByteArray) {
        WorkbookFactory.create(clientsXlsx.inputStream()).use {
            val sheet = it.getSheetAt(0)
            val clients = sheet.drop(1).map(::createClient)
            clientRepository.saveAll(clients)
            logger.info { "Successfully saved ${clients.size} clients" }
        }
    }

    private fun createClient(row: Row) =
        ClientEntity().apply {
            lastName = row.getCell(0)?.stringCellValue
            firstName = row.getCell(1)?.stringCellValue
            company = row.getCell(2)?.stringCellValue
            createdAt = Instant.now()
            email = row.getCell(5).stringCellValue
            address =
                AddressEntity().apply {
                    city = row.getCell(3).stringCellValue
                    zip =
                        row.getCell(4).let {
                            when (it.cellType) {
                                CellType.NUMERIC -> it.numericCellValue.toInt().toString()
                                CellType.STRING -> it.stringCellValue
                                else -> error("")
                            }
                        }
                }
        }
}
