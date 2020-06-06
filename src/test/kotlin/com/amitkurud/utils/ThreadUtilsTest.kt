/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ThreadUtilsTest{

    @Test
    public fun testPutValue(){
        ThreadUtils.putValue("TEST","VALUE")
        assertNotNull(ThreadUtils.getValue("TEST"))
    }

}