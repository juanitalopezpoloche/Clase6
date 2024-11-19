import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        // Lista de facturas (como cadenas de texto)
        List<String> facturas = Arrays.asList(
            "F001,ClienteA,1000.00,19",
            "F002,ClienteB,1500.00,19",
            "F003,ClienteA,2000.00,19",
            "F004,ClienteC,2500.00,19",
            "F005,ClienteB,3000.00,19"
        );

        Scanner scanner     = new Scanner(System.in);
        boolean mostrarMenu = true;

        while (mostrarMenu) {
            System.out.println("\n-------------- Menu de Opciones -------------- ");
            System.out.println("1. Calcular Monto Total con IVA");
            System.out.println("2. Calcular Ingreso Total por Cliente");
            System.out.println("3. Filtrar Facturas Mayores a un Valor Específico");
            System.out.println("4. Obtener Cliente con Mayor Ingreso Total");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    calcularMontoConIVA(facturas);
                    break;
                case 2:
                    calcularIngresoTotalPorCliente(facturas);
                    break;
                case 3:
                    filtrarFacturasPorMonto(facturas, scanner);
                    break;
                case 4:
                    obtenerClienteMayorIngreso(facturas);
                    break;
                case 5:
                    System.out.println("Gracias por usar el programa....");
                    mostrarMenu = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }

        scanner.close();
    }

    public static void calcularMontoConIVA(List<String> facturas) {
        System.out.println("\nCálculo del Monto Total con IVA: ");

        for (String factura : facturas) {
            String[] datos       = factura.split(",");
            String numeroFactura = datos[0];
            String cliente       = datos[1];
            double montoTotal    = Double.parseDouble(datos[2]);
            double iva           = Double.parseDouble(datos[3]);

            double montoConIVA = montoTotal * (1 + iva / 100);
            System.out.printf("Factura: %s, Cliente: %s, Monto Total con IVA: %.2f\n", numeroFactura, cliente, montoConIVA);
        }
    }

    public static void calcularIngresoTotalPorCliente(List<String> facturas) {
        System.out.println("\nCálculo del Ingreso Total por Cliente: ");
        Map<String, Double> ingresosPorCliente = new HashMap<>();

        for (String factura : facturas) {
            String[] datos    = factura.split(",");
            String cliente    = datos[1];
            double montoTotal = Double.parseDouble(datos[2]);

            ingresosPorCliente.put(cliente, ingresosPorCliente.getOrDefault(cliente, 0.0) + montoTotal);
        }

        for (Map.Entry<String, Double> entry : ingresosPorCliente.entrySet()) {
            System.out.printf("Cliente: %s, Ingreso Total: %.2f\n", entry.getKey(), entry.getValue());
        }
    }

    public static void filtrarFacturasPorMonto(List<String> facturas, Scanner scanner) {
        System.out.print("\nIntroduce el monto mínimo (sin IVA) para filtrar las facturas: ");
        double valorFiltro = scanner.nextDouble();

        System.out.println("\nFacturas filtradas:");
        for (String factura : facturas) {
            String[] datos     = factura.split(",");
            double montoTotal  = Double.parseDouble(datos[2]);
            double montoSinIVA = montoTotal / (1 + Double.parseDouble(datos[3]) / 100);

            if (montoSinIVA > valorFiltro) {
                String numeroFactura = datos[0];
                String cliente       = datos[1];
                System.out.printf("Factura: %s, Cliente: %s, Monto Total: %.2f\n", numeroFactura, cliente, montoTotal);
            }
        }
    }

    public static void obtenerClienteMayorIngreso(List<String> facturas) {
        System.out.println("\nObteniendo Cliente con Mayor Ingreso Total:");
        Map<String, Double> ingresosPorCliente = new HashMap<>();

        for (String factura : facturas) {
            String[] datos    = factura.split(",");
            String cliente    = datos[1];
            double montoTotal = Double.parseDouble(datos[2]);

            ingresosPorCliente.put(cliente, ingresosPorCliente.getOrDefault(cliente, 0.0) + montoTotal);
        }

        String clienteMayorIngreso = null;
        double mayorIngreso        = 0.0;

        for (Map.Entry<String, Double> entry : ingresosPorCliente.entrySet()) {

            if (entry.getValue() > mayorIngreso) {
                mayorIngreso = entry.getValue();
                clienteMayorIngreso = entry.getKey();
            }

        }

        System.out.printf("\nEl cliente con el mayor ingreso total es: %s con un ingreso de: %.2f\n", clienteMayorIngreso, mayorIngreso);
    }
}
