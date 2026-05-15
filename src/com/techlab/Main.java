package com.techlab;

import com.techlab.pedidos.EstadoPedido;
import com.techlab.productos.Bebida;
import com.techlab.productos.Comida;
import com.techlab.productos.ProductoGenerico;
import com.techlab.servicios.PedidoService;
import com.techlab.servicios.ProductoService;

import java.util.Scanner;

public class Main {

    // NUEVO: colores ANSI
    static final String VERDE  = "\u001B[32m";
    static final String ROJO   = "\u001B[31m";
    static final String AMARILLO = "\u001B[33m";
    static final String RESET  = "\u001B[0m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductoService productoService = new ProductoService();
        PedidoService pedidoService = new PedidoService();
        boolean salir = false;

        while (!salir) {
            System.out.println("\n========== SISTEMA DE GESTIÓN - TECHLAB ==========");
            System.out.println("1) Agregar producto");
            System.out.println("2) Listar productos");
            System.out.println("3) Buscar/Actualizar producto");
            System.out.println("4) Eliminar producto");
            System.out.println("5) Crear pedido");
            System.out.println("6) Listar pedidos");
            System.out.println("7) Actualizar estado de pedido");
            System.out.println("8) Salir");
            System.out.print("Elegí una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> agregarProducto(scanner, productoService);
                case "2" -> productoService.listar();
                case "3" -> buscarActualizar(scanner, productoService);
                case "4" -> eliminarProducto(scanner, productoService);
                case "5" -> pedidoService.crearPedido(productoService, scanner);
                case "6" -> pedidoService.listarPedidos();
                case "7" -> actualizarEstadoPedido(scanner, pedidoService, productoService);
                case "8" -> { salir = true; System.out.println(VERDE + "¡Hasta luego!" + RESET); }
                default  -> System.out.println(ROJO + "Opción inválida." + RESET);
            }
        }
        scanner.close();
    }

    static void agregarProducto(Scanner scanner, ProductoService service) {
        try {
            System.out.println("Tipo: 1) Genérico  2) Bebida  3) Comida  (x para cancelar)");
            String tipo = scanner.nextLine();
            if (tipo.equalsIgnoreCase("x")) { System.out.println(AMARILLO + "Operación cancelada." + RESET); return; }

            System.out.print("Nombre (x para cancelar): ");
            String nombre = scanner.nextLine();
            if (nombre.equalsIgnoreCase("x")) { System.out.println(AMARILLO + "Operación cancelada." + RESET); return; }

            System.out.print("Precio: ");
            double precio = Double.parseDouble(scanner.nextLine());
            System.out.print("Stock: ");
            int stock = Integer.parseInt(scanner.nextLine());

            switch (tipo) {
                case "2" -> {
                    System.out.print("Volumen en litros: ");
                    double volumen = Double.parseDouble(scanner.nextLine());
                    service.agregar(new Bebida(nombre, precio, stock, volumen));
                }
                case "3" -> {
                    System.out.print("Fecha de vencimiento (dd/mm/aaaa): ");
                    String fecha = scanner.nextLine();
                    service.agregar(new Comida(nombre, precio, stock, fecha));
                }
                default -> service.agregar(new ProductoGenerico(nombre, precio, stock)); // CAMBIO
            }
            System.out.println(VERDE + "Producto agregado correctamente." + RESET); // NUEVO
        } catch (NumberFormatException e) {
            System.out.println(ROJO + "Error: ingresaste un valor no numérico." + RESET);
        }
    }

    static void buscarActualizar(Scanner scanner, ProductoService service) {
        System.out.print("Buscar por: 1) ID  2) Nombre (x para cancelar) → ");
        String modo = scanner.nextLine();
        if (modo.equalsIgnoreCase("x")) { System.out.println(AMARILLO + "Operación cancelada." + RESET); return; }

        com.techlab.productos.Producto p = null;
        try {
            if (modo.equals("1")) {
                System.out.print("ID: ");
                p = service.buscarPorId(Integer.parseInt(scanner.nextLine()));
            } else {
                System.out.print("Nombre: ");
                p = service.buscarPorNombre(scanner.nextLine());
            }
        } catch (NumberFormatException e) {
            System.out.println(ROJO + "ID inválido." + RESET);
            return;
        }

        if (p == null) { System.out.println(ROJO + "Producto no encontrado." + RESET); return; }

        System.out.println("Encontrado: " + p);
        System.out.println("¿Qué querés actualizar? 1) Precio  2) Stock  3) Nada");
        String accion = scanner.nextLine();

        try {
            if (accion.equals("1")) {
                System.out.print("Nuevo precio: ");
                double nuevoPrecio = Double.parseDouble(scanner.nextLine());
                if (nuevoPrecio < 0) { System.out.println(ROJO + "El precio no puede ser negativo." + RESET); return; }
                p.setPrecio(nuevoPrecio);
                System.out.println(VERDE + "Precio actualizado." + RESET);
            } else if (accion.equals("2")) {
                System.out.print("Nuevo stock: ");
                int nuevoStock = Integer.parseInt(scanner.nextLine());
                if (nuevoStock < 0) { System.out.println(ROJO + "El stock no puede ser negativo." + RESET); return; }
                p.setStock(nuevoStock);
                System.out.println(VERDE + "Stock actualizado." + RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(ROJO + "Valor inválido." + RESET);
        }
    }

    static void eliminarProducto(Scanner scanner, ProductoService service) {
        try {
            System.out.print("ID del producto a eliminar (x para cancelar): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("x")) { System.out.println(AMARILLO + "Operación cancelada." + RESET); return; }

            int id = Integer.parseInt(input);
            com.techlab.productos.Producto p = service.buscarPorId(id);
            if (p == null) { System.out.println(ROJO + "Producto no encontrado." + RESET); return; }

            System.out.print("¿Confirmás la eliminación de '" + p.getNombre() + "'? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                service.eliminar(id); // ahora hace borrado lógico
                System.out.println(VERDE + "Producto desactivado correctamente." + RESET);
            } else {
                System.out.println(AMARILLO + "Eliminación cancelada." + RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(ROJO + "ID inválido." + RESET);
        }
    }

    static void actualizarEstadoPedido(Scanner scanner, PedidoService pedidoService, ProductoService productoService) {
    try {
        System.out.print("ID del pedido a actualizar (x para cancelar): ");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("x")) { System.out.println(AMARILLO + "Operación cancelada." + RESET); return; }

        int id = Integer.parseInt(input);
        System.out.println("Nuevo estado: 1) PENDIENTE  2) ENTREGADO  3) CANCELADO");
        String opcion = scanner.nextLine();

        EstadoPedido nuevoEstado = switch (opcion) {
            case "1" -> EstadoPedido.PENDIENTE;
            case "2" -> EstadoPedido.ENTREGADO;
            case "3" -> EstadoPedido.CANCELADO;
            default  -> null;
        };

        if (nuevoEstado == null) { System.out.println(ROJO + "Opción inválida." + RESET); return; }

        pedidoService.cambiarEstadoPedido(id, nuevoEstado, productoService);
        System.out.println(VERDE + "Estado actualizado correctamente." + RESET);

    } catch (NumberFormatException e) {
        System.out.println(ROJO + "ID inválido." + RESET);
    }
    }

}