/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.core.env.Profiles
import org.springframework.format.FormatterRegistry
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.method.HandlerTypePredicate
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.zalando.problem.ProblemModule


@EnableWebMvc
@Configuration
class WebConfig(val environment: Environment) : WebMvcConfigurer {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.apply {
            addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/")
            addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
        }
    }

    override fun addFormatters(registry: FormatterRegistry) {
        DateTimeFormatterRegistrar().apply {
            setUseIsoFormat(true)
            registerFormatters(registry)
        }
    }

    override fun configurePathMatch(configurer: PathMatchConfigurer) {
        configurer.addPathPrefix("api/v1/", HandlerTypePredicate.forAnnotation(RestController::class.java))
    }

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        if (!environment.acceptsProfiles(Profiles.of(DEV_PROFILE))) {
            val problemModule = ProblemModule()
            problemModule.withStackTraces(false)
            val mapper: ObjectMapper =
                Jackson2ObjectMapperBuilder.json().modules(JavaTimeModule(), problemModule).build()
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            converters.add(MappingJackson2HttpMessageConverter(mapper))
        } else {
            val mapper: ObjectMapper = Jackson2ObjectMapperBuilder.json().modules(JavaTimeModule()).build()
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            converters.add(MappingJackson2HttpMessageConverter(mapper))
        }
    }
}