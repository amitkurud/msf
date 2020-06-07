/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.config.apidocs

import com.fasterxml.classmate.TypeResolver
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Pageable
import springfox.documentation.schema.TypeNameExtractor
import springfox.documentation.spring.web.plugins.Docket

/**
 * Register Springfox plugins.
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnBean(Docket::class)
@AutoConfigureAfter(SwaggerAutoConfig::class)
class SwaggerPluginRegistration {
    @Configuration
    @ConditionalOnClass(Pageable::class)
    class SpringPagePluginConfiguration {
        @Bean
        @ConditionalOnMissingBean
        fun pageableParameterBuilderPlugin(typeNameExtractor: TypeNameExtractor?,
                                           typeResolver: TypeResolver?): PageableParameterSwaggerPlugin {
            return PageableParameterSwaggerPlugin(typeNameExtractor!!, typeResolver!!)
        }
    }
}
