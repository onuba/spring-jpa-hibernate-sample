package org.onuba.example.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 408382790553524409L;

    /**
     * Logger de la aplicación, usando la libreria logback.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleException.class);

    public ExampleException(Exception e) {
        super(e);
        LOGGER.error(e.getMessage(), e);
    }

    public ExampleException(String msg) {
        super(msg);
        LOGGER.error(msg, this);
    }

}
