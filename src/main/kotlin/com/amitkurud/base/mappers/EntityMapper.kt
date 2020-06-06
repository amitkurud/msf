/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.base.mappers

interface EntityMapper<D, E> {

    fun toEntity(dto: D): E

    fun toDto(entity: E): D

    fun toEntity(dtoList: MutableList<D>): MutableList<E>

    fun toDto(entityList: MutableList<E>): MutableList<D>
}
