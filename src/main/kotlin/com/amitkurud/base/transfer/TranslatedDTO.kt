/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.base.transfer

import javax.persistence.JoinColumn
import javax.persistence.MappedSuperclass
import javax.persistence.OneToMany

abstract class TranslatedDTO<T: TranslationDTO> : BaseDTO() {

    var translations: MutableSet<T> = mutableSetOf()

}