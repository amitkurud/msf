/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.base.service

import com.amitkurud.base.domain.BaseEntity
import com.amitkurud.base.mappers.EntityMapper
import com.amitkurud.base.transfer.BaseDTO
import org.springframework.data.jpa.repository.JpaRepository

abstract class BaseCrudService<D : BaseDTO, E : BaseEntity, R : JpaRepository<E, String>, M : EntityMapper<D, E>>(private val repository: R, private val mapper: M) {
    fun create(dto: D): D {
        return mapper.toDto(repository.save(mapper.toEntity(dto)))
    }

    fun create(entity: E) = repository.save(entity)

    fun update(entity: E) = repository.save(entity)

    fun update(dto: D): D {
        return mapper.toDto(repository.save(mapper.toEntity(dto)))
    }

    fun delete(id: String) {
        repository.deleteById(id)
    }

    fun delete(ids: List<String>) {
        repository.deleteInBatch(repository.findAllById(ids))
    }
}