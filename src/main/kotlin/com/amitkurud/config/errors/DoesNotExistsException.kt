/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.config.errors

import org.zalando.problem.AbstractThrowableProblem
import org.zalando.problem.Exceptional

open class DoesNotExistsException(val entityName: String) : AbstractThrowableProblem(
    BUSINESS_VALIDATION_EXCEPTION,
    ExceptionGroup.ENTITY_DOES_NOT_EXCEPTION.value,
    Status.ENTITY_DOES_NOT_EXISTS
) {
    override fun getCause(): Exceptional? = super.cause
}