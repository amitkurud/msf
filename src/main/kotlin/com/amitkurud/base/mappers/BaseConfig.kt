/*
 * Copyright (c) 2020. Amit Kurud
 */
package com.amitkurud.base.mappers

import com.amitkurud.base.domain.BaseEntity
import com.amitkurud.base.transfer.BaseDTO
import org.mapstruct.*


@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG
)
interface BaseConfig {
}