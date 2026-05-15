package com.techlab.productos;

public abstract class Producto {

    private static int contadorId = 1;

    private int id;
    private String nombre;
    private double precio;
    private int stock;
    private boolean activo = true; // NUEVO: borrado lógico

    public Producto(String nombre, double precio, int stock) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Método abstracto: cada subclase DEBE implementarlo
    public abstract String getDetalleTipo();

    // Getters
    public int getId()        { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock()     { return stock; }
    public boolean isActivo() { return activo; } // NUEVO

    // Setters
    public void setNombre(String nombre)  { this.nombre = nombre; }
    public void setPrecio(double precio)  { this.precio = precio; }
    public void setStock(int stock)       { this.stock = stock; }
    public void setActivo(boolean activo) { this.activo = activo; } // NUEVO

    @Override
    public String toString() {
        String estado = activo ? "" : " [INACTIVO]"; // NUEVO
        return String.format("[ID: %d] %s - $%.2f - Stock: %d%s",
                id, nombre, precio, stock, estado);
    }
}