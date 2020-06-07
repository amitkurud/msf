/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.base.domain

import org.hibernate.annotations.GenericGenerator
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity : AuditingEntity() {
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", columnDefinition = "CHAR(32)")
    @Id
    var id: String? = null
}