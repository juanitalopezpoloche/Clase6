import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Ejercicio5 {

    public static void main(String[] args) {

        // Datos de entrada 
        List<String> productos = Arrays.asList(
            "TabletX,LoteA,8",
            "SmartphoneY,LoteB,7",
            "SmartwatchZ,LoteC,6",
            "LaptopW,LoteD,9",
            "TabletX,LoteE,8"
        );

        List<String> inspecciones = Arrays.asList(
            "LoteA, funcionalidad, 85",
            "LoteB, seguridad, 92",
            "LoteC, funcionalidad, 75",
            "LoteD, seguridad, 60",
            "LoteA, seguridad, 88",
            "LoteC, seguridad, 82",
            "LoteB, funcionalidad, 80"
        );

        List<String> fallos = Arrays.asList(
            "LoteA, sobrecalentamiento, 3",
            "LoteB, pantalla, 5",
            "LoteC, batería, 2",
            "LoteD, sobrecalentamiento, 7",
            "LoteA, pantalla, 2"
        );

        Scanner scanner = new Scanner(System.in);
        boolean mostrarElMenu = true;

        while (mostrarElMenu) {
            // Mostrar el menú
            System.out.println("\n---- ANÁLISIS DE CALIDAD ----");
            System.out.println("1. Calcular Índice de Aprobación por Lote");
            System.out.println("2. Identificar Lotes con Complejidad Alta y Baja Aprobación");
            System.out.println("3. Analizar Tipos de Fallos Frecuentes");
            System.out.println("4. Obtener Lote con Mayor Incidencia de Fallos");
            System.out.println("5. Evaluar la Calidad Promedio por Tipo de Inspección");
            System.out.println("6. Identificar Lotes con Fallos Críticos en Inspecciones Clave");
            System.out.println("7. Simulación de Mejora de Procesos");
            System.out.println("8. Salir");


            // Leer la opción seleccionada por el usuario
            System.out.print("\nSeleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    calcularElIndiceDeAprobacion(inspecciones);
                    break;
                case 2:
                    identificarLotesComplejidadAltaYBajaAprobacion(productos, inspecciones);
                    break;
                case 3:
                    analizarTiposFallosFrecuentes(fallos);
                    break;
                case 4:
                    obtenerLoteConMayorIncidenciaDeFallos(fallos, productos);
                    break;
                case 5:
                    evaluarCalidadPromedioPorTipoDeInspeccion(inspecciones);
                    break;
                case 6:
                    identificarLotesConFallosCriticosEnInspeccionesClave(inspecciones, fallos);
                    break;
                case 7:
                    simularMejoraDeProcesos(productos, inspecciones);
                    break;
                case 8:
                    mostrarElMenu = false;
                    System.out.println("\nSaliendo del programa.");
                    break;
                default:
                    System.out.println("\nOpción no válida, intente de nuevo.");
            }

            System.out.println(); // Añadir una línea en blanco para mejorar la legibilidad
        }

        scanner.close();
    }

    public static void calcularElIndiceDeAprobacion(List<String> inspecciones) {
        Map<String, List<Integer>> lotes = new HashMap<>();

        // Agrupo las inspecciones por lote y calculo el índice de aprobación
        for (String inspeccion : inspecciones) {

            String[] partes = inspeccion.split(", ");
            String lote = partes[0];
            int porcentaje = Integer.parseInt(partes[2]);

            if (!lotes.containsKey(lote)) {
                lotes.put(lote, new ArrayList<>());
            }

            lotes.get(lote).add(porcentaje);

        }

        System.out.println("\nÍndice de Aprobación por Lote:");

        for (String lote : lotes.keySet()) {

            List<Integer> porcentajes = lotes.get(lote);
            int total                 = 0;
            for (int porcentaje : porcentajes) {
                total += porcentaje;
            }
            double promedio = total / (double) porcentajes.size();
            System.out.println(lote + ": " + promedio + "%");

            if (promedio < 80) {
                System.out.println("\nLote con bajo índice de aprobación: " + lote);
            }

        }
    }

    public static void identificarLotesComplejidadAltaYBajaAprobacion(List<String> productos, List<String> inspecciones) {
        Map<String, Integer> complejidad = new HashMap<>();

        for (String producto : productos) {
            String[] partes      = producto.split(", ");
            String lote          = partes[1];
            int nivelComplejidad = Integer.parseInt(partes[2]);

            complejidad.put(lote, nivelComplejidad);
        }

        Map<String, List<Integer>> lotes = new HashMap<>();

        for (String inspeccion : inspecciones) {
            String[] partes = inspeccion.split(", ");
            String lote     = partes[0];
            int porcentaje  = Integer.parseInt(partes[2]);

            if (!lotes.containsKey(lote)) {
                lotes.put(lote, new ArrayList<>());
            }

            lotes.get(lote).add(porcentaje);
        }

        System.out.println("\nLotes con alta complejidad y baja aprobación:");

        for (String lote : lotes.keySet()) {
            int total = 0;

            for (int porcentaje : lotes.get(lote)) {
                total += porcentaje;
            }

            double promedio     = total / (double) lotes.get(lote).size();
            int complejidadLote = complejidad.get(lote);

            if (complejidadLote > 7 && promedio < 85) {
                System.out.println("Lote: " + lote + " | Complejidad: " + complejidadLote + " | Índice de Aprobación: " + promedio + "%");
            }
        }
    }

    public static void analizarTiposFallosFrecuentes(List<String> fallos) {
        Map<String, Integer> tipoFallos = new HashMap<>();

        for (String fallo : fallos) {
            String[] partes   = fallo.split(", ");
            String tipoFallo  = partes[1];
            int cantidadFallos = Integer.parseInt(partes[2]);

            tipoFallos.put(tipoFallo, tipoFallos.getOrDefault(tipoFallo, 0) + cantidadFallos);
        }

        System.out.println("\nTipos de Fallos Frecuentes:");

        for (String tipoFallo : tipoFallos.keySet()) {
            if (tipoFallos.get(tipoFallo) >= 3) {
                System.out.println(tipoFallo + ": " + tipoFallos.get(tipoFallo) + " fallos");
            }
        }
    }

    public static void obtenerLoteConMayorIncidenciaDeFallos(List<String> fallos, List<String> productos) {
        Map<String, Integer> incidenciaFallos = new HashMap<>();

        for (String fallo : fallos) {
            String[] partes    = fallo.split(", ");
            String lote        = partes[0];
            int cantidadFallos = Integer.parseInt(partes[2]);

            incidenciaFallos.put(lote, incidenciaFallos.getOrDefault(lote, 0) + cantidadFallos);
        }

        String loteConMayorFallos     = Collections.max(incidenciaFallos.entrySet(), Map.Entry.comparingByValue()).getKey();
        String productoConMayorFallos = "";

        for (String producto : productos) {
            if (producto.contains(loteConMayorFallos)) {
                productoConMayorFallos = producto.split(", ")[0];
                break;
            }
        }

        System.out.println("\nLote con mayor incidencia de fallos:");
        System.out.println("Producto: " + productoConMayorFallos + " | Lote: " + loteConMayorFallos + " | Fallos totales: " + incidenciaFallos.get(loteConMayorFallos));
    }

    public static void evaluarCalidadPromedioPorTipoDeInspeccion(List<String> inspecciones) {
        Map<String, List<Integer>> inspeccionesPorTipo = new HashMap<>();

        for (String inspeccion : inspecciones) {
            String[] partes       = inspeccion.split(", ");
            String tipoInspeccion = partes[1];
            int porcentaje        = Integer.parseInt(partes[2]);

            if (!inspeccionesPorTipo.containsKey(tipoInspeccion)) {
                inspeccionesPorTipo.put(tipoInspeccion, new ArrayList<>());
            }

            inspeccionesPorTipo.get(tipoInspeccion).add(porcentaje);
        }

        System.out.println("\nCalidad promedio por tipo de inspección:");

        double menorPromedio        = 100;
        String tipoConMenorPromedio = "";

        for (String tipo : inspeccionesPorTipo.keySet()) {
            List<Integer> porcentajes = inspeccionesPorTipo.get(tipo);
            int total                 = 0;

            for (int porcentaje : porcentajes) {
                total += porcentaje;
            }

            double promedio = total / (double) porcentajes.size();
            System.out.println(tipo + ": " + promedio + "%");

            if (promedio < menorPromedio) {
                menorPromedio        = promedio;
                tipoConMenorPromedio = tipo;
            }
        }

        System.out.println("\nTipo de inspección con el promedio más bajo: " + tipoConMenorPromedio);
    }

    public static void identificarLotesConFallosCriticosEnInspeccionesClave(List<String> inspecciones, List<String> fallos) {
        Map<String, Double> lotesConFallosCriticos = new HashMap<>();

        for (String inspeccion : inspecciones) {
            String[] partes       = inspeccion.split(", ");
            String lote           = partes[0];
            String tipoInspeccion = partes[1];
            int porcentaje        = Integer.parseInt(partes[2]);

            if (tipoInspeccion.equals("seguridad") && porcentaje < 75) {
                lotesConFallosCriticos.put(lote, (double) porcentaje);
            }
        }

        System.out.println("\nLotes con fallos críticos en inspecciones clave:");

        for (String lote : lotesConFallosCriticos.keySet()) {
            System.out.println("Lote: " + lote + " | Aprobación en seguridad: " + lotesConFallosCriticos.get(lote) + "%");

            for (String fallo : fallos) {
                if (fallo.startsWith(lote)) {
                    String[] partes  = fallo.split(", ");
                    String tipoFallo = partes[1];
                    System.out.println("   Tipo de fallo: " + tipoFallo);
                }
            }
        }
    }


    public static void simularMejoraDeProcesos(List<String> productos, List<String> inspecciones) {
        Map<String, List<Integer>> lotes = new HashMap<>();

        for (String inspeccion : inspecciones) {
            String[] partes = inspeccion.split(", ");
            String lote     = partes[0];
            int porcentaje  = Integer.parseInt(partes[2]);

            if (!lotes.containsKey(lote)) {
                lotes.put(lote, new ArrayList<>());
            }

            lotes.get(lote).add(porcentaje);
        }

        System.out.println("\nSimulación de mejora de procesos (aumento del 5% en complejidad > 7):");

        for (String lote : lotes.keySet()) {
            List<Integer> porcentajes = lotes.get(lote);
            int total                 = 0;

            for (int porcentaje : porcentajes) {
                total += porcentaje;
            }

            double promedio = total / (double) porcentajes.size();

            if (promedio > 7) {
                promedio += 5; // Incremento al 5% si la complejidad es mayor a 7
            }

            System.out.println("Lote: " + lote + " | Índice de Aprobación simulado: " + promedio + "%");
        }
    }
        
}
