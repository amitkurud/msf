/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.base.transfer

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.ZonedDateTime
import javax.persistence.Column

abstract class AuditingDTO {

    var createdBy: String? = null

    var createdDate: ZonedDateTime? = ZonedDateTime.now()

    var lastModifiedBy: String? = null

    var lastModifiedDate: ZonedDateTime? = ZonedDateTime.now()
}