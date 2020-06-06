/*
 * Copyright (c) 2020. Amit Kurud
 */
package com.amitkurud.controller

import com.amitkurud.base.mappers.BaseConfig
import com.amitkurud.base.mappers.EntityMapper
import org.mapstruct.Mapper

@Mapper(componentModel = "spring", uses = [])
interface UserMapper : EntityMapper<UserDTO, User>{
    override fun toEntity(dto: UserDTO): User
    override fun toDto(entity: User): UserDTO
}