package com.techlab.pedidos;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private static int contadorId = 1;

    private int id;
    private List<LineaPedido> lineas;
    private EstadoPedido estado; // NUEVO: usa el enum

    public Pedido() {
        this.id = contadorId++;
        this.lineas = new ArrayList<>();
        this.estado = EstadoPedido.PENDIENTE; // todo pedido arranca pendiente
    }

    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
    }

    public int getId()                  { return id; }
    public List<LineaPedido> getLineas(){ return lineas; }
    public EstadoPedido getEstado()     { return estado; } // NUEVO
    public void setEstado(EstadoPedido estado) { this.estado = estado; } // NUEVO

    public double calcularTotal() {
        double total = 0;
        for (LineaPedido linea : lineas) {
            total += linea.getSubtotal();
        }
        return total;
    }

    // NUEVO: total sin descuento para mostrar el ahorro
    public double calcularTotalSinDescuento() {
        double total = 0;
        for (LineaPedido linea : lineas) {
            total += linea.getPrecioOriginal() * linea.getCantidad();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Pedido #%d [%s]:\n", id, estado)); // muestra el estado
        for (LineaPedido linea : lineas) {
            sb.append(linea.toString()).append("\n");
        }
        double total = calcularTotal();
        double sinDescuento = calcularTotalSinDescuento();
        sb.append(String.format("  TOTAL: $%.2f", total));
        if (sinDescuento > total) {
            sb.append(String.format("  (Ahorraste: $%.2f)", sinDescuento - total)); // NUEVO
        }
        return sb.toString();
    }
}