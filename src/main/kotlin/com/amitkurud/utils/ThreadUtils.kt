/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.utils

import org.springframework.util.Assert

/**
 * To Store and get the values in Thread Storage so it can be access Across Thread Execution
 */
object ThreadUtils {
    private val VALUES = ThreadLocal.withInitial<MutableMap<String, String>> { HashMap() }
    fun putValue(key: String, value: String) {
        Assert.notNull(key, "KEY Cannot be Empty")
        Assert.notNull(value, "VALUE Cannot be Empty")
        VALUES.get()[key] = value
    }

    fun getValue(key: String): String? {
        Assert.notNull(key, "KEY Cannot be Empty")
        return VALUES.get()[key]
    }
}