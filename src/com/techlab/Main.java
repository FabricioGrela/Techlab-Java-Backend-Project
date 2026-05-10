package com.techlab;

import com.techlab.productos.Bebida;
import com.techlab.productos.Comida;
import com.techlab.productos.Producto;
import com.techlab.servicios.PedidoService;
import com.techlab.servicios.ProductoService;

import java.util.Scanner;

public class Main {

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
            System.out.println("7) Salir");
            System.out.print("Elegí una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> agregarProducto(scanner, productoService);
                case "2" -> productoService.listar();
                case "3" -> buscarActualizar(scanner, productoService);
                case "4" -> eliminarProducto(scanner, productoService);
                case "5" -> pedidoService.crearPedido(productoService, scanner);
                case "6" -> pedidoService.listarPedidos();
                case "7" -> { salir = true; System.out.println("¡Hasta luego!"); }
                default  -> System.out.println("Opción inválida.");
            }
        }
        scanner.close();
    }

    static void agregarProducto(Scanner scanner, ProductoService service) {
        try {
            System.out.println("Tipo: 1) Genérico  2) Bebida  3) Comida");
            String tipo = scanner.nextLine();

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
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
                default -> service.agregar(new Producto(nombre, precio, stock));
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: ingresaste un valor no numérico.");
        }
    }

    static void buscarActualizar(Scanner scanner, ProductoService service) {
        System.out.print("Buscar por: 1) ID  2) Nombre → ");
        String modo = scanner.nextLine();
        Producto p = null;

        try {
            if (modo.equals("1")) {
                System.out.print("ID: ");
                p = service.buscarPorId(Integer.parseInt(scanner.nextLine()));
            } else {
                System.out.print("Nombre: ");
                p = service.buscarPorNombre(scanner.nextLine());
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        if (p == null) { System.out.println("Producto no encontrado."); return; }

        System.out.println("Encontrado: " + p);
        System.out.println("¿Qué querés actualizar? 1) Precio  2) Stock  3) Nada");
        String accion = scanner.nextLine();

        try {
            if (accion.equals("1")) {
                System.out.print("Nuevo precio: ");
                double nuevoPrecio = Double.parseDouble(scanner.nextLine());
                if (nuevoPrecio < 0) { System.out.println("El precio no puede ser negativo."); return; }
                p.setPrecio(nuevoPrecio);
                System.out.println("Precio actualizado.");
            } else if (accion.equals("2")) {
                System.out.print("Nuevo stock: ");
                int nuevoStock = Integer.parseInt(scanner.nextLine());
                if (nuevoStock < 0) { System.out.println("El stock no puede ser negativo."); return; }
                p.setStock(nuevoStock);
                System.out.println("Stock actualizado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido.");
        }
    }

    static void eliminarProducto(Scanner scanner, ProductoService service) {
        try {
            System.out.print("ID del producto a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());
            Producto p = service.buscarPorId(id);
            if (p == null) { System.out.println("Producto no encontrado."); return; }
            System.out.print("¿Confirmás la eliminación de '" + p.getNombre() + "'? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                service.eliminar(id);
                System.out.println("Producto eliminado.");
            } else {
                System.out.println("Eliminación cancelada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        }
    }
}