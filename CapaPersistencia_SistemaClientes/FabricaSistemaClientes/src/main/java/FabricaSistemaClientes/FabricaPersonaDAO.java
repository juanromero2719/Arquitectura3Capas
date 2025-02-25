package FabricaSistemaClientes;


import DaoSistemaClientes.PersonaDAO;

public class FabricaPersonaDAO {
   
    private static FabricaPersonaDAO instancia;
    private final PersonaDAO personaDAO;

    private FabricaPersonaDAO() {
        personaDAO = new PersonaDAO();
    }

    public static synchronized FabricaPersonaDAO getInstancia() {
        
        if (instancia == null) {
            instancia = new FabricaPersonaDAO();
        }
        return instancia;
    }

    public PersonaDAO obtenerPersonaDAO() {
        return personaDAO;
    }
    

}
