package me.dio.creditapplicationsystem.controllers

import com.electronwill.nightconfig.core.conversion.Path
import jakarta.validation.Valid
import me.dio.creditapplicationsystem.dto.CustomerDto
import me.dio.creditapplicationsystem.dto.CustomerUpdateDto
import me.dio.creditapplicationsystem.dto.CustomerView
import me.dio.creditapplicationsystem.entities.Customer
import me.dio.creditapplicationsystem.services.impl.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/customers")
class CustomerController(
  private val customerService: CustomerService
) {

  @PostMapping
  fun save(@RequestBody @Valid customerDto: CustomerDto): ResponseEntity<String> {
    val saved = this.customerService.save(customerDto.toEntity())

    return ResponseEntity.status(HttpStatus.CREATED).body("Customer ${saved.email} saved!")
  }

  @GetMapping("{id}")
  fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> {
    val customer: Customer = this.customerService.findById(id)

    return ResponseEntity.ok(CustomerView(customer))
  }

  @DeleteMapping("{id}")
  fun delete(@PathVariable id: Long) = this.customerService.delete(id)

  @PatchMapping
  fun update(@RequestParam(value = "customerId") id: Long, @RequestBody @Valid customerUpdateDto: CustomerUpdateDto): ResponseEntity<CustomerView> {
    val customer = this.customerService.findById(id)
    val customerToUpdate: Customer = customerUpdateDto.toEntity(customer)
    val updated = this.customerService.save(customerToUpdate)

    return ResponseEntity.ok(CustomerView(updated))
  }
}