<div align="center">

# 🛒 TechLab — Sistema de Gestión de Productos

![Java](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=java)
![Status](https://img.shields.io/badge/Status-Completed-brightgreen?style=for-the-badge)
![OOP](https://img.shields.io/badge/Paradigm-OOP-blue?style=for-the-badge)

</div>

---

## 🇦🇷 Español

### 📄 Descripción

Sistema de gestión de productos y pedidos desarrollado en **Java puro** como proyecto integrador de un curso de backend. Permite registrar productos, gestionar stock y crear pedidos con validación de inventario en tiempo real, todo desde una interfaz de consola interactiva.

### 🎯 Funcionalidades

- ✅ Alta, baja y modificación de productos
- ✅ Productos especializados: **Bebida** (volumen) y **Comida** (fecha de vencimiento)
- ✅ Búsqueda por ID o nombre
- ✅ Creación de pedidos con descuento automático de stock
- ✅ Validación de stock con excepción personalizada
- ✅ Menú interactivo de consola

### 🏗️ Arquitectura y conceptos aplicados

```
techlab/
└── src/com/techlab/
    ├── Main.java                          # Menú principal
    ├── productos/
    │   ├── Producto.java                  # Clase base
    │   ├── Bebida.java                    # Herencia
    │   └── Comida.java                    # Herencia
    ├── pedidos/
    │   ├── Pedido.java                    # Entidad pedido
    │   └── LineaPedido.java               # Ítem de pedido
    ├── excepciones/
    │   └── StockInsuficienteException.java # Excepción personalizada
    └── servicios/
        ├── ProductoService.java           # Lógica de negocio
        └── PedidoService.java             # Lógica de pedidos
```

| Concepto | Implementación |
|---|---|
| Encapsulamiento | Getters/Setters en todas las entidades |
| Herencia | `Bebida` y `Comida` extienden `Producto` |
| Polimorfismo | `toString()` sobreescrito por subclase |
| Colecciones | `ArrayList<Producto>`, `ArrayList<Pedido>` |
| Excepciones | `try/catch`, `NumberFormatException`, excepción custom |
| Paquetes | Organización en capas lógicas |

### ▶️ Cómo ejecutar

**Requisitos:** Java 17 o superior

```bash
# Clonar el repositorio
git clone https://github.com/TU_USUARIO/techlab-gestion-productos.git
cd techlab-gestion-productos

# Compilar
javac -d out src/com/techlab/excepciones/*.java \
             src/com/techlab/productos/*.java \
             src/com/techlab/pedidos/*.java \
             src/com/techlab/servicios/*.java \
             src/com/techlab/Main.java

# Ejecutar
java -cp out com.techlab.Main
```

### 💻 Ejemplo de uso

```
========== SISTEMA DE GESTIÓN - TECHLAB ==========
1) Agregar producto
2) Listar productos
3) Buscar/Actualizar producto
4) Eliminar producto
5) Crear pedido
6) Listar pedidos
7) Salir
Elegí una opción:
```

---

## 🇺🇸 English

### 📄 Description

A product and order management system built in **pure Java** as an integrative backend course project. It allows registering products, managing stock, and creating orders with real-time inventory validation — all through an interactive console interface.

### 🎯 Features

- ✅ Create, update and delete products
- ✅ Specialized products: **Beverage** (volume) and **Food** (expiration date)
- ✅ Search by ID or name
- ✅ Order creation with automatic stock deduction
- ✅ Stock validation with custom exception
- ✅ Interactive console menu

### 🏗️ Architecture & Concepts Applied

| Concept | Implementation |
|---|---|
| Encapsulation | Getters/Setters on all entities |
| Inheritance | `Bebida` and `Comida` extend `Producto` |
| Polymorphism | Overridden `toString()` per subclass |
| Collections | `ArrayList<Producto>`, `ArrayList<Pedido>` |
| Exceptions | `try/catch`, `NumberFormatException`, custom exception |
| Packages | Organized in logical layers |

### ▶️ How to Run

**Requirements:** Java 17+

```bash
# Clone the repository
git clone https://github.com/TU_USUARIO/techlab-gestion-productos.git
cd techlab-gestion-productos

# Compile
javac -d out src/com/techlab/excepciones/*.java \
             src/com/techlab/productos/*.java \
             src/com/techlab/pedidos/*.java \
             src/com/techlab/servicios/*.java \
             src/com/techlab/Main.java

# Run
java -cp out com.techlab.Main
```

---

<div align="center">

Desarrollado como proyecto integrador · Backend Java Course 2025

</div>
