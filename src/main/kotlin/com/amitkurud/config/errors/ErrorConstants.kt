package com.amitkurud.config.errors

import java.net.URI

const val ERR_CONCURRENCY_FAILURE: String = "error.concurrencyFailure"
const val ERR_VALIDATION: String = "error.validation"
const val PROBLEM_BASE_URL: String = "localhost:8080"
@JvmField
val DEFAULT_TYPE: URI = URI.create("$PROBLEM_BASE_URL/problem-with-message")
@JvmField
val CONSTRAINT_VIOLATION_TYPE: URI = URI.create("$PROBLEM_BASE_URL/constraint-violation")
