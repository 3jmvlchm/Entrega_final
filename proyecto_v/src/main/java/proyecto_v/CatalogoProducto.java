package proyecto_v;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class CatalogoProducto {

    public static final class ProductoCatalogo {

        private final String codigo;
        private final String nombre;
        private final BigDecimal precioUnitario;
        private final String imagen;
        private final String descripcion;

        public ProductoCatalogo(String codigo, String nombre, BigDecimal precioUnitario, String imagen, String descripcion) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.precioUnitario = precioUnitario;
            this.imagen = imagen;
            this.descripcion = descripcion;
        }

        public String getCodigo() {
            return codigo;
        }

        public String getNombre() {
            return nombre;
        }

        public BigDecimal getPrecioUnitario() {
            return precioUnitario;
        }

        public String getImagen() {
            return imagen;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    private static final List<ProductoCatalogo> PRODUCTOS = Collections.unmodifiableList(Arrays.asList(
            new ProductoCatalogo(
                    "manguera-industrial",
                    "Manguera industrial",
                    new BigDecimal("150000.00"),
                    "https://www.agroinsumosgao.com/wp-content/uploads/2025/08/2.webp",
                    "Alta resistencia para uso agricola e industrial."),
            new ProductoCatalogo(
                    "manguera-agricola",
                    "Manguera agricola",
                    new BigDecimal("98000.00"),
                    "https://www.agroinsumosgao.com/wp-content/uploads/2025/08/3.png",
                    "Solucion flexible para riego y conduccion de agua."),
            new ProductoCatalogo(
                    "acople-riego",
                    "Acople de riego",
                    new BigDecimal("45000.00"),
                    "https://www.agroinsumosgao.com/wp-content/uploads/2025/08/4.png",
                    "Accesorio para conexiones seguras en sistemas de riego."),
            new ProductoCatalogo(
                    "kit-agroindustrial",
                    "Kit agroindustrial",
                    new BigDecimal("120000.00"),
                    "https://www.agroinsumosgao.com/wp-content/uploads/2025/08/1.webp",
                    "Producto de apoyo para instalaciones agroindustriales.")
    ));

    private CatalogoProducto() {
    }

    public static List<ProductoCatalogo> obtenerProductos() {
        return PRODUCTOS;
    }

    public static ProductoCatalogo buscarPorCodigo(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (ProductoCatalogo producto : PRODUCTOS) {
            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }
        return null;
    }

    public static ProductoCatalogo buscarPorNombre(String nombre) {
        if (nombre == null) {
            return null;
        }
        for (ProductoCatalogo producto : PRODUCTOS) {
            if (producto.getNombre().equalsIgnoreCase(nombre.trim())) {
                return producto;
            }
        }
        return null;
    }

    public static BigDecimal calcularTotal(BigDecimal precioUnitario, int cantidad) {
        return precioUnitario
                .multiply(BigDecimal.valueOf(cantidad))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
