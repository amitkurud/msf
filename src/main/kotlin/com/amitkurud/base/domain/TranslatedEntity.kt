/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.base.domain

import javax.persistence.JoinColumn
import javax.persistence.MappedSuperclass
import javax.persistence.OneToMany

@MappedSuperclass
abstract class TranslatedEntity<T: TranslationEntity> : BaseEntity() {

    @OneToMany
    @JoinColumn(name = "entity_id",referencedColumnName = "id")
    var translations: MutableSet<T> = mutableSetOf()

}