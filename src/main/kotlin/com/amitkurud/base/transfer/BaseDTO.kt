/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.base.transfer

import java.time.Instant

open class BaseDTO : AuditingDTO() {

    var id: String? = null

    val who: String?
         get() = if (super.lastModifiedBy == null) super.createdBy else lastModifiedBy

    val time: Instant?
        get() = if (super.lastModifiedDate == null) super.createdDate else super.lastModifiedDate

}