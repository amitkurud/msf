/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.base.transfer

abstract class TranslatedDTO<T : TranslationDTO> : BaseDTO() {

    var translations: MutableSet<T> = mutableSetOf()

}