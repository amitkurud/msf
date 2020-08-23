/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.translationservice

import com.amitkurud.base.domain.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity

@Entity(name = "supported_lang")
class SupportedLang : BaseEntity() {
    /**
     * ISO 639-1 code for a language
     */
    @Column(name = "lang_code")
    val code: String = ""

    @Column(name = "db_name")
    val dbName: String = ""
}