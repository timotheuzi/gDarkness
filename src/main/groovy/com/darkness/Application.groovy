package com.darkness

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
//@EnableWebFlux
class Application {

    static void main(String[] args) {
        SpringApplication.run(Application, args)
    }
}

