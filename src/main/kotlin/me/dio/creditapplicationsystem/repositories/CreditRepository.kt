package me.dio.creditapplicationsystem.repositories

import me.dio.creditapplicationsystem.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository

interface CreditRepository: JpaRepository<Credit, Long>