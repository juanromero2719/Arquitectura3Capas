package ConexionPersistencia;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConexionBDPersistencia {
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistenciaMysql"); 

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("✅ EntityManagerFactory cerrado correctamente.");
        }
    }
}
