package proyecto_v;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) para la entidad Cliente
 * Gestiona todas las operaciones CRUD contra la base de datos
 *
 * @author samue
 */
public class ClienteDao {

    private Connection conexionBD;

    public ClienteDao() {
        this.conexionBD = conexion.getConexion();
        if (this.conexionBD == null) {
            throw new IllegalStateException(
                    "No se pudo abrir la conexion a la BD. Revisa MySQL, URL y credenciales.");
        }
    }

    /**
     * Crea (inserta) un nuevo cliente en la base de datos
     *
     * @param cliente Objeto Cliente con nombre y apellido
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean crear(Cliente cliente) {
        String sql = "INSERT INTO clientes (name, last_name) VALUES (?, ?)";
        try (PreparedStatement pstmt = conexionBD.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getName());
            pstmt.setString(2, cliente.getLast_name());
            int filas = pstmt.executeUpdate();
            return filas > 0; // ojo 
        } catch (SQLException e) {
            System.err.println("Error al crear cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene un cliente por su ID
     *
     * @param id El ID del cliente a buscar
     * @return Objeto Cliente si existe, null en caso contrario
     */
    public Cliente obtenerPorId(int id) {
        String sql = "SELECT id, name, last_name FROM clientes WHERE id = ?";
        try (PreparedStatement pstmt = conexionBD.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("last_name")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cliente: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Obtiene todos los clientes de la base de datos
     *
     * @return Lista de clientes
     */
    public List<Cliente> obtenerTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT id, name, last_name FROM clientes";
        try (Statement stmt = conexionBD.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("last_name")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los clientes: " + e.getMessage());
            e.printStackTrace();
        }
        return clientes;
    }

    /**
     * Actualiza los datos de un cliente existente
     *
     * @param cliente Objeto Cliente con los datos actualizados (debe tener ID)
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET name = ?, last_name = ? WHERE id = ?";
        try (PreparedStatement pstmt = conexionBD.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getName());
            pstmt.setString(2, cliente.getLast_name());
            pstmt.setInt(3, cliente.getId());
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina un cliente de la base de datos
     *
     * @param id El ID del cliente a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminar(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (PreparedStatement pstmt = conexionBD.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Busca clientes por nombre
     *
     * @param nombre El nombre a buscar (búsqueda parcial)
     * @return Lista de clientes que coinciden con la búsqueda
     */
    public List<Cliente> buscarPorNombre(String nombre) {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT id, name, last_name FROM clientes WHERE name LIKE ?";
        try (PreparedStatement pstmt = conexionBD.prepareStatement(sql)) {
            pstmt.setString(1, "%" + nombre + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("last_name")
                    );
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar clientes por nombre: " + e.getMessage());
            e.printStackTrace();
        }
        return clientes;
    }

    /**
     * Obtiene la cantidad total de clientes en la base de datos
     *
     * @return Número de clientes
     */
    public int obtenerTotalClientes() {
        String sql = "SELECT COUNT(*) as total FROM clientes";
        try (Statement stmt = conexionBD.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener total de clientes: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }
}
