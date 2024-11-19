import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Ejercicio4 {


    private static List<String> uids          = new ArrayList<>();
    private static List<String> uidsValidos   = new ArrayList<>();
    private static List<String> uidsInvalidos = new ArrayList<>();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n----- MENÚ -----");
            System.out.println("1. Agregar UIDs");
            System.out.println("2. Mostrar UIDs");
            System.out.println("3. Mostrar UIDs válidos");
            System.out.println("4. Mostrar UIDs inválidos");
            System.out.println("5. Salir");
            System.out.print("\nSeleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarUIDs(scanner);
                    break;
                case 2:
                    mostrarUIDs();
                    break;
                case 3:
                    mostrarUIDsValidos();
                    break;
                case 4:
                    mostrarUIDsInvalidos();
                    break;
                case 5:
                    System.out.println("\nGracias por hacer uso del programa :)");
                    break;
                default:
                    System.out.println("\nOpción no válida. Intente de nuevo.");
            }
        } while (opcion != 5);

        scanner.close();
    }

    public static void agregarUIDs(Scanner scanner) {

        System.out.print("\n¿Cuántos UIDs desea agregar? ");

        int cantidad = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantidad; i++) {

            System.out.print("Ingrese el UID #" + (i + 1) + ": ");

            String uid = scanner.nextLine();

            uids.add(uid); // Agego el UID a la lista

            // Valido el UID y lo agrego a la lista correspondiente
            if (esUIDValido(uid)) {
                uidsValidos.add(uid);
            } else {
                uidsInvalidos.add(uid);
            }
        }
    }

    // Método para mostrar todos los UIDs
    public static void mostrarUIDs() {
        if (uids.isEmpty()) {
            System.out.println("\nNo hay UIDs registrados.");
        } else {
            System.out.println("\nListado de todos los UIDs:");

            for (String uid : uids) {
                System.out.println(uid);
            }
        }
    }

    public static void mostrarUIDsValidos() {
        if (uidsValidos.isEmpty()) {
            System.out.println("No hay UIDs válidos.");
        } else {
            System.out.println("\nUIDs válidos:");

            for (String uid : uidsValidos) {
                System.out.println(uid);
            }
        }
    }

    public static void mostrarUIDsInvalidos() {
        if (uidsInvalidos.isEmpty()) {
            System.out.println("No hay UIDs inválidos.");
        } else {
            System.out.println("\nUIDs inválidos:");

            for (String uid : uidsInvalidos) {
                System.out.println(uid);
            }
        }
    }
  
    // Validaciones que comprueban si un UID es valido
    public static boolean esUIDValido(String uid) {

        if (uid.length() != 10) {
            return false;
        }

        // Expresión regular para verificar que el UID contenga solo caracteres alfanuméricos
        if (!uid.matches("[A-Za-z0-9]+")) {
            return false;
        }

        if (contarMayusculas(uid) < 2) {
            return false;
        }

        if (contarDigitos(uid) < 3) {
            return false;
        }

        if (tieneCaracteresRepetidos(uid)) {
            return false;
        }

        return true;
    }
  

    public static int contarMayusculas(String uid) {
        int cantadoDeMayusculas = 0;

        for (int i = 0; i < uid.length(); i++) {

            if (Character.isUpperCase(uid.charAt(i))) {
                cantadoDeMayusculas++;
            }

        }

        return cantadoDeMayusculas;
    }

    public static int contarDigitos(String uid) {
        int contadorDeDigitos = 0;

        for (int i = 0; i < uid.length(); i++) {
            if (Character.isDigit(uid.charAt(i))) {
                contadorDeDigitos++;
            }
        }

        return contadorDeDigitos;
    }
  

    public static boolean tieneCaracteresRepetidos(String uid) {
        Set<Character> caracteres = new HashSet<>();

        for (int i = 0; i < uid.length(); i++) {

            char c = uid.charAt(i);

            if (!caracteres.add(c)) {
                return true; // Si no se puede añadir al set, significa que el caracter ya existe
            }

        }

        return false;
    }

}
