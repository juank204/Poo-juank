import java.util.ArrayList;

class EstudianteNotas {

    private String nombre;
    private ArrayList<Double> notas;

    public EstudianteNotas(String nombre) {
        this.nombre = nombre;
        this.notas = new ArrayList<>();
    }

    public void agregarNota(double nota) {
        this.notas.add(nota);
    }

    public double calcularPromedio() {
        if (notas.isEmpty()) return 0;

        double suma = 0;

        for (double nota : notas) {
            suma += nota;
        }

        return suma / notas.size();
    }

    public boolean aprobo() {
        return calcularPromedio() >= 3.0;
    }

    public void mostrarResultado() {
        double promedio = calcularPromedio();
        String estado = aprobo() ? "APROBÓ" : "REPROBÓ";

        System.out.printf("%s: %.2f - %s%n", nombre, promedio, estado);
    }
}

public class CalculadoraNotas {

    public static void main(String[] args) {

        System.out.println("=== VERSIÓN OOP ===");

        EstudianteNotas est = new EstudianteNotas("Juan");
        est.agregarNota(4.5);
        est.agregarNota(3.8);
        est.agregarNota(4.2);
        est.mostrarResultado();

        System.out.println("\n=== MÚLTIPLES ESTUDIANTES ===");

        EstudianteNotas maria = new EstudianteNotas("María");
        maria.agregarNota(4.8);
        maria.agregarNota(4.5);
        maria.agregarNota(4.9);

        EstudianteNotas pedro = new EstudianteNotas("Pedro");
        pedro.agregarNota(2.5);
        pedro.agregarNota(3.1);
        pedro.agregarNota(2.8);

        maria.mostrarResultado();
        pedro.mostrarResultado();
    }
}