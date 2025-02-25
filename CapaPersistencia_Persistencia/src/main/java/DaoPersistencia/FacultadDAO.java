package DaoPersistencia;

import ConexionPersistencia.ConexionBDPersistencia;
import DTObject.FacultadDto;
import ModeloEntidades.Facultad;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

public class FacultadDAO {

    private static final EntityManagerFactory emf = ConexionBDPersistencia.getEntityManagerFactory();

    public void insertarFacultad(FacultadDto facultadDTO) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();

    try {
        transaction.begin();

        Facultad facultad = new Facultad();
        // No asignar el ID para que se genere automáticamente
        // facultad.setId(facultadDTO.getId());
        facultad.setNombre(facultadDTO.getNombre());

        em.persist(facultad);
        transaction.commit();

        System.out.println("✅ Facultad insertada correctamente.");
    } catch (Exception e) {
        if (transaction.isActive()) {
            transaction.rollback();
        }
        System.err.println("❌ Error al insertar facultad: " + e.getMessage());
    } finally {
        em.close();
    }
    }

    public List<FacultadDto> obtenerTodasLasFacultades() {
        EntityManager em = emf.createEntityManager();
        List<FacultadDto> listaFacultadesDTO;

        try {
            List<Facultad> facultades = em.createQuery("SELECT f FROM Facultad f", Facultad.class).getResultList();
            listaFacultadesDTO = facultades.stream().map(f -> new FacultadDto(
                    f.getId(), f.getNombre()
            )).collect(Collectors.toList());
        } finally {
            em.close();
        }

        return listaFacultadesDTO;
    }

    public FacultadDto obtenerFacultadPorId(int id) {
        EntityManager em = emf.createEntityManager();
        FacultadDto facultadDTO = null;

        try {
            Facultad facultad = em.find(Facultad.class, id);
            if (facultad != null) {
                facultadDTO = new FacultadDto(
                        facultad.getId(), facultad.getNombre()
                );
            }
        } finally {
            em.close();
        }

        return facultadDTO;
    }

    public void actualizarFacultad(FacultadDto facultadDTO) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Facultad facultad = em.find(Facultad.class, facultadDTO.getId());
            if (facultad == null) {
                System.err.println("❌ No se encontró la facultad con ID " + facultadDTO.getId());
                return;
            }

            facultad.setNombre(facultadDTO.getNombre());

            em.merge(facultad);
            transaction.commit();

            System.out.println("✅ Facultad actualizada correctamente.");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("❌ Error al actualizar facultad: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void eliminarFacultad(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Facultad facultad = em.find(Facultad.class, id);
            if (facultad == null) {
                System.err.println("❌ No se encontró la facultad con ID " + id);
                return;
            }

            em.remove(facultad);
            transaction.commit();

            System.out.println("✅ Facultad eliminada correctamente.");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("❌ Error al eliminar facultad: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
