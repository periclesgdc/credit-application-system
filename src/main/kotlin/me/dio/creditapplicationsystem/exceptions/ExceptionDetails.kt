package me.dio.creditapplicationsystem.exceptions

import java.time.LocalDateTime

class ExceptionDetails(
  val title: String,
  val timestamp: LocalDateTime,
  val status: Int,
  val exception: String,
  val details: MutableMap<String, String?>
)