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
    }

    public boolean crear(Recibo recibo) {
        String sql = "INSERT INTO recibos (producto, precio, documento, nombre) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexionBD.prepareStatement(sql)) {
            pstmt.setString(1, recibo.getProducto());
            pstmt.setBigDecimal(2, recibo.getPrecio());
            pstmt.setString(3, recibo.getDocumento());
            pstmt.setString(4, recibo.getNombre());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear recibo: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Recibo obtenerPorId(int id) {
        String sql = "SELECT id, producto, precio, documento, nombre FROM recibos WHERE id = ?";
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
        String sql = "SELECT id, producto, precio, documento, nombre FROM recibos ORDER BY id DESC";
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
        String sql = "UPDATE recibos SET producto = ?, precio = ?, documento = ?, nombre = ? WHERE id = ?";
        try (PreparedStatement pstmt = conexionBD.prepareStatement(sql)) {
            pstmt.setString(1, recibo.getProducto());
            pstmt.setBigDecimal(2, recibo.getPrecio());
            pstmt.setString(3, recibo.getDocumento());
            pstmt.setString(4, recibo.getNombre());
            pstmt.setInt(5, recibo.getId());
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
        String sql = "SELECT id, producto, precio, documento, nombre "
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
                rs.getBigDecimal("precio"),
                rs.getString("documento"),
                rs.getString("nombre")
        );
    }
}
