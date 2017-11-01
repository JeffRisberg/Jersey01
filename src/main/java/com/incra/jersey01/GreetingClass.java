package com.incra.jersey01;

import javax.inject.Singleton;

@Singleton
public class GreetingClass {
    private static final String greeting = "Greetings";

    public String getGreeting() {
        return greeting;
    }
}
