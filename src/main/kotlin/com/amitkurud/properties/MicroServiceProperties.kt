/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.springframework.web.cors.CorsConfiguration
import java.util.*
import javax.validation.constraints.NotNull

@ConfigurationProperties(prefix = "msf", ignoreUnknownFields = false)
@PropertySources(PropertySource(value = ["classpath:git.properties"], ignoreResourceNotFound = true),
        PropertySource(value = ["classpath:META-INF/build-info.properties"], ignoreResourceNotFound = true))
class MicroServiceProperties {
    /**
     *
     * Getter for the field `async`.
     *
     * @return a [io.github.jhipster.config.JHipsterProperties.Async] object.
     */
    val async = Async()

    /**
     *
     * Getter for the field `http`.
     *
     * @return a [io.github.jhipster.config.JHipsterProperties.Http] object.
     */
    val http = Http()

    /**
     *
     * Getter for the field `cache`.
     *
     * @return a [io.github.jhipster.config.JHipsterProperties.Cache] object.
     */
    val cache = Cache()

    /**
     *
     * Getter for the field `mail`.
     *
     * @return a [io.github.jhipster.config.JHipsterProperties.Mail] object.
     */
    val mail = Mail()

    /**
     *
     * Getter for the field `security`.
     *
     * @return a [io.github.jhipster.config.JHipsterProperties.Security] object.
     */
    val security = Security()

    /**
     *
     * Getter for the field `swagger`.
     *
     * @return a [io.github.jhipster.config.JHipsterProperties.Swagger] object.
     */
    val swagger = Swagger()

    /**
     *
     * Getter for the field `metrics`.
     *
     * @return a [io.github.jhipster.config.JHipsterProperties.Metrics] object.
     */
    val metrics = Metrics()

    /**
     *
     * Getter for the field `logging`.
     *
     * @return a [io.github.jhipster.config.JHipsterProperties.Logging] object.
     */
    val logging = Logging()

    /**
     *
     * Getter for the field `cors`.
     *
     * @return a [org.springframework.web.cors.CorsConfiguration] object.
     */
    val cors = CorsConfiguration()

    /**
     *
     * Getter for the field `social`.
     *
     * @return a [io.github.jhipster.config.JHipsterProperties.Social] object.
     */
    val social = Social()

    /**
     *
     * Getter for the field `gateway`.
     *
     * @return a [io.github.jhipster.config.JHipsterProperties.Gateway] object.
     */
    val gateway = Gateway()

    /**
     *
     * Getter for the field `registry`.
     *
     * @return a [io.github.jhipster.config.JHipsterProperties.Registry] object.
     */
    val registry = Registry()

    /**
     *
     * Getter for the field `clientApp`.
     *
     * @return a [io.github.jhipster.config.JHipsterProperties.ClientApp] object.
     */
    val clientApp = ClientApp()

    /**
     *
     * Getter for the field `auditEvents`.
     *
     * @return a [io.github.jhipster.config.JHipsterProperties.AuditEvents] object.
     */
    val auditEvents = AuditEvents()

    class Async {
        var corePoolSize: Int = PropertiesDefaults.Async.corePoolSize
        var maxPoolSize: Int = PropertiesDefaults.Async.maxPoolSize
        var queueCapacity: Int = PropertiesDefaults.Async.queueCapacity

    }

    class Http {
        val cache = Cache()

        class Cache {
            var timeToLiveInDays: Int = PropertiesDefaults.Http.Cache.timeToLiveInDays

        }
    }

    class Cache {
        val hazelcast = Hazelcast()
        val caffeine = Caffeine()
        val ehcache = Ehcache()
        val infinispan = Infinispan()
        val memcached = Memcached()
        val redis = Redis()

        class Hazelcast {
            var timeToLiveSeconds: Int = PropertiesDefaults.Cache.Hazelcast.timeToLiveSeconds
            var backupCount: Int = PropertiesDefaults.Cache.Hazelcast.backupCount
            val managementCenter = ManagementCenter()

            class ManagementCenter {
                var isEnabled: Boolean = PropertiesDefaults.Cache.Hazelcast.ManagementCenter.enabled
                var updateInterval: Int = PropertiesDefaults.Cache.Hazelcast.ManagementCenter.updateInterval
                var url: String = PropertiesDefaults.Cache.Hazelcast.ManagementCenter.url

            }

        }

        class Caffeine {
            var timeToLiveSeconds: Int = PropertiesDefaults.Cache.Caffeine.timeToLiveSeconds
            var maxEntries: Long = PropertiesDefaults.Cache.Caffeine.maxEntries

        }

        class Ehcache {
            var timeToLiveSeconds: Int = PropertiesDefaults.Cache.Ehcache.timeToLiveSeconds
            var maxEntries: Long = PropertiesDefaults.Cache.Ehcache.maxEntries

        }

        class Infinispan {
            var configFile: String = PropertiesDefaults.Cache.Infinispan.configFile
            var isStatsEnabled: Boolean = PropertiesDefaults.Cache.Infinispan.statsEnabled
            val local = Local()
            val distributed = Distributed()
            val replicated = Replicated()

            class Local {
                var timeToLiveSeconds: Long = PropertiesDefaults.Cache.Infinispan.Local.timeToLiveSeconds
                var maxEntries: Long = PropertiesDefaults.Cache.Infinispan.Local.maxEntries

            }

            class Distributed {
                var timeToLiveSeconds: Long = PropertiesDefaults.Cache.Infinispan.Distributed.timeToLiveSeconds
                var maxEntries: Long = PropertiesDefaults.Cache.Infinispan.Distributed.maxEntries
                var instanceCount: Int = PropertiesDefaults.Cache.Infinispan.Distributed.instanceCount

            }

