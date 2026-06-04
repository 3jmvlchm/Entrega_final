package proyecto_v;

import java.math.BigDecimal;

public class Recibo {

    private int id;
    private String producto;
    private BigDecimal precioUnitario;
    private int cantidad = 1;
    private BigDecimal precio;
    private String documento;
    private String nombre;

    public Recibo() {
    }

    public Recibo(String producto, BigDecimal precio, String documento, String nombre) {
        this.producto = producto;
        this.precio = precio;
        this.precioUnitario = precio;
        this.documento = documento;
        this.nombre = nombre;
    }

    public Recibo(int id, String producto, BigDecimal precio, String documento, String nombre) {
        this.id = id;
        this.producto = producto;
        this.precio = precio;
        this.precioUnitario = precio;
        this.documento = documento;
        this.nombre = nombre;
    }

    public Recibo(String producto, BigDecimal precioUnitario, int cantidad, BigDecimal precio, String documento, String nombre) {
        this.producto = producto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.precio = precio;
        this.documento = documento;
        this.nombre = nombre;
    }

    public Recibo(int id, String producto, BigDecimal precioUnitario, int cantidad, BigDecimal precio, String documento, String nombre) {
        this.id = id;
        this.producto = producto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.precio = precio;
        this.documento = documento;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Recibo{"
                + "id=" + id
                + ", producto='" + producto + '\''
                + ", precioUnitario=" + precioUnitario
                + ", cantidad=" + cantidad
                + ", precio=" + precio
                + ", documento='" + documento + '\''
                + ", nombre='" + nombre + '\''
                + '}';
    }
}
