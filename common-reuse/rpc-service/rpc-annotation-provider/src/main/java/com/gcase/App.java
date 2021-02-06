package com.gcase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example")
public class App {

    public static void main(String[] args) {
        ApplicationContext applicationContext=
                new AnnotationConfigApplicationContext(App.class);
    }
}

