/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.utils

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

internal class ThreadUtilsTest {

    @Test
    fun testPutValue() {
        ThreadUtils.putValue("TEST", "VALUE")
        assertNotNull(ThreadUtils.getValue("TEST"))
    }

}