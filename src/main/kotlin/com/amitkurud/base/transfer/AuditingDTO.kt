/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.base.transfer

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.time.ZonedDateTime
import javax.persistence.Column

abstract class AuditingDTO {

    @JsonIgnore
    var createdBy: String? = null

    @JsonIgnore
    var createdDate: Instant? = Instant.now()

    @JsonIgnore
    var lastModifiedBy: String? = null

    @JsonIgnore
    var lastModifiedDate: Instant? = Instant.now()
}