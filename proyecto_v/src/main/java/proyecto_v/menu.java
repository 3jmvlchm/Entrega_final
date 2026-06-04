package proyecto_v;

import java.util.List;
import java.util.Scanner;

public class menu {

    public static void main(String[] args) {

        Scanner consola = new Scanner(System.in);
        ReciboDao reciboDao = new ReciboDao();
        int opcion = 0;

        try {
            while (opcion != 7) {
                System.out.println("\n===== MENU CRUD RECIBOS =====");
                System.out.println("1. Registrar recibo");
                System.out.println("2. Mostrar todos los recibos");
                System.out.println("3. Buscar recibo por ID");
                System.out.println("4. Modificar recibo");
                System.out.println("5. Eliminar recibo");
                System.out.println("6. Buscar por documento o nombre");
                System.out.println("7. Salir");
                System.out.print("Seleccione una opcion: ");

                opcion = Integer.parseInt(consola.nextLine());

                switch (opcion) {
                    case 1:
                        Recibo nuevoRecibo = leerRecibo(consola);
                        if (reciboDao.crear(nuevoRecibo)) {
                            System.out.println("Recibo registrado correctamente");
                        } else {
                            System.out.println("Error al registrar recibo");
                        }
                        break;

                    case 2:
                        mostrarRecibos(reciboDao.obtenerTodos());
                        break;

                    case 3:
                        System.out.print("Ingrese ID del recibo: ");
                        int idBuscar = Integer.parseInt(consola.nextLine());
                        Recibo reciboEncontrado = reciboDao.obtenerPorId(idBuscar);
                        if (reciboEncontrado != null) {
                            System.out.println(reciboEncontrado);
                        } else {
                            System.out.println("Recibo no encontrado");
                        }
                        break;

                    case 4:
                        System.out.print("Ingrese ID del recibo a modificar: ");
                        int idActualizar = Integer.parseInt(consola.nextLine());
                        Recibo reciboActualizar = reciboDao.obtenerPorId(idActualizar);
                        if (reciboActualizar != null) {
                            Recibo datosActualizados = leerRecibo(consola);
                            datosActualizados.setId(idActualizar);
                            if (reciboDao.actualizar(datosActualizados)) {
                                System.out.println("Recibo actualizado");
                            } else {
                                System.out.println("Error al actualizar");
                            }
                        } else {
                            System.out.println("Recibo no encontrado");
                        }
                        break;

                    case 5:
                        System.out.print("Ingrese ID del recibo a eliminar: ");
                        int idEliminar = Integer.parseInt(consola.nextLine());
                        if (reciboDao.eliminar(idEliminar)) {
                            System.out.println("Recibo eliminado");
                        } else {
                            System.out.println("No se pudo eliminar");
                        }
                        break;

                    case 6:
                        System.out.print("Ingrese documento o nombre a buscar: ");
                        String busqueda = consola.nextLine();
                        mostrarRecibos(reciboDao.buscarPorDocumentoONombre(busqueda));
                        break;

                    case 7:
                        System.out.println("Saliendo del programa...");
                        break;

                    default:
                        System.out.println("Opcion invalida");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            conexion.cerrarConexion();
            consola.close();
            System.out.println("Conexion cerrada");
        }
    }

    private static Recibo leerRecibo(Scanner consola) {
        System.out.println("Productos disponibles:");
        for (CatalogoProducto.ProductoCatalogo producto : CatalogoProducto.obtenerProductos()) {
            System.out.println("- " + producto.getCodigo() + " | " + producto.getNombre()
                    + " | $" + producto.getPrecioUnitario());
        }

        System.out.print("Codigo del producto: ");
        String codigoProducto = consola.nextLine();

        System.out.print("Cantidad: ");
        String cantidad = consola.nextLine();

        System.out.print("Documento de quien pidio: ");
        String documento = consola.nextLine();

        System.out.print("Nombre de quien pidio: ");
        String nombre = consola.nextLine();

        return PedidoService.crearRecibo(codigoProducto, "", cantidad, documento, nombre);
    }

    private static void mostrarRecibos(List<Recibo> recibos) {
        if (recibos.isEmpty()) {
            System.out.println("No hay recibos registrados");
            return;
        }

        for (Recibo recibo : recibos) {
            System.out.println(recibo);
        }
    }
}
