import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Ejercicio1 {
    
    public static void main(String[] args) {

        Scanner scanner     = new Scanner(System.in);
        boolean mostrarMenu = true;

        List<String> ventas = Arrays.asList(
            "camisa,50,20.00",// 1000
            "pantalon,80,35.00",   // 2800
            "zapatos,30,50.00",    // 1500
            "polo,96,20.00",       // 1920
            "short,66,25.00"       // 1650
        );
        

        // Menú de opciones
        while (mostrarMenu) {
            System.out.println("\n---------------- ANÁLISIS DE VENTAS  ----------------");
            System.out.println("1. Calcular Ingreso Total");
            System.out.println("2. Contar Ventas por Producto");
            System.out.println("3. Filtrar Ventas Mayores a un Monto");
            System.out.println("4. Obtener Producto Más Vendido");
            System.out.println("5. Mostrar todas las ventas");
            System.out.println("6. Salir");
            System.out.println("\nElija una opción:");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:

                    double ingresoTotalVentas = calcularIngresoTotalVentas(ventas);
                    System.out.println("\nIngreso total de ventas: " + ingresoTotalVentas);

                    break;

                case 2:

                    Map<String, Integer> ventasPorProducto = contarVentasPorProducto(ventas);
                    System.out.println("\nVentas por Producto: ");
                    for (Map.Entry<String, Integer> entry : ventasPorProducto.entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue() + " ventas");
                    }

                    break;

                case 3:

                    System.out.println("\nIngrese el monto:");

                    double monto = scanner.nextDouble();
                    scanner.nextLine();

                    filtrarVentasMayoresAMonto(ventas, monto);

                    break;

                case 4:

                    String productoMasVendido = obtenerProductoMasVendido(ventas);
                    System.out.println("\nProducto más vendido: " + productoMasVendido);

                    break;

                case 5:

                    mostrarVentas(ventas);

                    break;
                case 6:

                    mostrarMenu = false;
                    System.out.println("Saliendo del programa....");

                    break;
                default:
                    System.out.println("\nOpción inválida. Intente de nuevo.");
            }
        }

        scanner.close();
    }

    public static double calcularIngresoTotalVentas(List<String> ventas) {
        double ingresoTotal = 0;

        for (String venta : ventas) {

            String[] partes       = venta.split(","); // Parto por , para obtener el nombre del producto y la cantidad de ventas de este producto
            int cantidad          = Integer.parseInt(partes[1]);
            double precioUnitario = Double.parseDouble(partes[2]);

            ingresoTotal += cantidad * precioUnitario;

        }

        return ingresoTotal;
    }

    public static Map<String, Integer> contarVentasPorProducto(List<String> ventas) {
        Map<String, Integer> conteo = new HashMap<>();

        for (String venta : ventas) {
            String[] partes = venta.split(","); // Parto por , para obtener el nombre del producto y la cantidad de ventas de este producto
            String producto = partes[0];
            int cantidad    = Integer.parseInt(partes[1]);

            conteo.put(producto, conteo.getOrDefault(producto, 0) + cantidad);
        }

        return conteo;
    }

    public static void filtrarVentasMayoresAMonto(List<String> ventas, double monto) {
        for (String venta : ventas) {

            String[] partes       = venta.split(","); // Parto por , para obtener el nombre del producto y la cantidad de ventas de este producto
            int cantidad          = Integer.parseInt(partes[1]);
            double precioUnitario = Double.parseDouble(partes[2]);
            double ingresoVenta   = cantidad * precioUnitario;

            if (ingresoVenta > monto) {
                System.out.println("\nProducto: " + partes[0] + " | Cantidad vendidas: " + partes[1] + " | Precio Unitario: " + partes[2]);
            }
        }
    }

    public static String obtenerProductoMasVendido(List<String> ventas) {
        Map<String, Integer> contadorProducto = contarVentasPorProducto(ventas);
        String productoMasVendido = null;
        int maxVentas             = 0;

        for (Map.Entry<String, Integer> entry : contadorProducto.entrySet()) {

            if (entry.getValue() > maxVentas) {
                productoMasVendido = entry.getKey();// cantidad de ventas del producto mas vendido (valor).
                maxVentas          = entry.getValue();// Nombre del producto mas vendido
            }

        }

        return productoMasVendido;
    }

    public static void mostrarVentas(List<String> ventas) {

        System.out.println("\nLista de todas las ventas:");

        for (String venta : ventas) {
            String[] explodeInfoProducto = venta.split(",");
            System.out.println("\nProducto: " + explodeInfoProducto[0] + " | Cantidad vendidas: " + explodeInfoProducto[1] + " | Precio Unitario: " + explodeInfoProducto[2]);
        }

    }

}