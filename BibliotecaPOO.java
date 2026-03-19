package com.biblioteca.main;

import java.util.ArrayList;
import java.util.List;

// interfaz prestable
interface Prestable {
    void prestar();
    void devolver();
}

// clase persona
class Persona {
    protected String nombre;
    protected String identificacion;

    public Persona(String nombre, String identificacion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
    }

    public String getNombre() { return nombre; }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("ID: " + identificacion);
    }
}

// clase libro implementa prestable
class Libro implements Prestable {
    private String titulo;
    private String autor;
    private boolean disponible;

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
    }

    @Override
    public void prestar() { disponible = false; }

    @Override
    public void devolver() { disponible = true; }

    public String getTitulo()     { return titulo; }
    public boolean isDisponible() { return disponible; }

    public void mostrarLibro() {
        System.out.println("Titulo: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Disponible: " + disponible);
    }
}

// clase usuario con maximo 3 libros
class Usuario extends Persona {
    private List<Libro> librosPrestados;
    private static final int MAX_LIBROS = 3;

    public Usuario(String nombre, String identificacion) {
        super(nombre, identificacion);
        this.librosPrestados = new ArrayList<>();
    }

    public List<Libro> getLibrosPrestados() { return librosPrestados; }

    public boolean puedePrestar() { return librosPrestados.size() < MAX_LIBROS; }

    public void agregarLibro(Libro libro) { librosPrestados.add(libro); }
    public void quitarLibro(Libro libro)  { librosPrestados.remove(libro); }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Libros prestados: " + librosPrestados.size());
    }
}

// clase bibliotecario
class Bibliotecario extends Persona {
    private double salario;

    public Bibliotecario(String nombre, String identificacion, double salario) {
        super(nombre, identificacion);
        this.salario = salario;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Salario: $" + salario);
    }
}

// servicio para manejar libros
class LibroService {
    private List<Libro> libros = new ArrayList<>();

    public void agregarLibro(Libro libro) { libros.add(libro); }

    public List<Libro> getLibros() { return libros; }

    public void mostrarLibros() {
        for (Libro libro : libros) {
            libro.mostrarLibro();
            System.out.println("---------------");
        }
    }
}

// servicio para manejar prestamos
class UsuarioService {

    public void prestarLibro(Usuario usuario, Libro libro) {
        if (!usuario.puedePrestar()) {
            System.out.println("error: " + usuario.getNombre() +
                " ya tiene 3 libros prestados, debe devolver uno primero");
            return;
        }
        if (!libro.isDisponible()) {
            System.out.println("error: el libro '" + libro.getTitulo() +
                "' no esta disponible");
            return;
        }
        libro.prestar();
        usuario.agregarLibro(libro);
        System.out.println("prestamo exitoso: '" + libro.getTitulo() +
            "' a " + usuario.getNombre());
    }

    public void devolverLibro(Usuario usuario, Libro libro) {
        if (!usuario.getLibrosPrestados().contains(libro)) {
            System.out.println("error: " + usuario.getNombre() +
                " no tiene ese libro");
            return;
        }
        libro.devolver();
        usuario.quitarLibro(libro);
        System.out.println("devolucion exitosa: '" + libro.getTitulo() +
            "' de " + usuario.getNombre());
    }
}

// clase principal
public class Main {
    public static void main(String[] args) {

        LibroService   libroService   = new LibroService();
        UsuarioService usuarioService = new UsuarioService();

        Libro libro1 = new Libro("Cien anos de soledad", "Gabriel Garcia Marquez");
        Libro libro2 = new Libro("El principito",        "Antoine de Saint-Exupery");
        Libro libro3 = new Libro("Don quijote",          "Cervantes");
        Libro libro4 = new Libro("1984",                 "Orwell");

        libroService.agregarLibro(libro1);
        libroService.agregarLibro(libro2);
        libroService.agregarLibro(libro3);
        libroService.agregarLibro(libro4);

        Usuario usuario     = new Usuario("Carlos Perez", "123");
        Bibliotecario admin = new Bibliotecario("Ana Torres", "999", 2500000);

        // prestamos, el cuarto debe fallar
        usuarioService.prestarLibro(usuario, libro1);
        usuarioService.prestarLibro(usuario, libro2);
        usuarioService.prestarLibro(usuario, libro3);
        usuarioService.prestarLibro(usuario, libro4);

        // devuelve uno e intenta de nuevo
        usuarioService.devolverLibro(usuario, libro1);
        usuarioService.prestarLibro(usuario, libro4);

        System.out.println("\n===== libros =====");
        libroService.mostrarLibros();

        System.out.println("===== usuario =====");
        usuario.mostrarInformacion();

        System.out.println("===== bibliotecario =====");
        admin.mostrarInformacion();
    }
}
