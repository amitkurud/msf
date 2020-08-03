/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud

import com.alibaba.fastjson.JSON
import com.amitkurud.base.domain.BaseEntity
import com.amitkurud.base.transfer.BaseDTO

/**
 * Declare println function so we don't have to call Println function as much as needed
 */
fun String.println() {
    println(this)
}

fun BaseEntity.toJsonString(): String {
    return JSON.toJSONString(this)
}

fun BaseDTO.toJsonString(): String {
    return JSON.toJSONString(this)
}