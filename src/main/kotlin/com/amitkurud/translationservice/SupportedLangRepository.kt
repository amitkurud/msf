/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.translationservice

import org.springframework.data.jpa.repository.JpaRepository

interface SupportedLangRepository : JpaRepository<SupportedLang,String> {
}