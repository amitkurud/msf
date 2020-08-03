/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.controller

import com.amitkurud.config.KafkaProperties
import io.swagger.annotations.Api
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.time.Duration
import java.time.Instant
import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@RestController
@RequestMapping("coreKafka")
@Api
class KafkaResource(
        private val kafkaProperties: KafkaProperties
) {

    private val log = LoggerFactory.getLogger(javaClass)
    private lateinit var producer: KafkaProducer<String, String>
    private lateinit var sseExecutorService: ExecutorService

    init {
        producer = KafkaProducer<String, String>(kafkaProperties.getProducerProps())
        sseExecutorService = Executors.newCachedThreadPool()
    }

    @PostMapping("/publish/{topic}")
    @Throws(*[ExecutionException::class, InterruptedException::class])
    fun publish(@PathVariable topic: String, @RequestParam message: String, @RequestParam(required = false) key: String?): PublishResult {
        log.debug("REST request to send to Kafka topic $topic with key $key the message : $message")
        val metadata = producer.send(ProducerRecord(topic, key, message)).get()
        return PublishResult(metadata.topic(), metadata.partition(), metadata.offset(), Instant.ofEpochMilli(metadata.timestamp()))
    }

    @GetMapping("/consume")
    fun consume(@RequestParam("topic") topics: List<String>, @RequestParam consumerParams: Map<String, String>): SseEmitter {
        log.debug("REST request to consume records from Kafka topics $topics")
        val consumerProps = kafkaProperties.getConsumerProps()
        consumerProps.putAll(consumerParams)
        consumerProps.remove("topic")

        val emitter = SseEmitter(0L)
        sseExecutorService.execute {
            val consumer = KafkaConsumer<String, String>(consumerProps)
            emitter.onCompletion(consumer::close)
            consumer.subscribe(topics)
            var exitLoop = false

            while (!exitLoop) {
                try {
                    val records = consumer.poll(Duration.ofSeconds(5L))
                    records.forEach { emitter.send(it.value()) }
                    emitter.send(SseEmitter.event().comment(""))
                } catch (ex: Exception) {
                    log.trace("Complete with error ${ex.message}", ex)
                    emitter.completeWithError(ex)
                    exitLoop = true
                }
            }
            consumer.close()
            emitter.complete()
        }
        return emitter
    }

    class PublishResult(
            val topic: String,
            val partition: Int,
            val offset: Long,
            val timestamp: Instant
    )
}