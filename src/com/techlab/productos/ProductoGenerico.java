package com.techlab.productos;

public class ProductoGenerico extends Producto {

    public ProductoGenerico(String nombre, double precio, int stock) {
        super(nombre, precio, stock);
    }

    @Override
    public String getDetalleTipo() {
        return "General";
    }
}