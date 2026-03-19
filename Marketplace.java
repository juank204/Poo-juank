// ============================================================
// PROYECTO: MiMercado - Marketplace estilo Mercado Libre
// AVANCE INICIAL - Estructura base con los 4 pilares de POO
// ============================================================

import java.util.ArrayList;
import java.util.List;

// ============================================================
// INTERFAZ: Define el "contrato" que todo producto debe cumplir
// Polimorfismo: cualquier clase que implemente esto puede
// comportarse como un Producto.
// ============================================================
interface Vendible {
    String getNombre();       // Toda clase debe poder devolver su nombre
    double getPrecio();       // Toda clase debe poder devolver su precio
    void mostrarDetalle();    // Toda clase debe mostrar su informacion
    boolean estaDisponible(); // Toda clase debe indicar si hay stock
}

// ============================================================
// CLASE ABSTRACTA: Base comun para todos los productos
// No se puede instanciar directamente (no puedes hacer new Producto())
// Encapsulamiento: los atributos son 'protected' (solo hijos los ven)
// ============================================================
abstract class Producto implements Vendible {

    // Encapsulamiento: atributos privados, solo accesibles por metodos
    protected String nombre;
    protected double precio;
    protected int stock;
    protected String vendedor;

    // Constructor base que comparten todos los productos
    public Producto(String nombre, double precio, int stock, String vendedor) {
        this.nombre   = nombre;
        this.precio   = precio;
        this.stock    = stock;
        this.vendedor = vendedor;
    }

    // Getters: forma segura de leer los atributos privados
    @Override
    public String getNombre()  { return nombre; }

    @Override
    public double getPrecio()  { return precio; }

    @Override
    public boolean estaDisponible() { return stock > 0; } // true si hay stock

    // Metodo abstracto: cada tipo de producto lo implementa a su manera
    // Esto es POLIMORFISMO - mismo nombre, comportamiento diferente
    @Override
    public abstract void mostrarDetalle();

    // Metodo comun a todos: aplica descuento sobre el precio
    public double calcularPrecioConDescuento(double porcentaje) {
        return precio - (precio * porcentaje / 100);
    }
}

// ============================================================
// CLASE CONCRETA: Producto Electronico
// Hereda de Producto e implementa su propio mostrarDetalle()
// ============================================================
class Electronico extends Producto {

    private String marca;    // Atributo exclusivo de electronicos
    private int garantiaMeses;

    public Electronico(String nombre, double precio, int stock,
                       String vendedor, String marca, int garantiaMeses) {
        super(nombre, precio, stock, vendedor); // Llama al constructor padre
        this.marca          = marca;
        this.garantiaMeses  = garantiaMeses;
    }

    // POLIMORFISMO: implementacion propia de mostrarDetalle
    @Override
    public void mostrarDetalle() {
        System.out.println("=== ELECTRONICO ===");
        System.out.println("Producto  : " + nombre);
        System.out.println("Marca     : " + marca);
        System.out.println("Precio    : $" + precio);
        System.out.println("Garantia  : " + garantiaMeses + " meses");
        System.out.println("Vendedor  : " + vendedor);
        System.out.println("Disponible: " + (estaDisponible() ? "Si" : "No"));
    }
}

// ============================================================
// CLASE CONCRETA: Producto de Ropa
// Otro tipo de producto con sus propios atributos especificos
// ============================================================
class Ropa extends Producto {

    private String talla;   // Atributo exclusivo de ropa
    private String color;

    public Ropa(String nombre, double precio, int stock,
                String vendedor, String talla, String color) {
        super(nombre, precio, stock, vendedor);
        this.talla = talla;
        this.color = color;
    }

    // POLIMORFISMO: misma firma, diferente contenido
    @Override
    public void mostrarDetalle() {
        System.out.println("=== ROPA ===");
        System.out.println("Producto  : " + nombre);
        System.out.println("Talla     : " + talla);
        System.out.println("Color     : " + color);
        System.out.println("Precio    : $" + precio);
        System.out.println("Vendedor  : " + vendedor);
        System.out.println("Disponible: " + (estaDisponible() ? "Si" : "No"));
    }
}

// ============================================================
// CLASE: Carrito de Compras
// ENCAPSULAMIENTO: la lista interna no es accesible directamente
// ============================================================
class Carrito {

    // Lista privada: nadie puede modificarla desde afuera directamente
    private List<Producto> productos = new ArrayList<>();
    private String clienteNombre;

    public Carrito(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    // Metodo para agregar producto al carrito (con validacion de stock)
    public void agregar(Producto p) {
        if (p.estaDisponible()) {
            productos.add(p);
            System.out.println("'" + p.getNombre() + "' agregado al carrito.");
        } else {
            System.out.println("'" + p.getNombre() + "' no tiene stock disponible.");
        }
    }

    // Calcula el total sumando todos los precios del carrito
    public double calcularTotal() {
        double total = 0;
        for (Producto p : productos) {
            total += p.getPrecio(); // Usa el getter, no accede directo al atributo
        }
        return total;
    }

    // Muestra el resumen completo del carrito
    public void mostrarResumen() {
        System.out.println("\n====== CARRITO DE: " + clienteNombre + " ======");
        for (Producto p : productos) {
            p.mostrarDetalle(); // POLIMORFISMO en accion: cada producto se muestra diferente
            System.out.println("-----------------------------");
        }
        System.out.println("TOTAL A PAGAR: $" + calcularTotal());
    }
}

// ============================================================
// CLASE PRINCIPAL: Punto de entrada del programa
// Simula un cliente usando la plataforma
// ============================================================
public class Marketplace {

    public static void main(String[] args) {

        // Creando productos del catálogo
        Electronico celular = new Electronico(
            "Samsung Galaxy A55", 850000, 10,
            "TechStore Colombia", "Samsung", 12
        );

        Ropa camiseta = new Ropa(
            "Camiseta Nike Dri-Fit", 95000, 5,
            "SportZone", "M", "Negro"
        );

        Electronico laptop = new Electronico(
            "Lenovo IdeaPad 3", 2300000, 3,
            "TechStore Colombia", "Lenovo", 24
        );

        // Creando un carrito para el cliente
        Carrito carrito = new Carrito("Juan Perez");

        // El cliente agrega productos
        carrito.agregar(celular);
        carrito.agregar(camiseta);
        carrito.agregar(laptop);

        // Ver resumen del carrito
        carrito.mostrarResumen();

        // Ejemplo de descuento
        System.out.println("\nPrecio con 10% de descuento en el celular: $"
            + celular.calcularPrecioConDescuento(10));
    }
}

// ============================================================
// ¿QUE FALTARIA IMPLEMENTAR PARA COMPLETAR EL PROYECTO?
// Ver el archivo PENDIENTES.md que se genera junto a este archivo
// ============================================================
