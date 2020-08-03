/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.logging

import com.alibaba.fastjson.JSON
import com.amitkurud.config.DEV_PROFILE
import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.MeterRegistry
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment
import org.springframework.core.env.Profiles


@Suppress("unused")
@Aspect
open class LoggingAspect(private val env: Environment) {

    @Pointcut(
            "within(@org.springframework.stereotype.Repository *)" +
                    " || within(@org.springframework.stereotype.Service *)" +
                    " || within(@org.springframework.web.bind.annotation.RestController *)"
    )
    fun springBeanPointcut() =
            Unit // Method is empty as this is just a Pointcut, the implementations are in the advices.

    @Pointcut(
            "within(com.amitkurud..*)" +
                    " || within(com.amitkurud..*)" +
                    " || within(com.amitkurud..*)"
    )
    fun applicationPackagePointcut() =
            Unit // Method is empty as this is just a Pointcut, the implementations are in the advices.

    private fun logger(joinPoint: JoinPoint) = LoggerFactory.getLogger(joinPoint.signature.declaringTypeName)

    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    fun logAfterThrowing(joinPoint: JoinPoint, e: Throwable) {

        if (env.acceptsProfiles(Profiles.of(DEV_PROFILE))) {
            logger(joinPoint).error(
                    "Exception in {}() with cause = \'{}\' and exception = \'{}\'",
                    joinPoint.signature.name,
                    if (e.cause != null) e.cause else "NULL",
                    e.message, e
            )
        } else {
            logger(joinPoint).error(
                    "Exception in {}() with cause = {}", joinPoint.signature.name,
                    if (e.cause != null) e.cause else "NULL"
            )
        }
    }

    @Around(value = "applicationPackagePointcut() && springBeanPointcut()")
    fun logAround(joinPoint: ProceedingJoinPoint): Any? {
        val log = logger(joinPoint)
        if (log.isDebugEnabled) {
            log.debug(
                    "Enter: {}() with argument[s] = {}",
                    joinPoint.signature.name, joinPoint.args.map {
                if (it.javaClass.packageName.contains("com.amitkurud", false)) {
                    JSON.toJSON(it)
                } else {
                    it.toString()
                }
            }.joinToString()
            )
        }
        try {
            val result = joinPoint.proceed()
            if (log.isDebugEnabled) {
                log.debug(
                        "Exit: {}() with result = {}", joinPoint.signature.name, result
                )
            }
            return result
        } catch (e: IllegalArgumentException) {
            log.error(
                    "Illegal argument: {} in {}()", joinPoint.args.joinToString(),
                    joinPoint.signature.name
            )
            throw e
        }
    }

    @Bean
    fun timedAspect(registry: MeterRegistry?): TimedAspect {
        return TimedAspect(registry)
    }

}