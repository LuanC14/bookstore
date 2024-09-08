package dev.luan.bookstore.annotation.validations

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [EmailAvailableValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class EmailAvaiable(
    val message: String = "O Email já está em uso",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
