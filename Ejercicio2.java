import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Ejercicio2 {

    public static void main(String[] args) {
        // Datos de calificaciones
        List<String> calificaciones = Arrays.asList(
            "Dana,Fisica,85",
            "Dana,Matematicas,65",
            "Ana,Filosofia,100",
            "Ana,Historia,90",
            "Pedro,Matematicas,70",
            "Juan,Historia,75",
            "Juan,Etica,75",
            "Pablo,Filosofia,95",
            "Valentina,Etica,80",
            "Valentina,Ingles,70"
        );
        
        Scanner scanner       = new Scanner(System.in);
        boolean mostrarElMenu = true;

        // Menú de opciones
        while (mostrarElMenu) {
            System.out.println("\n---------- MENU ----------");
            System.out.println("1. Calcular Promedio de Calificaciones por Estudiante");
            System.out.println("2. Contar Estudiantes por Materia");
            System.out.println("3. Filtrar Calificaciones Mayores a un Valor");
            System.out.println("4. Obtener la Materia con el Promedio Más Alto");
            System.out.println("5. Mostrar Información de Calificaciones");
            System.out.println("6. Salir");
            System.out.println("\nElija una opción:");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    calcularPromedioEstudiantes(calificaciones);
                    break;
                case 2:
                    contarEstudiantesPorMateria(calificaciones);
                    break;
                case 3:
                    System.out.println("Ingrese el valor mínimo de calificación:");
                    int valor = scanner.nextInt();
                    scanner.nextLine();
                    filtrarCalificacionesMayores(calificaciones, valor);
                    break;
                case 4:
                    obtenerMateriaConPromedioMasAlto(calificaciones);
                    break;
                case 5:
                    mostrarCalificaciones(calificaciones);
                    break;
                case 6:
                    mostrarElMenu = false;
                    System.out.println("Saliendo del programa....");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }

        scanner.close();
    }

    public static void calcularPromedioEstudiantes(List<String> calificaciones) {
        Map<String, List<Integer>> estudiantes = new HashMap<>();
        
        // Organizo las calificaciones por estudiante
        for (String registro : calificaciones) {
            String[] infoCalificacionesEstudiante = registro.split(",");
            String estudiante  = infoCalificacionesEstudiante[0];
            int calificacion   = Integer.parseInt(infoCalificacionesEstudiante[2]);

            estudiantes.putIfAbsent(estudiante, new ArrayList<>());
            estudiantes.get(estudiante).add(calificacion);
        }
        
        // Calculo y muestro el promedio por estudiante
        for (Map.Entry<String, List<Integer>> entry : estudiantes.entrySet()) {
            String estudiante   = entry.getKey();
            List<Integer> notas = entry.getValue();
            int suma = 0;

            for (int nota : notas) {
                suma += nota;
            }

            double promedio = (double) suma / notas.size();
            System.out.println(estudiante + " - Promedio: " + promedio);
        }
    }

    public static void contarEstudiantesPorMateria(List<String> calificaciones) {
        Map<String, Set<String>> materias = new HashMap<>();

        for (String registro : calificaciones) {
            String[] infoCalificacionesEstudiante = registro.split(",");
            String materia    = infoCalificacionesEstudiante[1];
            String estudiante = infoCalificacionesEstudiante[0];

            materias.putIfAbsent(materia, new HashSet<>()); // Si la clave no existe en el mapa agrega la clave y su valor asociado al mapa
            materias.get(materia).add(estudiante);
        }

        // Muestro el número de estudiantes por materia
        for (Map.Entry<String, Set<String>> entry : materias.entrySet()) {
            String materia = entry.getKey();
            Set<String> estudiantes = entry.getValue();
            System.out.println(materia + ": " + estudiantes.size() + " estudiantes");
        }
    }

    public static void filtrarCalificacionesMayores(List<String> calificaciones, int valor) {
        System.out.println("\nCalificaciones mayores a " + valor + ":");

        for (String registro : calificaciones) {
            String[] infoCalificacionesEstudiante = registro.split(",");
            String estudiante = infoCalificacionesEstudiante[0];
            String materia    = infoCalificacionesEstudiante[1];
            int calificacion  = Integer.parseInt(infoCalificacionesEstudiante[2]);

            if (calificacion > valor) {
                System.out.println(estudiante + " - " + materia + ": " + calificacion);
            }
        }
    }

    public static void obtenerMateriaConPromedioMasAlto(List<String> calificaciones) {
        Map<String, List<Integer>> materias = new HashMap<>();


        for (String registro : calificaciones) {
            String[] infoCalificacionesEstudiante = registro.split(",");
            String materia   = infoCalificacionesEstudiante[1];
            int calificacion = Integer.parseInt(infoCalificacionesEstudiante[2]);

            materias.putIfAbsent(materia, new ArrayList<>());
            materias.get(materia).add(calificacion);
        }

        // Calcular el promedio de cada materia y determinar la de mayor promedio
        String materiaConMayorPromedio = "";
        double mayorPromedio           = 0;

        for (Map.Entry<String, List<Integer>> entry : materias.entrySet()) {
            String materia      = entry.getKey();
            List<Integer> notas = entry.getValue();
            int suma = 0;

            for (int nota : notas) {
                suma += nota;
            }

            double promedio = (double) suma / notas.size();

            if (promedio > mayorPromedio) {
                mayorPromedio           = promedio;
                materiaConMayorPromedio = materia;
            }
        }

        System.out.println("\nMateria con el promedio más alto: " + materiaConMayorPromedio + " - Promedio: " + mayorPromedio);

    }

    public static void mostrarCalificaciones(List<String> calificaciones) {
        System.out.println("\nInformación de las calificaciones:");

        for (String registro : calificaciones) {
            String[] infoCalificacionesEstudiante = registro.split(",");
            String estudiante = infoCalificacionesEstudiante[0];
            String materia    = infoCalificacionesEstudiante[1];
            int calificacion  = Integer.parseInt(infoCalificacionesEstudiante[2]);

            System.out.println("Estudiante: " + estudiante + ", Materia: " + materia + ", Calificación: " + calificacion);
        }
    }

}
