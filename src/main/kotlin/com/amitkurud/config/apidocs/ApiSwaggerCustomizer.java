/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.config.apidocs;

import org.springframework.core.Ordered;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import springfox.documentation.spring.web.plugins.Docket;

import java.nio.ByteBuffer;

import static springfox.documentation.builders.PathSelectors.any;

public class ApiSwaggerCustomizer implements SwaggerCustomizer, Ordered {

    public static final int DEFAULT_ORDER = 0;

    private int order = DEFAULT_ORDER;

    @Override
    public void customize(Docket docket) {

        docket
                .useDefaultResponseMessages(true)
                .forCodeGeneration(true)
                .directModelSubstitute(ByteBuffer.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .ignoredParameterTypes(Pageable.class)
                .select()
                .paths(any())
                .build();
    }

    @Override
    public int getOrder() {
        return order;
    }
}