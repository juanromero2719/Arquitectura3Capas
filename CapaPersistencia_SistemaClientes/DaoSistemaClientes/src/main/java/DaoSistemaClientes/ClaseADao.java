package DaoSistemaClientes;

import ConexionSistemaClientes.ConexionBDSistemaClientes;
import ModeloSistemaClientes.ClaseA;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad ClaseA.
 */
public class ClaseADao {

    private final Connection conexion;

    public ClaseADao() {
        this.conexion = ConexionBDSistemaClientes.getConexion();
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() {
        String sql = """
                CREATE TABLE IF NOT EXISTS ClaseA (
                    id DOUBLE PRIMARY KEY,
                    nombres VARCHAR(255),
                    apellidos VARCHAR(255)
                )
                """;
        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
        }
    }

    public void insertar(ClaseA claseA) {
        String sql = "INSERT INTO ClaseA (id, nombres, apellidos) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setDouble(1, claseA.getId());
            pstmt.setString(2, claseA.getNombres());
            pstmt.setString(3, claseA.getApellidos());
            pstmt.executeUpdate();
            System.out.println("✅ Registro insertado en ClaseA");
        } catch (SQLException e) {
            System.err.println("Error al insertar en ClaseA: " + e.getMessage());
        }
    }
    
    public List<ClaseA> obtenerTodos() {
        List<ClaseA> lista = new ArrayList<>();
        String sql = "SELECT * FROM ClaseA";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()){
                ClaseA claseA = new ClaseA();
                claseA.setId(rs.getDouble("id"));
                claseA.setNombres(rs.getString("nombres"));
                claseA.setApellidos(rs.getString("apellidos"));
                lista.add(claseA);
            }
        } catch(SQLException e) {
            System.err.println("Error al obtener todos los registros de ClaseA: " + e.getMessage());
        }
        return lista;
    }
}
