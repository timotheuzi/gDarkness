package com.darkness

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Application {

    static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class)
        System.out.print("Muahahahahahaha - Starting darkness with Args: [")
        for (String s : args) {
            System.out.print(s + " ")
        }
        System.out.println("]")
        app.run(args)
    }
}

