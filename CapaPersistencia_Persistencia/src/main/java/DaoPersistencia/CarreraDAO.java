package DaoPersistencia;

import ConexionPersistencia.ConexionBDPersistencia;
import DTObject.CarreraDto;
import ModeloEntidades.Carrera;
import ModeloEntidades.Facultad;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

public class CarreraDAO {
    private static final EntityManagerFactory emf = ConexionBDPersistencia.getEntityManagerFactory();

    public void insertarCarrera(CarreraDto carreraDTO) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();

    try {
        transaction.begin();

        Facultad facultad = em.find(Facultad.class, carreraDTO.getFacultadId());
        if (facultad == null) {
            System.err.println("❌ No existe la facultad con ID " + carreraDTO.getFacultadId());
            return;
        }

        Carrera carrera = new Carrera();
        // No se asigna el ID porque es autoincremental
        // carrera.setId(carreraDTO.getId());
        carrera.setNombre(carreraDTO.getNombre());
        carrera.setFacultad(facultad);

        em.persist(carrera); // Insertar en la base de datos
        transaction.commit();

        System.out.println("✅ Carrera insertada correctamente.");
    } catch (Exception e) {
        if (transaction.isActive()) {
            transaction.rollback();
        }
        System.err.println("❌ Error al insertar carrera: " + e.getMessage());
    } finally {
        em.close();
    }
    }

    public List<CarreraDto> obtenerTodasLasCarreras() {
        EntityManager em = emf.createEntityManager();
        List<CarreraDto> listaCarrerasDTO;

        try {
            List<Carrera> carreras = em.createQuery("SELECT c FROM Carrera c", Carrera.class).getResultList();
            listaCarrerasDTO = carreras.stream().map(c -> new CarreraDto(
                    c.getId(), c.getNombre(), c.getFacultad().getId()
            )).collect(Collectors.toList());
        } finally {
            em.close();
        }

        return listaCarrerasDTO;
    }

    public CarreraDto obtenerCarreraPorId(int id) {
        EntityManager em = emf.createEntityManager();
        CarreraDto carreraDTO = null;

        try {
            Carrera carrera = em.find(Carrera.class, id);
            if (carrera != null) {
                carreraDTO = new CarreraDto(
                        carrera.getId(), carrera.getNombre(), carrera.getFacultad().getId()
                );
            }
        } finally {
            em.close();
        }

        return carreraDTO;
    }

    public void actualizarCarrera(CarreraDto carreraDTO) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Carrera carrera = em.find(Carrera.class, carreraDTO.getId());
            if (carrera == null) {
                System.err.println("❌ No se encontró la carrera con ID " + carreraDTO.getId());
                return;
            }

            carrera.setNombre(carreraDTO.getNombre());

            Facultad nuevaFacultad = em.find(Facultad.class, carreraDTO.getFacultadId());
            if (nuevaFacultad == null) {
                System.err.println("❌ No existe la facultad con ID " + carreraDTO.getFacultadId());
                return;
            }
            carrera.setFacultad(nuevaFacultad);

            em.merge(carrera);
            transaction.commit();

            System.out.println("✅ Carrera actualizada correctamente.");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("❌ Error al actualizar carrera: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void eliminarCarrera(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Carrera carrera = em.find(Carrera.class, id);
            if (carrera == null) {
                System.err.println("❌ No se encontró la carrera con ID " + id);
                return;
            }

            em.remove(carrera);
            transaction.commit();

            System.out.println("✅ Carrera eliminada correctamente.");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("❌ Error al eliminar carrera: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
