/*
 * Clase de conexion a la base de datos MySQL
 *
 * @author samue
 */
package proyecto_v;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {

    private static Connection connection;

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/bdprueba?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String user = "root";
    private static final String password = "Malumapbdb";

    private conexion() {
    }

    public static Connection getConexion() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Conexion exitosa a la base de datos");
            }
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException(
                    "No se pudo conectar a MySQL. Verifica que el servidor este arriba, "
                    + "la BD 'bdprueba' exista y las credenciales sean correctas.", e);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(
                    "No se encontro el driver de MySQL en el classpath.", e);
        }
    }

    public static void cerrarConexion() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Conexion cerrada");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }
}

