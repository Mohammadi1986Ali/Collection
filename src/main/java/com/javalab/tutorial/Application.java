package com.javalab.tutorial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOGGER.info("Application#main");
    }
}
