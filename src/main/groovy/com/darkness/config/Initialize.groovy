package com.darkness.config


import  org.springframework.beans.factory.annotation.Autowired
import  org.springframework.context.annotation.Bean
import  org.springframework.context.annotation.Configuration
import  org.springframework.stereotype.Component
import  org.springframework.web.reactive.result.view.ViewResolver
import  org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import  org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
//import  org.thymeleaf.spring5.SpringTemplateEngine.org.thymeleaf.spring5.view.ThymeleafViewResolver
import  org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

import com.darkness.db.ItemsDB
import com.darkness.db.ItemsRepo
import com.darkness.db.MapDB
import com.darkness.db.MapRepo
import com.darkness.db.NpcDB
import com.darkness.db.NpcRepo
import com.darkness.db.UserRepo
import com.darkness.utils.DarknessUtils
import com.darkness.utils.DarknessConstants


@Configuration
class Initialize<config> {

    @Autowired
    UserRepo repository

    @Autowired
    MapRepo maprepo

    @Autowired
    DarknessUtils Methods

    @Autowired
    ItemsRepo itemsRepos

    @Autowired
    NpcRepo npcRepos

    //#############################################################################
    //##################### // method //
    //#############################################################################
    //#####################

    @Bean void initializeMapValues() { System.out.println("does this fire") + Methods.initializeItemValues() }

    @Bean void initializeItemValues() { Methods.initializeItemValues() }

    @Bean void initializeNpcValues() { Methods.initializeNpcValues() } }
