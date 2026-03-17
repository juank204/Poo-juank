import java.util.ArrayList;

public class Estudiante {

    // Atributos privados (encapsulación)
    private String nombre;
    private int edad;
    private String carrera;
    private ArrayList<String> materias;

    // Constructor
    public Estudiante(String nombre, int edad, String carrera) {
        this.nombre = nombre;
        this.edad = edad;
        this.carrera = carrera;
        this.materias = new ArrayList<>();
    }

    // Método para inscribir materia
    public void inscribirMateria(String materia) {
        this.materias.add(materia);
        System.out.println(nombre + " se inscribió en " + materia);
    }

    public void estudiar(int horas) { 
    System.out.println(nombre + " estudió " + horas + " horas"); }

    // Método para presentarse
    public void presentarse() {
        System.out.println("Hola, soy " + nombre);
        System.out.println("Tengo " + edad + " años");
        System.out.println("Estudio " + carrera);
        System.out.println("Materias inscritas: " + materias.size());
    }

    // Método main para probar
    public static void main(String[] args) {

        // Crear objetos
        Estudiante est1 = new Estudiante("Juan Lozano", 18, "Sistematizacion de Datos");
        Estudiante est2 = new Estudiante("Carlos López", 22, "Ing. Sistemas");

        // Usar métodos
        est1.presentarse();

        System.out.println("---");

        est1.inscribirMateria("POO");
        est1.inscribirMateria("Calculo integral");

        System.out.println("---");

        est2.presentarse();
        est2.inscribirMateria("POO");
    }
}