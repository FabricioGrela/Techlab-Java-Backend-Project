package com.techlab.excepciones;

public class ProductoNoEncontradoException extends TechlabException {

    public ProductoNoEncontradoException(int id) {
        super("No se encontró ningún producto con ID: " + id);
    }
}