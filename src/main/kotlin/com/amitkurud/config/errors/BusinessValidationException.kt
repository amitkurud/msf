/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.config.errors

import org.zalando.problem.AbstractThrowableProblem
import org.zalando.problem.Exceptional

open class BusinessValidationException(override val message: String, val entityName: String, val errorKey: String) :
    AbstractThrowableProblem(BUSINESS_VALIDATION_EXCEPTION, message, Status.BUSINESS_VALIDATION) {
    override fun getCause(): Exceptional? = super.cause
}