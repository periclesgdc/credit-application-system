package me.dio.creditapplicationsystem.repositories

import me.dio.creditapplicationsystem.entities.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository: JpaRepository<Customer, Long>