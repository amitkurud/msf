/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.kafka.entities

class Event<T> {

    val eventType: EventType? = null

    val operation: Operation? = null

    var payload: T? = null
}