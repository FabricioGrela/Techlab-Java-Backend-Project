package com.techlab.excepciones;

public class StockInsuficienteException extends TechlabException { // antes era Exception

    public StockInsuficienteException(String nombreProducto, int stockDisponible, int cantidadPedida) {
        super(String.format(
            "Stock insuficiente para '%s'. Disponible: %d, Pedido: %d",
            nombreProducto, stockDisponible, cantidadPedida
        ));
    }
}