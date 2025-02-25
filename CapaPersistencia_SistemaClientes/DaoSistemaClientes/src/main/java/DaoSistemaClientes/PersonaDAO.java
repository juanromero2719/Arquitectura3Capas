package DaoSistemaClientes;

import ConexionSistemaClientes.ConexionBDSistemaClientes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import DTObject.PersonaDto;
import ModeloSistemaClientes.Persona;

public class PersonaDAO {

    public PersonaDto obtenerPorId(Double id) {
        
        EntityManager em = ConexionBDSistemaClientes.getEntityManagerFactory().createEntityManager();
        PersonaDto personaDTO = null;

        try {
            Persona persona = em.find(Persona.class, id);
            if (persona != null) {
                personaDTO = new PersonaDto(persona.getIdentificacion(), persona.getNombres(), persona.getApellidos(), persona.getEdad());
            }
        } finally {
            em.close();
        }
        return personaDTO;
    }

    public void guardarPersona(PersonaDto personaDTO) {
        
        EntityManager em = ConexionBDSistemaClientes.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Persona persona = new Persona(personaDTO.getIdentificacion(), personaDTO.getNombres(), personaDTO.getApellidos(), personaDTO.getEdad());
            em.persist(persona);
            transaction.commit();
            System.out.println("✅ Persona guardada con éxito.");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("❌ Error al guardar persona: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void actualizarPersona(PersonaDto personaDTO) {
        
        EntityManager em = ConexionBDSistemaClientes.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Persona persona = em.find(Persona.class, personaDTO.getIdentificacion());
            if (persona != null) {
                persona.setNombres(personaDTO.getNombres());
                persona.setApellidos(personaDTO.getApellidos());
                persona.setEdad(personaDTO.getEdad());
                em.merge(persona);
                transaction.commit();
                System.out.println("✅ Persona actualizada con éxito.");
            } else {
                System.out.println("⚠ No se encontró la persona para actualizar.");
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("❌ Error al actualizar persona: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void eliminarPersona(Double id) {
        
        EntityManager em = ConexionBDSistemaClientes.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Persona persona = em.find(Persona.class, id);
            if (persona != null) {
                em.remove(persona);
                transaction.commit();
                System.out.println("✅ Persona eliminada con éxito.");
            } else {
                System.out.println("⚠ No se encontró la persona para eliminar.");
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("❌ Error al eliminar persona: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<PersonaDto> obtenerTodas() {
        
        EntityManager em = ConexionBDSistemaClientes.getEntityManagerFactory().createEntityManager();
        List<PersonaDto> listaPersonasDTO;

        try {
            List<Persona> personas = em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
            listaPersonasDTO = personas.stream()
                    .map(p -> new PersonaDto(p.getIdentificacion(), p.getNombres(), p.getApellidos(), p.getEdad()))
                    .toList();
        } finally {
            em.close();
        }

        return listaPersonasDTO;
    }
    
}
