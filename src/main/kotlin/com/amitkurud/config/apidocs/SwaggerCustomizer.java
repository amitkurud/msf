/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.config.apidocs;


import springfox.documentation.spring.web.plugins.Docket;

@FunctionalInterface
public interface SwaggerCustomizer {

    /**
     * Customize the Springfox Docket.
     *
     * @param docket the Docket to customize
     */
    void customize(Docket docket);
}