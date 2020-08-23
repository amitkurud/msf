/*
 * Copyright (c) 2020. Amit Kurud
 */
package com.amitkurud.base.mappers

import org.mapstruct.MapperConfig
import org.mapstruct.MappingInheritanceStrategy
import org.mapstruct.ReportingPolicy


@MapperConfig(
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG
)
interface BaseConfig