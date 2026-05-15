package com.techlab.servicios;

import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.pedidos.EstadoPedido;
import com.techlab.pedidos.LineaPedido;
import com.techlab.pedidos.Pedido;
import com.techlab.productos.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoService {

    private List<Pedido> pedidos = new ArrayList<>();

    public void crearPedido(ProductoService productoService, Scanner scanner) {
        Pedido pedido = new Pedido();
        System.out.print("¿Cuántos productos querés agregar al pedido? ");
        int cantidad = 0;
        try {
            cantidad = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Número inválido.");
            return;
        }

        for (int i = 0; i < cantidad; i++) {
            System.out.print("ID del producto: ");
            try {
                int idProducto = Integer.parseInt(scanner.nextLine());
                Producto p = productoService.buscarPorId(idProducto);
                if (p == null) {
                    System.out.println("Producto no encontrado. Se omite.");
                    continue;
                }
                System.out.print("Cantidad deseada: ");
                int cantDeseada = Integer.parseInt(scanner.nextLine());

                if (cantDeseada > p.getStock()) {
                    throw new StockInsuficienteException(p.getNombre(), p.getStock(), cantDeseada);
                }

                pedido.agregarLinea(new LineaPedido(p, cantDeseada));
                p.setStock(p.getStock() - cantDeseada);

            } catch (NumberFormatException e) {
                System.out.println("Valor inválido, se omite este producto.");
            } catch (StockInsuficienteException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        if (!pedido.getLineas().isEmpty()) {
            pedidos.add(pedido);
            System.out.println("Pedido creado exitosamente.");
            System.out.println(pedido);
        } else {
            System.out.println("El pedido estaba vacío, no se guardó.");
        }
    }

    // NUEVO: permite cambiar el estado de un pedido por su ID
    public void cambiarEstadoPedido(int idPedido, EstadoPedido nuevoEstado, ProductoService productoService) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                // Si se cancela, devuelve el stock
                if (nuevoEstado == EstadoPedido.CANCELADO && p.getEstado() != EstadoPedido.CANCELADO) {
                    for (LineaPedido linea : p.getLineas()) {
                        Producto prod = linea.getProducto();
                        prod.setStock(prod.getStock() + linea.getCantidad());
                    }
                    System.out.println("Stock restaurado por cancelación del pedido.");
                }
                p.setEstado(nuevoEstado);
                System.out.println("Estado actualizado a: " + nuevoEstado);
                return;
            }
        }
        System.out.println("Pedido no encontrado.");
    }

    public void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }
        System.out.println("\n--- PEDIDOS ---");
        for (Pedido p : pedidos) {
            System.out.println(p);
            System.out.println("---------------");
        }
    }
}