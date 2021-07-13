package com.darkness.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import com.darkness.utils.DarknessUtils

@Configuration
class ConfigApplication<config> {

    // ##################################################################################################
    // method Bean
    // ##################################################################################################

    @Bean
    static DarknessUtils methods() {
        DarknessUtils methods = new DarknessUtils()

        return methods // rtest
    }

}