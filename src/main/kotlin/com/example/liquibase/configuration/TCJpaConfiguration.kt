package com.example.liquibase.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(
    basePackages = ["com.example.liquibase.repository.db"]
)
class TCJpaConfiguration
