package DaoPersistencia;

import ConexionPersistencia.ConexionBDPersistencia;
import DTObject.EstudianteDto;
import ModeloEntidades.Carrera;
import ModeloEntidades.Estudiante;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

public class EstudianteDAO {
    private static final EntityManagerFactory emf = ConexionBDPersistencia.getEntityManagerFactory();

    public void insertarEstudiante(EstudianteDto estudianteDTO) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Carrera carrera = em.find(Carrera.class, estudianteDTO.getCarreraId());
            if (carrera == null) {
                System.err.println("❌ No existe la carrera con ID " + estudianteDTO.getCarreraId());
                return;
            }

            Estudiante estudiante = new Estudiante();
            estudiante.setIdentificacion(estudianteDTO.getIdentificacion());
            estudiante.setNombres(estudianteDTO.getNombres());
            estudiante.setApellidos(estudianteDTO.getApellidos());
            estudiante.setEdad(estudianteDTO.getEdad());
            estudiante.setCarrera(carrera);

            em.persist(estudiante);
            transaction.commit();

            System.out.println("✅ Estudiante insertado correctamente.");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("❌ Error al insertar estudiante: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<EstudianteDto> obtenerTodosLosEstudiantes() {
        EntityManager em = emf.createEntityManager();
        List<EstudianteDto> listaEstudiantesDTO;

        try {
            List<Estudiante> estudiantes = em.createQuery("SELECT e FROM Estudiante e", Estudiante.class).getResultList();
            listaEstudiantesDTO = estudiantes.stream().map(e -> new EstudianteDto(
                    e.getIdentificacion(), e.getNombres(), e.getApellidos(), e.getEdad(), e.getCarrera().getId()
            )).collect(Collectors.toList());
        } finally {
            em.close();
        }

        return listaEstudiantesDTO;
    }

    public EstudianteDto obtenerEstudiantePorId(Long identificacion) {
        EntityManager em = emf.createEntityManager();
        EstudianteDto estudianteDTO = null;

        try {
            Estudiante estudiante = em.find(Estudiante.class, identificacion);
            if (estudiante != null) {
                estudianteDTO = new EstudianteDto(
                        estudiante.getIdentificacion(), estudiante.getNombres(), estudiante.getApellidos(),
                        estudiante.getEdad(), estudiante.getCarrera().getId()
                );
            }
        } finally {
            em.close();
        }

        return estudianteDTO;
    }

    public void actualizarEstudiante(EstudianteDto estudianteDTO) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Estudiante estudiante = em.find(Estudiante.class, estudianteDTO.getIdentificacion());
            if (estudiante == null) {
                System.err.println("❌ No se encontró el estudiante con ID " + estudianteDTO.getIdentificacion());
                return;
            }

            estudiante.setNombres(estudianteDTO.getNombres());
            estudiante.setApellidos(estudianteDTO.getApellidos());
            estudiante.setEdad(estudianteDTO.getEdad());

            Carrera nuevaCarrera = em.find(Carrera.class, estudianteDTO.getCarreraId());
            if (nuevaCarrera == null) {
                System.err.println("❌ No existe la carrera con ID " + estudianteDTO.getCarreraId());
                return;
            }
            estudiante.setCarrera(nuevaCarrera);

            em.merge(estudiante);
            transaction.commit();

            System.out.println("✅ Estudiante actualizado correctamente.");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("❌ Error al actualizar estudiante: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void eliminarEstudiante(Long identificacion) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Estudiante estudiante = em.find(Estudiante.class, identificacion);
            if (estudiante == null) {
                System.err.println("❌ No se encontró el estudiante con ID " + identificacion);
                return;
            }

            em.remove(estudiante);
            transaction.commit();

            System.out.println("✅ Estudiante eliminado correctamente.");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("❌ Error al eliminar estudiante: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
