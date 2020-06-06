/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.config.errors

import org.apiguardian.api.API
import org.zalando.problem.StatusType
import javax.annotation.Nonnull

@API(status = API.Status.MAINTAINED)
enum class Status(statusCode: Int, reasonPhrase: String) : StatusType {

    ENTITY_DOES_NOT_EXISTS(404, "Entity Does not exists"),
    BUSINESS_VALIDATION(400, "Business Validation");

    private val code: Int = statusCode
    private val reason: String = reasonPhrase
    override fun getStatusCode(): Int {
        return code
    }

    @Nonnull
    override fun getReasonPhrase(): String {
        return reason
    }

    override fun toString(): String {
        return this.statusCode.toString() + " " + this.reasonPhrase
    }

    companion object {
        private val STATUSES: Map<Int, Status> = values().associateBy { status: Status -> status.code }

        fun valueOf(code: Int): Status {
            return STATUSES[code] ?: throw IllegalArgumentException("There is no known status for this code ($code).")
        }
    }

}
