/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.base.domain

import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.*

@MappedSuperclass
abstract class TranslatedEntity<T : TranslationEntity> : BaseEntity() {

    @OneToMany(cascade = [CascadeType.ALL],orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", referencedColumnName = "id")
    @Fetch(FetchMode.JOIN)
    var translations: MutableSet<T> = mutableSetOf()

}