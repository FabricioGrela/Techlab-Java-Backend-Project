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
        // CAMBIO: filtra los inactivos
        List<Producto> activos = new ArrayList<>();
        for (Producto p : productos) {
            if (p.isActivo()) activos.add(p);
        }
        if (activos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        for (Producto p : activos) {
            System.out.println(p);
        }
    }

    public Producto buscarPorId(int id) {
        for (Producto p : productos) {
            // CAMBIO: solo busca entre activos
            if (p.getId() == id && p.isActivo()) return p;
        }
        return null;
    }

    public Producto buscarPorNombre(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre) && p.isActivo()) return p;
        }
        return null;
    }

    public boolean eliminar(int id) {
        Producto p = buscarPorId(id);
        if (p != null) {
            p.setActivo(false); // CAMBIO: borrado lógico, no físico
            return true;
        }
        return false;
    }

    public List<Producto> getProductos() {
        return productos;
    }
}