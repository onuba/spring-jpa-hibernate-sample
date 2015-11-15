package org.onuba.example.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleRuntimeException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 8008223412390938912L;

    /**
     * Logger de la aplicación, usando la libreria logback.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleRuntimeException.class);

    public ExampleRuntimeException(Exception e) {
        super(e);
        LOGGER.error(e.getMessage(), e);
    }

    public ExampleRuntimeException(String msg) {
        super(msg);
        LOGGER.error(msg, this);
    }
}
