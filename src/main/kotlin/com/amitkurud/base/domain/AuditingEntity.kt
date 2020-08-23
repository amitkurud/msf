/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.base.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AuditingEntity {

    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    @JsonIgnore
    var createdBy: String? = null

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    @JsonIgnore
    var createdDate: Instant? = Instant.now()

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    @JsonIgnore
    var lastModifiedBy: String? = null

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @JsonIgnore
    var lastModifiedDate: Instant? = Instant.now()
}