/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.base.mappers

import com.amitkurud.base.domain.TranslationEntity
import com.amitkurud.base.transfer.TranslationDTO

interface TranslationMapper<D : TranslationDTO, E : TranslationEntity> : EntityMapper<D, E>