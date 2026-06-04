package proyecto_v;

import java.math.BigDecimal;

public class Recibo {

    private int id;
    private String producto;
    private BigDecimal precio;
    private String documento;
    private String nombre;

    public Recibo() {
    }

    public Recibo(String producto, BigDecimal precio, String documento, String nombre) {
        this.producto = producto;
        this.precio = precio;
        this.documento = documento;
        this.nombre = nombre;
    }

    public Recibo(int id, String producto, BigDecimal precio, String documento, String nombre) {
        this.id = id;
        this.producto = producto;
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
                + ", precio=" + precio
                + ", documento='" + documento + '\''
                + ", nombre='" + nombre + '\''
                + '}';
    }
}
