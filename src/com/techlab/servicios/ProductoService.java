package com.techlab.servicios;

import com.techlab.productos.Producto;
import java.util.ArrayList;
import java.util.List;

public class ProductoService {

    private List<Producto> productos = new ArrayList<>();

    public void agregar(Producto p) {
        productos.add(p);
        System.out.println("Producto agregado: " + p.getNombre());
    }

    public void listar() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    public Producto buscarPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public Producto buscarPorNombre(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) return p;
        }
        return null;
    }

    public boolean eliminar(int id) {
        Producto p = buscarPorId(id);
        if (p != null) {
            productos.remove(p);
            return true;
        }
        return false;
    }

    public List<Producto> getProductos() {
        return productos;
    }
}