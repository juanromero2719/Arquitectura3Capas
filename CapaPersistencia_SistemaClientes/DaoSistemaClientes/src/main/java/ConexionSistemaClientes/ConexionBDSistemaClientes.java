package ConexionSistemaClientes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConexionBDSistemaClientes {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistenciaH2");

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public static void cerrarEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("✅ EntityManagerFactory cerrado correctamente.");
        }
    }
    
   
}
