package proyecto_v;

import java.math.BigDecimal;

public final class PedidoService {

    private PedidoService() {
    }

    public static Recibo crearRecibo(String codigoProducto, String productoAlterno, String cantidadValor,
            String documentoValor, String nombreValor) {
        CatalogoProducto.ProductoCatalogo producto = obtenerProducto(codigoProducto, productoAlterno);
        int cantidad = validarCantidad(cantidadValor);
        String documento = validarDocumento(documentoValor);
        String nombre = validarNombre(nombreValor);
        BigDecimal precioUnitario = producto.getPrecioUnitario();
        BigDecimal total = CatalogoProducto.calcularTotal(precioUnitario, cantidad);
        return new Recibo(producto.getNombre(), precioUnitario, cantidad, total, documento, nombre);
    }

    public static int validarCantidad(String valor) {
        try {
            int cantidad = Integer.parseInt(limpiar(valor));
            if (cantidad < 1) {
                throw new IllegalArgumentException("La cantidad debe ser mayor o igual a 1.");
            }
            if (cantidad > 999) {
                throw new IllegalArgumentException("La cantidad maxima permitida es 999.");
            }
            return cantidad;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ingresa una cantidad valida.");
        }
    }

    public static String validarDocumento(String valor) {
        String documento = requerirTexto(valor, "El documento es obligatorio.");
        if (documento.length() < 5 || documento.length() > 30) {
            throw new IllegalArgumentException("El documento debe tener entre 5 y 30 caracteres.");
        }
        return documento;
    }

    public static String validarNombre(String valor) {
        String nombre = requerirTexto(valor, "El nombre del solicitante es obligatorio.");
        if (nombre.length() < 2 || nombre.length() > 120) {
            throw new IllegalArgumentException("El nombre debe tener entre 2 y 120 caracteres.");
        }
        return nombre;
    }

    public static String limpiar(String valor) {
        return valor == null ? "" : valor.trim();
    }

    private static CatalogoProducto.ProductoCatalogo obtenerProducto(String codigoProducto, String productoAlterno) {
        CatalogoProducto.ProductoCatalogo producto = CatalogoProducto.buscarPorCodigo(limpiar(codigoProducto));
        if (producto == null) {
            producto = CatalogoProducto.buscarPorNombre(productoAlterno);
        }
        if (producto == null) {
            throw new IllegalArgumentException("Selecciona un producto valido del portafolio.");
        }
        return producto;
    }

    private static String requerirTexto(String valor, String mensaje) {
        String texto = limpiar(valor);
        if (texto.isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }
        return texto;
    }
}
