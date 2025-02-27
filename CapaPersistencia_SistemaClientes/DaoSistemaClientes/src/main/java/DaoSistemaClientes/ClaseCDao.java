package DaoSistemaClientes;

import ConexionSistemaClientes.ConexionBDSistemaClientes;
import ModeloSistemaClientes.ClaseC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClaseCDao {

    private final Connection conexion;

    public ClaseCDao() {
        this.conexion = ConexionBDSistemaClientes.getConexion();
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() {
        String sql = """
                CREATE TABLE IF NOT EXISTS ClaseC (
                    id IDENTITY PRIMARY KEY,
                    texto VARCHAR(255)
                )
                """;
        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(sql);
            System.out.println("✅ Tabla ClaseC verificada/creada.");
        } catch (SQLException e) {
            System.err.println("Error creando/verificando tabla ClaseC: " + e.getMessage());
        }
    }

    public void insertar(ClaseC claseC) {
        String sql = "INSERT INTO ClaseC (texto) VALUES (?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, claseC.getTexto());
            pstmt.executeUpdate();
            System.out.println("✅ Registro insertado en ClaseC");
        } catch (SQLException e) {
            System.err.println("Error al insertar en ClaseC: " + e.getMessage());
        }
    }
    
    public List<ClaseC> obtenerTodos() {
        List<ClaseC> lista = new ArrayList<>();
        String sql = "SELECT * FROM ClaseC";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()){
                ClaseC claseC = new ClaseC(rs.getString("texto"));
                lista.add(claseC);
            }
        } catch(SQLException e) {
            System.err.println("Error al obtener todos los registros de ClaseC: " + e.getMessage());
        }
        return lista;
    }
}
