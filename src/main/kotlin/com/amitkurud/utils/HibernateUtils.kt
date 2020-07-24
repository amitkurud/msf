/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.utils

import org.hibernate.proxy.HibernateProxy

/**
 * Hibernate Utils
 */
object HibernateUtils {

    @Throws(ClassCastException::class)
    fun <T> unProxy(objectUnProxy: Any?, objectClass: Class<T>): T {
        return if (objectUnProxy is HibernateProxy) {
            objectClass.cast(objectUnProxy.hibernateLazyInitializer.implementation)
        } else {
            objectClass.cast(objectUnProxy)
        }
    }
}