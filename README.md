<div align="center">

# 🛒 TechLab — Sistema de Gestión de Productos

![Java](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=java)
![Status](https://img.shields.io/badge/Status-Completed-brightgreen?style=for-the-badge)
![OOP](https://img.shields.io/badge/Paradigm-OOP-blue?style=for-the-badge)
![Pattern](https://img.shields.io/badge/Pattern-Service--Layer-purple?style=for-the-badge)

</div>

---

## 🇦🇷 Español

### 📄 Descripción

Sistema de gestión de productos y pedidos desarrollado en **Java puro (SE 17+)** como proyecto integrador de un curso de backend. Permite registrar productos, gestionar stock y procesar pedidos con descuentos automáticos, validación de inventario en tiempo real y manejo de estados — todo desde una interfaz de consola interactiva con colores ANSI.

### 🎯 Funcionalidades

- ✅ Alta, baja lógica y modificación de productos
- ✅ Productos especializados: **Bebida** (volumen en litros) y **Comida** (fecha de vencimiento)
- ✅ Búsqueda por ID o nombre (case-insensitive)
- ✅ Creación de pedidos con descuento automático por volumen:
  - 10% de descuento desde 5 unidades
  - 20% de descuento desde 10 unidades
- ✅ Validación de stock con jerarquía de excepciones personalizada
- ✅ Estados de pedido con `enum`: `PENDIENTE`, `ENTREGADO`, `CANCELADO`
- ✅ Cancelar un pedido devuelve el stock automáticamente al inventario
- ✅ Borrado lógico: los productos eliminados no afectan el historial de pedidos
- ✅ Cancelar cualquier operación a mitad de camino con `x`
- ✅ Interfaz de consola con colores ANSI (🟢 éxito · 🔴 error · 🟡 advertencia)

### 🏗️ Arquitectura

```
src/com/techlab/
├── Main.java                                    # Menú interactivo con colores ANSI
├── productos/
│   ├── Producto.java                            # Clase abstracta base
│   ├── ProductoGenerico.java                    # Producto sin tipo especial
│   ├── Bebida.java                              # Herencia: agrega volumen en litros
│   └── Comida.java                              # Herencia: agrega fecha de vencimiento
├── pedidos/
│   ├── Pedido.java                              # Entidad pedido con estado y totales
│   ├── LineaPedido.java                         # Ítem con lógica de descuento
│   └── EstadoPedido.java                        # Enum: PENDIENTE / ENTREGADO / CANCELADO
├── excepciones/
│   ├── TechlabException.java                    # Superclase de todas las excepciones
│   ├── StockInsuficienteException.java          # Stock insuficiente al crear pedido
│   └── ProductoNoEncontradoException.java       # Producto inexistente o inactivo
└── servicios/
    ├── ProductoService.java                     # CRUD con borrado lógico
    └── PedidoService.java                       # Pedidos, estados y restauración de stock
```

### 📐 Conceptos de POO aplicados

| Concepto | Implementación |
|---|---|
| **Clase abstracta** | `Producto` es `abstract` y declara `getDetalleTipo()` como método abstracto |
| **Encapsulamiento** | Todos los atributos son `private`/`protected`, acceso solo por getters y setters |
| **Herencia** | `Bebida`, `Comida` y `ProductoGenerico` extienden `Producto` |
| **Polimorfismo** | `toString()` y `getDetalleTipo()` sobreescritos por cada subclase |
| **Enum** | `EstadoPedido` restringe los estados posibles en tiempo de compilación |
| **Colecciones** | `ArrayList<Producto>`, `ArrayList<Pedido>`, `ArrayList<LineaPedido>` |
| **Excepciones** | Jerarquía propia: `TechlabException` → `StockInsuficienteException` / `ProductoNoEncontradoException` |
| **Borrado lógico** | Campo `activo` en `Producto`: eliminar = desactivar, nunca borrar de la lista |
| **Descuentos** | Calculados en `LineaPedido` por cantidad: 10% (≥5 ud.) / 20% (≥10 ud.) |
| **Restauración de stock** | Cancelar un pedido devuelve automáticamente el stock a cada producto |
| **Paquetes** | Organización en capas: `productos` / `pedidos` / `excepciones` / `servicios` |

### 🗂️ Menú del sistema

```
========== SISTEMA DE GESTIÓN - TECHLAB ==========
1) Agregar producto
2) Listar productos
3) Buscar/Actualizar producto
4) Eliminar producto
5) Crear pedido
6) Listar pedidos
7) Actualizar estado de pedido
8) Salir
Elegí una opción:
```

### 💻 Ejemplo de salida

```
Pedido #1 [PENDIENTE]:
  - Café Premium x10 (-20%) = $12000.00
  - Medialunas x3 = $6600.00
  TOTAL: $18600.00  (Ahorraste: $3000.00)
```

```
Estado actualizado a: CANCELADO
Stock restaurado por cancelación del pedido.
```

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

---

## 🇺🇸 English

### 📄 Description

A product and order management system built in **pure Java (SE 17+)** as an integrative backend course project. It allows registering products, managing stock, and processing orders with automatic discounts, real-time inventory validation and order state management — all through an interactive console interface with ANSI color output.

### 🎯 Features

- ✅ Create, soft-delete and update products
- ✅ Specialized products: **Beverage** (volume in liters) and **Food** (expiration date)
- ✅ Search by ID or name (case-insensitive)
- ✅ Order creation with automatic volume discounts:
  - 10% discount from 5 units
  - 20% discount from 10 units
- ✅ Stock validation with custom exception hierarchy
- ✅ Order states via `enum`: `PENDING`, `DELIVERED`, `CANCELLED`
- ✅ Cancelling an order automatically restores stock to inventory
- ✅ Soft delete: removed products don't break order history
- ✅ Cancel any operation mid-flow with `x`
- ✅ ANSI color console (🟢 success · 🔴 error · 🟡 warning)

### 🏗️ Architecture

```
src/com/techlab/
├── Main.java                                    # Interactive menu with ANSI colors
├── productos/
│   ├── Producto.java                            # Abstract base class
│   ├── ProductoGenerico.java                    # Concrete generic product
│   ├── Bebida.java                              # Inheritance: adds volume in liters
│   └── Comida.java                              # Inheritance: adds expiration date
├── pedidos/
│   ├── Pedido.java                              # Order entity with state and totals
│   ├── LineaPedido.java                         # Order item with discount logic
│   └── EstadoPedido.java                        # Enum: PENDIENTE / ENTREGADO / CANCELADO
├── excepciones/
│   ├── TechlabException.java                    # Base class for all system exceptions
│   ├── StockInsuficienteException.java          # Thrown when stock is insufficient
│   └── ProductoNoEncontradoException.java       # Thrown when product not found
└── servicios/
    ├── ProductoService.java                     # CRUD with soft delete
    └── PedidoService.java                       # Orders, states and stock restoration
```

### 📐 OOP Concepts Applied

| Concept | Implementation |
|---|---|
| **Abstract class** | `Producto` is `abstract` and declares `getDetalleTipo()` as abstract method |
| **Encapsulation** | All fields are `private`/`protected`, accessed only through getters/setters |
| **Inheritance** | `Bebida`, `Comida` and `ProductoGenerico` extend `Producto` |
| **Polymorphism** | `toString()` and `getDetalleTipo()` overridden per subclass |
| **Enum** | `EstadoPedido` restricts possible states at compile time |
| **Collections** | `ArrayList<Producto>`, `ArrayList<Pedido>`, `ArrayList<LineaPedido>` |
| **Exceptions** | Custom hierarchy: `TechlabException` → `StockInsuficienteException` / `ProductoNoEncontradoException` |
| **Soft delete** | `activo` field in `Producto`: delete = deactivate, never remove from list |
| **Discounts** | Calculated in `LineaPedido` by quantity: 10% (≥5 units) / 20% (≥10 units) |
| **Stock restoration** | Cancelling an order returns stock to each product automatically |
| **Packages** | Layered organization: `productos` / `pedidos` / `excepciones` / `servicios` |

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
<br>
UTN FRBA · Ingeniería en Sistemas de Información

</div>
