/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.base.domain

import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class TranslationEntity : BaseEntity() {

    var entityId: String = ""

    var langCd: String = ""

    var description: String = ""
}