package DaoSistemaClientes;

import ConexionSistemaClientes.ConexionBDSistemaClientes;
import ModeloSistemaClientes.ClaseB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClaseBDao {

    private final Connection conexion;

    public ClaseBDao() {
        this.conexion = ConexionBDSistemaClientes.getConexion();
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() {
        String sql = """
                CREATE TABLE IF NOT EXISTS ClaseB (
                    id IDENTITY PRIMARY KEY,
                    destino VARCHAR(255),
                    remitente VARCHAR(255),
                    mensaje VARCHAR(1000)
                )
                """;
        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
        }
    }

    public void insertar(ClaseB claseB) {
        String sql = "INSERT INTO ClaseB (destino, remitente, mensaje) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, claseB.mensajeEnviado().contains("Destino: ") 
                                  ? claseB.mensajeEnviado().split("Destino: ")[1].split("\n")[0] 
                                  : claseB.mensajeEnviado());
            pstmt.setString(2, "***miempresa***"); 
            pstmt.setString(3, claseB.mensajeEnviado());
            pstmt.executeUpdate();
            System.out.println("✅ Registro insertado en ClaseB");
        } catch (SQLException e) {
            System.err.println("Error al insertar en ClaseB: " + e.getMessage());
        }
        claseB.mensajeEnviadoFinal();
    }
    
 
    public List<ClaseB> obtenerTodos() {
        List<ClaseB> lista = new ArrayList<>();
        String sql = "SELECT * FROM ClaseB";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()){
                String destino = rs.getString("destino");
                String mensaje = rs.getString("mensaje");
                ClaseB claseB = new ClaseB(destino, mensaje);
                lista.add(claseB);
            }
        } catch(SQLException e) {
            System.err.println("Error al obtener todos los registros de ClaseB: " + e.getMessage());
        }
        return lista;
    }
}
