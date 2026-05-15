package com.techlab.pedidos;

import com.techlab.productos.Producto;

public class LineaPedido {

    private Producto producto;
    private int cantidad;
    private double precioAplicado; // NUEVO: precio con descuento ya aplicado

    public LineaPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioAplicado = calcularPrecioConDescuento(producto.getPrecio(), cantidad); // NUEVO
    }

    // NUEVO: lógica de descuento por cantidad
    private double calcularPrecioConDescuento(double precioOriginal, int cantidad) {
        if (cantidad >= 10) return precioOriginal * 0.80; // 20% off
        if (cantidad >= 5)  return precioOriginal * 0.90; // 10% off
        return precioOriginal;
    }

    public Producto getProducto()      { return producto; }
    public int getCantidad()           { return cantidad; }
    public double getPrecioAplicado()  { return precioAplicado; } // NUEVO
    public double getPrecioOriginal()  { return producto.getPrecio(); } // NUEVO

    public double getSubtotal() {
        return precioAplicado * cantidad; // usa precio con descuento
    }

    @Override
    public String toString() {
        String descuento = "";
        if (cantidad >= 10) descuento = " (-20%)";
        else if (cantidad >= 5) descuento = " (-10%)";
        return String.format("  - %s x%d%s = $%.2f",
                producto.getNombre(), cantidad, descuento, getSubtotal());
    }
}