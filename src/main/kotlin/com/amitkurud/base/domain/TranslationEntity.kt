/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.base.domain

import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class TranslationEntity : BaseEntity() {

    @Column(name = "lang_cd",length = 2)
    var langCd: String = ""

    @Column(name = "description",length = 120)
    var description: String = ""
}