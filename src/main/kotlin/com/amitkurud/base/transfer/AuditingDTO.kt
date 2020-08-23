/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.base.transfer

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.Instant

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