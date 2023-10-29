package me.dio.creditapplicationsystem.controllers

import me.dio.creditapplicationsystem.dto.CreditDto
import me.dio.creditapplicationsystem.dto.CreditView
import me.dio.creditapplicationsystem.dto.CreditViewList
import me.dio.creditapplicationsystem.entities.Credit
import me.dio.creditapplicationsystem.services.impl.CreditService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.stream.Collectors

@RestController
@RequestMapping("api/credits")
class CreditController(
  private val creditService: CreditService
) {

  @PostMapping
  fun save(creditDto: CreditDto): ResponseEntity<String> {
    val credit: Credit = this.creditService.save(creditDto.toEntity())

    return ResponseEntity.status(HttpStatus.CREATED).body("Credit ${credit.id} was created for ${credit.customer?.firstName}")
  }

  @GetMapping
  fun findAllByCustomer(@RequestParam customerId: Long): ResponseEntity<List<CreditViewList>> {
    val creditViewList = this.creditService.findAllByCustomer(customerId).stream()
      .map { credit: Credit -> CreditViewList(credit) }
      .collect(Collectors.toList())

    return ResponseEntity.ok(creditViewList)
  }

  @GetMapping("creditCode")
  fun findByCreditCode(@RequestParam customerId: Long, @PathVariable creditCode: UUID): ResponseEntity<CreditView> {
    val credit: Credit = this.creditService.findByCreditCode(customerId, creditCode)

    return ResponseEntity.ok(CreditView(credit))
  }
}