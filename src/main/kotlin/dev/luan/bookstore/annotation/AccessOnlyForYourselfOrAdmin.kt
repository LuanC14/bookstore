package dev.luan.bookstore.annotation

import org.springframework.security.access.prepost.PreAuthorize

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("hasRole('ROLE_ADMIN') || #id.toString() == authentication.principal.toString()")
annotation class AccessOnlyForYourselfOrAdmin()
