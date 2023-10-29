package me.dio.creditapplicationsystem.services.impl

import me.dio.creditapplicationsystem.entity.Credit
import me.dio.creditapplicationsystem.repositories.CreditRepository
import me.dio.creditapplicationsystem.services.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService(
  private val creditRepository: CreditRepository,
  private val customerService: CustomerService
): ICreditService {
  override fun save(credit: Credit): Credit {
    credit.apply {
      customer = customerService.findById(credit.customer?.id!!)
    }

    return this.creditRepository.save(credit)
  }

  override fun findAllByCustomer(customerId: Long): List<Credit> = this.creditRepository.findAllByCustomer(customerId)

  override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
    val credit: Credit = (this.creditRepository
      .findByCreditCode(creditCode) ?: throw RuntimeException("Credit $creditCode not found"))

    return if (customerId == credit.customer?.id) credit else throw RuntimeException("Contact admin")
  }
}