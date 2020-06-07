/*
 * Copyright (c) 2020. Amit Kurud
 */
package com.amitkurud.config.apidocs

import springfox.documentation.spring.web.plugins.Docket

@FunctionalInterface
interface SwaggerCustomizer {
    /**
     * Customize the Springfox Docket.
     *
     * @param docket the Docket to customize
     */
    fun customize(docket: Docket?)
}