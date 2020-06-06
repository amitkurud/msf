/*
 * Copyright (c) 2020. Amit Kurud
 */

package com.amitkurud.config

import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.*

@Component
class SpringSecurityAuditorAware : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> = Optional.of(SYSTEM_ACCOUNT)
}
