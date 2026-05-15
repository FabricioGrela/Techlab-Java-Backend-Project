package com.techlab.excepciones;

public class TechlabException extends RuntimeException {

    public TechlabException(String mensaje) {
        super(mensaje);
    }
}