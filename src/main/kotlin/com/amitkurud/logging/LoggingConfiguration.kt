/*
 * Copyright (c) 2020. Amit Kurud
 */
package com.amitkurud.logging

import ch.qos.logback.classic.LoggerContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.info.BuildProperties
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.context.annotation.Configuration

@Configuration
@RefreshScope
class LoggingConfiguration(
    @Value("\${spring.application.name}") appName: String,
    @Value("\${server.port}") serverPort: String,
    buildProperties: BuildProperties?
) {
    init {
        val context = LoggerFactory.getILoggerFactory() as LoggerContext

        val map = mutableMapOf<String, String?>()
        map["app_name"] = appName
        map["app_port"] = serverPort
        buildProperties?.apply { map["version"] = this.version }
    }
}