package me.dio.creditapplicationsystem.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun handleException(ex: MethodArgumentNotValidException): ResponseEntity<ExceptionDetails> {
    val errors: MutableMap<String, String?> = HashMap()

    ex.bindingResult.allErrors.stream().forEach {
      error: ObjectError ->
      val fieldName: String = (error as FieldError).field
      val messageError: String? = error.defaultMessage

      errors[fieldName] = messageError
    }

    return ResponseEntity(ExceptionDetails(
      title = "Bad Request, consult docs",
      timestamp = LocalDateTime.now(),
      status = HttpStatus.BAD_REQUEST.value(),
      exception = ex.objectName.toString(),
      details = errors
    ), HttpStatus.BAD_REQUEST)
  }
}