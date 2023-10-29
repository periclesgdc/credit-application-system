package me.dio.creditapplicationsystem.repositories

import me.dio.creditapplicationsystem.entities.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface CreditRepository: JpaRepository<Credit, Long> {
  fun findByCreditCode(creditCode: UUID): Credit?

  @Query(nativeQuery = true, value = """
    select * from credit
    where credit_id = ?1
  """)
  fun findAllByCustomer(customerId: Long): List<Credit>
}