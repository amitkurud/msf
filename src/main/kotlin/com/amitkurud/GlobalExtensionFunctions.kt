/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud

import kotlin.reflect.KClass

/**
 * Declare println function so we don't have to call Println function as much as needed
 */
fun String.println() {
    println(this)
}