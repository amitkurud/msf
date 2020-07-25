/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.kafka

import com.amitkurud.config.KafkaProperties
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.listener.MessageListener
import org.springframework.stereotype.Service

@Service
class KafkaConsumer(@Autowired
                    val properties: KafkaProperties) {
}