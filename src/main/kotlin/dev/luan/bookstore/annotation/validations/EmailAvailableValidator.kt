package dev.luan.bookstore.annotation.validations

import dev.luan.bookstore.service.CustomerService
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class EmailAvailableValidator(val customerService: CustomerService) : ConstraintValidator<EmailAvaiable, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value.isNullOrEmpty()) {
            return false
        }
        return !customerService.verifyIfEmailIsAvailable(value)
    }
}