            class Replicated {
                var timeToLiveSeconds: Long = PropertiesDefaults.Cache.Infinispan.Replicated.timeToLiveSeconds
                var maxEntries: Long = PropertiesDefaults.Cache.Infinispan.Replicated.maxEntries

            }
        }

        class Memcached {
            var isEnabled: Boolean = PropertiesDefaults.Cache.Memcached.enabled

            /**
             * Comma or whitespace separated list of servers' addresses.
             */
            var servers: String = PropertiesDefaults.Cache.Memcached.servers
            var expiration: Int = PropertiesDefaults.Cache.Memcached.expiration
            var isUseBinaryProtocol: Boolean = PropertiesDefaults.Cache.Memcached.useBinaryProtocol

        }

        class Redis {
            var server: Array<String> = PropertiesDefaults.Cache.Redis.server
            var expiration: Int = PropertiesDefaults.Cache.Redis.expiration
            var isCluster: Boolean = PropertiesDefaults.Cache.Redis.cluster

        }
    }

    class Mail {
        var isEnabled: Boolean = PropertiesDefaults.Mail.enabled
        var from: String = PropertiesDefaults.Mail.from
        var baseUrl: String = PropertiesDefaults.Mail.baseUrl

    }

    class Security {
        val clientAuthorization = ClientAuthorization()
        val authentication = Authentication()
        val rememberMe = RememberMe()
        val oauth2 = OAuth2()

        class ClientAuthorization {
            var accessTokenUri: String? = PropertiesDefaults.Security.ClientAuthorization.accessTokenUri
            var tokenServiceId: String? = PropertiesDefaults.Security.ClientAuthorization.tokenServiceId
            var clientId: String? = PropertiesDefaults.Security.ClientAuthorization.clientId
            var clientSecret: String? = PropertiesDefaults.Security.ClientAuthorization.clientSecret

        }

        class Authentication {
            val jwt = Jwt()

            class Jwt {
                var secret: String? = PropertiesDefaults.Security.Authentication.Jwt.secret
                var base64Secret: String? = PropertiesDefaults.Security.Authentication.Jwt.base64Secret
                var tokenValidityInSeconds: Long = PropertiesDefaults.Security.Authentication.Jwt.tokenValidityInSeconds
                var tokenValidityInSecondsForRememberMe: Long = PropertiesDefaults.Security.Authentication.Jwt.tokenValidityInSecondsForRememberMe

            }
        }

        class RememberMe {
            var key: @NotNull String? = PropertiesDefaults.Security.RememberMe.key

        }

        class OAuth2 {
            private val audience: MutableList<String> = ArrayList()
            fun getAudience(): List<String> {
                return Collections.unmodifiableList(audience)
            }

            fun setAudience(audience: @NotNull MutableList<String>?) {
                this.audience.addAll(audience!!)
            }
        }
    }

    class Swagger {
        var title: String = PropertiesDefaults.Swagger.title
        var description: String = PropertiesDefaults.Swagger.description
        var version: String = PropertiesDefaults.Swagger.version
        var termsOfServiceUrl: String? = PropertiesDefaults.Swagger.termsOfServiceUrl
        var contactName: String? = PropertiesDefaults.Swagger.contactName
        var contactUrl: String? = PropertiesDefaults.Swagger.contactUrl
        var contactEmail: String? = PropertiesDefaults.Swagger.contactEmail
        var license: String? = PropertiesDefaults.Swagger.license
        var licenseUrl: String? = PropertiesDefaults.Swagger.licenseUrl
        var defaultIncludePattern: String = PropertiesDefaults.Swagger.defaultIncludePattern
        var host: String? = PropertiesDefaults.Swagger.host
        var protocols: Array<String> = PropertiesDefaults.Swagger.protocols
        var isUseDefaultResponseMessages: Boolean = PropertiesDefaults.Swagger.useDefaultResponseMessages

    }

    class Metrics {
        val logs = Logs()

        class Logs {
            var isEnabled: Boolean = PropertiesDefaults.Metrics.Logs.enabled
            var reportFrequency: Long = PropertiesDefaults.Metrics.Logs.reportFrequency

        }
    }

    class Logging {
        var isUseJsonFormat: Boolean = PropertiesDefaults.Logging.useJsonFormat
        val logstash = Logstash()

        class Logstash {
            var isEnabled: Boolean = PropertiesDefaults.Logging.Logstash.enabled
            var host: String = PropertiesDefaults.Logging.Logstash.host
            var port: Int = PropertiesDefaults.Logging.Logstash.port
            var queueSize: Int = PropertiesDefaults.Logging.Logstash.queueSize

        }
    }

    class Social {
        var redirectAfterSignIn: String = PropertiesDefaults.Social.redirectAfterSignIn

    }

    class Gateway {
        val rateLimiting = RateLimiting()

        var authorizedMicroservicesEndpoints: Map<String, List<String>> = PropertiesDefaults.Gateway.authorizedMicroservicesEndpoints

        class RateLimiting {
            var isEnabled: Boolean = PropertiesDefaults.Gateway.RateLimiting.enabled
            var limit: Long = PropertiesDefaults.Gateway.RateLimiting.limit
            var durationInSeconds: Int = PropertiesDefaults.Gateway.RateLimiting.durationInSeconds

        }
    }

    class Registry {
        var password: String? = PropertiesDefaults.Registry.password
    }

    class ClientApp {
        var name: String? = PropertiesDefaults.ClientApp.name

    }

    class AuditEvents {
        var retentionPeriod: Int = PropertiesDefaults.AuditEvents.retentionPeriod

    }
}
