// Sonidos de 5 animales en Java
import java.util.Scanner;

public class Animales {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Selecciona un animal:");
        System.out.println("1. Perro");
        System.out.println("2. Gato");
        System.out.println("3. Vaca");
        System.out.println("4. Rana");
        System.out.println("5. Leon");
        System.out.print("Escribe el numero: ");

        int opcion = sc.nextInt();

        switch (opcion) {
            case 1: System.out.println("El perro dice: Guau guau!");  break;
            case 2: System.out.println("El gato dice: Miau miau!");   break;
            case 3: System.out.println("La vaca dice: Muuuuu!");      break;
            case 4: System.out.println("La rana dice: Croac croac!"); break;
            case 5: System.out.println("El leon dice: Roaaarrr!");    break;
            default: System.out.println("Opcion no valida");
        }

        sc.close();
    }
}
