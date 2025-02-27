package ConexionSistemaClientes;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBDSistemaClientes {
    
    private static volatile Connection conexion;
    
    private static final String URL = "jdbc:h2:./db";
    private static final String USUARIO = "sa";
    private static final String PASSWORD = "";

    private ConexionBDSistemaClientes() {
        
    }

    public static Connection getConexion() {
        
        if (conexion == null) {
            synchronized (ConexionBDSistemaClientes.class) {
                if (conexion == null) {
                    try {
                        
                        Class.forName("org.h2.Driver");                      
                        conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                        System.out.println("✅ Conexión H2 establecida (Singleton).");
                        
                    } catch (ClassNotFoundException | SQLException e) {
                        System.err.println("Error al conectar a la base de datos H2: " + e.getMessage());
                    }
                }
            }
        }
        return conexion;
    }
}