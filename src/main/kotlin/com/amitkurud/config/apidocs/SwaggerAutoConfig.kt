/*
 * Copyright (c) 2020. Amit Kurud
 */
package com.amitkurud.config.apidocs

import com.google.common.base.Predicates
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.DispatcherServlet
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.nio.ByteBuffer
import java.util.*
import javax.servlet.Servlet

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass(
    ApiInfo::class,
    BeanValidatorPluginsConfiguration::class,
    Servlet::class,
    DispatcherServlet::class,
    Docket::class
)
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration::class)
class SwaggerAutoConfig {
    private val log = LoggerFactory.getLogger(SwaggerAutoConfig::class.java)

    @Autowired
    private val buildProperties: BuildProperties? = null

    @Bean
    @ConditionalOnMissingBean(name = ["swaggerSpringfoxManagementDocket"])
    fun swaggerSpringfoxManagementDocket(
        @Value("\${spring.application.name:application}") appName: String?,
        swaggerCustomizers: List<SwaggerCustomizer?>?
    ): Docket {
        val apiInfo = ApiInfo(
            StringUtils.capitalize(appName) + " " + MANAGEMENT_TITLE_SUFFIX,
            mavenBuildInfo,
            "0.0.1-SNAPSHOT",
            "",
            ApiInfo.DEFAULT_CONTACT,
            "",
            "",
            ArrayList()
        )
        return createDocket().apiInfo(apiInfo)
    }

    private val mavenBuildInfo: String
        private get() {
            if (buildProperties == null) {
                return ""
            }
            var mavenBuildInfo = ""
            if (buildProperties.version != null) {
                mavenBuildInfo += "Version - " + buildProperties.version + ";"
            }
            if (buildProperties.time != null) {
                mavenBuildInfo += " Last build time - " + buildProperties.time.toString() + ";"
            }
            return mavenBuildInfo
        }

    protected fun createDocket(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(true)
            .forCodeGeneration(true)
            .directModelSubstitute(ByteBuffer::class.java, String::class.java)
            .genericModelSubstitutes(ResponseEntity::class.java)
            .ignoredParameterTypes(Pageable::class.java)
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController::class.java))
            .paths(PathSelectors.any())
            .paths(Predicates.not(PathSelectors.regex("/error/*")))
            .build()
    }

    companion object {
        const val STARTING_MESSAGE = "Starting Swagger"
        const val STARTED_MESSAGE = "Started Swagger in {} ms"
        const val MANAGEMENT_TITLE_SUFFIX = "Management API"
        const val MANAGEMENT_GROUP_NAME = "management"
        const val MANAGEMENT_DESCRIPTION = "Management endpoints documentation"
    }
}