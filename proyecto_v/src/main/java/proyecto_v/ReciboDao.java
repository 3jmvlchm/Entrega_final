package proyecto_v;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReciboDao {

    private final Connection conexionBD;

    public ReciboDao() {
        this.conexionBD = conexion.getConexion();
        if (this.conexionBD == null) {
            throw new IllegalStateException(
                    "No se pudo abrir la conexion a la BD. Revisa MySQL, URL y credenciales.");
        }
        asegurarColumnasPedido();
    }

    public boolean crear(Recibo recibo) {
        String sql = "INSERT INTO recibos (producto, cantidad, precio_unitario, precio, documento, nombre) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexionBD.prepareStatement(sql)) {
            pstmt.setString(1, recibo.getProducto());
            pstmt.setInt(2, recibo.getCantidad());
            pstmt.setBigDecimal(3, recibo.getPrecioUnitario());
            pstmt.setBigDecimal(4, recibo.getPrecio());
            pstmt.setString(5, recibo.getDocumento());
            pstmt.setString(6, recibo.getNombre());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear recibo: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Recibo obtenerPorId(int id) {
        String sql = "SELECT id, producto, cantidad, precio_unitario, precio, documento, nombre FROM recibos WHERE id = ?";
        try (PreparedStatement pstmt = conexionBD.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapearRecibo(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener recibo: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Recibo> obtenerTodos() {
        List<Recibo> recibos = new ArrayList<>();
        String sql = "SELECT id, producto, cantidad, precio_unitario, precio, documento, nombre FROM recibos ORDER BY id DESC";
        try (Statement stmt = conexionBD.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                recibos.add(mapearRecibo(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener recibos: " + e.getMessage());
            e.printStackTrace();
        }
        return recibos;
    }

    public boolean actualizar(Recibo recibo) {
        String sql = "UPDATE recibos SET producto = ?, cantidad = ?, precio_unitario = ?, precio = ?, documento = ?, nombre = ? WHERE id = ?";
        try (PreparedStatement pstmt = conexionBD.prepareStatement(sql)) {
            pstmt.setString(1, recibo.getProducto());
            pstmt.setInt(2, recibo.getCantidad());
            pstmt.setBigDecimal(3, recibo.getPrecioUnitario());
            pstmt.setBigDecimal(4, recibo.getPrecio());
            pstmt.setString(5, recibo.getDocumento());
            pstmt.setString(6, recibo.getNombre());
            pstmt.setInt(7, recibo.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar recibo: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM recibos WHERE id = ?";
        try (PreparedStatement pstmt = conexionBD.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar recibo: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Recibo> buscarPorDocumentoONombre(String busqueda) {
        List<Recibo> recibos = new ArrayList<>();
        String sql = "SELECT id, producto, cantidad, precio_unitario, precio, documento, nombre "
                + "FROM recibos WHERE documento LIKE ? OR nombre LIKE ? ORDER BY id DESC";
        try (PreparedStatement pstmt = conexionBD.prepareStatement(sql)) {
            String patron = "%" + busqueda + "%";
            pstmt.setString(1, patron);
            pstmt.setString(2, patron);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    recibos.add(mapearRecibo(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar recibos: " + e.getMessage());
            e.printStackTrace();
        }
        return recibos;
    }

    public int obtenerTotalRecibos() {
        String sql = "SELECT COUNT(*) AS total FROM recibos";
        try (Statement stmt = conexionBD.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Error al contar recibos: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    private Recibo mapearRecibo(ResultSet rs) throws SQLException {
        return new Recibo(
                rs.getInt("id"),
                rs.getString("producto"),
                rs.getBigDecimal("precio_unitario"),
                rs.getInt("cantidad"),
                rs.getBigDecimal("precio"),
                rs.getString("documento"),
                rs.getString("nombre")
        );
    }

    private void asegurarColumnasPedido() {
        try {
            if (!existeColumna("cantidad")) {
                ejecutarAlter("ALTER TABLE recibos ADD COLUMN cantidad INT NOT NULL DEFAULT 1 AFTER producto");
            }
            if (!existeColumna("precio_unitario")) {
                ejecutarAlter("ALTER TABLE recibos ADD COLUMN precio_unitario DECIMAL(12,2) NOT NULL DEFAULT 0 AFTER cantidad");
                try (Statement stmt = conexionBD.createStatement()) {
                    stmt.executeUpdate("UPDATE recibos SET precio_unitario = precio WHERE precio_unitario = 0");
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo actualizar la estructura de la tabla recibos.", e);
        }
    }

    private boolean existeColumna(String columna) throws SQLException {
        String sql = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS "
                + "WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'recibos' AND COLUMN_NAME = ?";
        try (PreparedStatement pstmt = conexionBD.prepareStatement(sql)) {
            pstmt.setString(1, columna);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    private void ejecutarAlter(String sql) throws SQLException {
        try (Statement stmt = conexionBD.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
}
