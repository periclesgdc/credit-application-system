package me.dio.creditapplicationsystem.entity

import me.dio.creditapplicationsystem.enums.Status
import jakarta.persistence.*
import java.util.UUID
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "credit")
data class Credit(
  @Column(nullable = false, unique = true) val creditCode: UUID = UUID.randomUUID(),
  @Column(nullable = false) val creditValue: BigDecimal = BigDecimal.ZERO,
  @Column(nullable = false) val dayFirstInstallment: LocalDate,
  @Column(nullable = false) val numberOfInstallments: Int = 0,
  @Enumerated val status: Status = Status.IN_PROGRESS,
  @ManyToOne var customer: Customer? = null,
  @Id val id: Long? = null
)