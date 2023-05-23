package com.example.vintedandroid.config

import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration
import org.springframework.context.annotation.Bean

class ModelMapper {
    @Bean
    fun modelMapper(): ModelMapper {
        val modelMapper = ModelMapper()
        modelMapper.configuration
            .setFieldMatchingEnabled(true)
            .fieldAccessLevel = Configuration.AccessLevel.PRIVATE
        return modelMapper
    }
}