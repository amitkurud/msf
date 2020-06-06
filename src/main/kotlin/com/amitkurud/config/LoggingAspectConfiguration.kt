/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.config

import com.amitkurud.logging.LoggingAspect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Profile
import org.springframework.core.env.Environment


@Configuration
@EnableAspectJAutoProxy
class LoggingAspectConfiguration {

    @Bean
    @Profile(DEV_PROFILE)
    fun loggingAspect(env: Environment) = LoggingAspect(env)
}