import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class Venta {
    private List<Cliente> clientes = new ArrayList<>();
    private Map<Cliente, List<Producto>> ventas = new HashMap<>();

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
        ventas.put(cliente, new ArrayList<>());
    }

    public void agregarProducto(Cliente cliente, Producto producto) {
        if (ventas.containsKey(cliente)) {
            ventas.get(cliente).add(producto);
        }
    }

    public void mostrarClientes() {
        for (Cliente cliente : clientes) {
            System.out.println("Cliente: " + cliente.getNombre() + " (Cédula: " + cliente.getCedula() + ")");
            List<Producto> productosAdquiridos = ventas.get(cliente);

            if (productosAdquiridos != null && !productosAdquiridos.isEmpty()) {
                System.out.println("Productos adquiridos:");
                for (Producto producto : productosAdquiridos) {
                    System.out.println("  - Nombre: " + producto.getNombre() + ", Precio: $" + producto.getPrecio() + ", Cantidad: " + producto.getCantidad());
                }
                double totalCliente = calcularTotalPorCliente(productosAdquiridos);
                System.out.println("Total de compras: $" + totalCliente);
            } else {
                System.out.println("No se han realizado compras por este cliente.");
            }
            System.out.println("---------------");
        }
    }

    public double calcularTotalPorCliente(List<Producto> productos) {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecio() * producto.getCantidad();
        }
        return total;
    }

    public void impresionDeDatos (){
        Venta venta = new Venta();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Ingrese el nombre del cliente (o escriba 'fin' para terminar):");
            String nombreCliente = scanner.nextLine();

            if (nombreCliente.equalsIgnoreCase("fin")) {
                break;
            }

            System.out.println("Ingrese la cédula del cliente:");
            int cedulaCliente = Integer.parseInt(scanner.nextLine());

            Cliente cliente = new Cliente(nombreCliente, cedulaCliente);
            venta.agregarCliente(cliente);

            while (true) {
                System.out.println("Ingrese el nombre del producto (o escriba 'fin' para terminar esta venta):");
                String nombreProducto = scanner.nextLine();

                if (nombreProducto.equalsIgnoreCase("fin")) {
                    break;
                }

                System.out.println("Ingrese el precio del producto:");
                double precioProducto = Double.parseDouble(scanner.nextLine());

                System.out.println("Ingrese la cantidad del producto:");
                int cantidadProducto = Integer.parseInt(scanner.nextLine());

                Producto producto = new Producto(nombreProducto, precioProducto, cantidadProducto);
                venta.agregarProducto(cliente, producto);
            }
        }

        venta.mostrarClientes();
    }
}

