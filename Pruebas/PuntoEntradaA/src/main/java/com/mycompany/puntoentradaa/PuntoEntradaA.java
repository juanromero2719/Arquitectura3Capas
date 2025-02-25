package com.mycompany.puntoentradaa;

import DaoPersistencia.FacultadDAO;
import DaoPersistencia.CarreraDAO;
import DaoPersistencia.EstudianteDAO;
import DTObject.FacultadDto;
import DTObject.CarreraDto;
import DTObject.EstudianteDto;
import ConexionPersistencia.ConexionBDPersistencia;

import java.util.List;

public class PuntoEntradaA {
    public static void main(String[] args) {
        // Instancias de los DAO
        FacultadDAO facultadDAO = new FacultadDAO();
        CarreraDAO carreraDAO = new CarreraDAO();
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        
        try {
            System.out.println("=== PRUEBAS DE FACULTAD ===");

            // 1. Insertar facultad
            FacultadDto nuevaFacultad = new FacultadDto(0, "Facultad de Ingeniería");
            facultadDAO.insertarFacultad(nuevaFacultad);

            // 2. Obtener todas las facultades
            List<FacultadDto> facultades = facultadDAO.obtenerTodasLasFacultades();
            System.out.println("Lista de facultades después de insertar:");
            for (FacultadDto f : facultades) {
                System.out.println(" - " + f);
            }

            // Suponiendo que la primera facultad insertada tiene ID=1 en la base de datos
            // 3. Obtener facultad por ID
            FacultadDto facultadObtenida = facultadDAO.obtenerFacultadPorId(1);
            if (facultadObtenida != null) {
                System.out.println("Facultad obtenida por ID=1: " + facultadObtenida);

                // 4. Actualizar la facultad
                facultadObtenida.setNombre("Facultad de Ingeniería y Tecnología");
                facultadDAO.actualizarFacultad(facultadObtenida);

                // 5. Verificar la actualización
                FacultadDto facultadActualizada = facultadDAO.obtenerFacultadPorId(1);
                System.out.println("Facultad actualizada: " + facultadActualizada);
            } else {
                System.out.println("No se encontró la facultad con ID=1 (verifica la DB).");
            }

            // 6. Eliminar la facultad con ID=1
            facultadDAO.eliminarFacultad(1);

            // 7. Verificar la eliminación
            FacultadDto facultadEliminada = facultadDAO.obtenerFacultadPorId(1);
            System.out.println("Facultad luego de eliminar (debe ser null): " + facultadEliminada);
            
            System.out.println("\n=== PRUEBAS DE CARRERA ===");

            // Para probar Carrera, necesitamos una facultad existente
            // Insertamos una nueva facultad (ID se generará automáticamente)
            FacultadDto facultadParaCarrera = new FacultadDto(0, "Facultad de Ciencias");
            facultadDAO.insertarFacultad(facultadParaCarrera);

            // Obtenemos la lista de facultades para ubicar la ID de la recién creada
            facultades = facultadDAO.obtenerTodasLasFacultades();
            int facultadId = -1;
            if (!facultades.isEmpty()) {
                // Tomamos la primera o buscamos la que acabamos de crear
                facultadId = facultades.get(0).getId();
            }

            // 1. Insertar carrera asociada a la facultad
            if (facultadId != -1) {
                CarreraDto nuevaCarrera = new CarreraDto(0, "Licenciatura en Física", facultadId);
                carreraDAO.insertarCarrera(nuevaCarrera);
            }

            // 2. Obtener todas las carreras
            List<CarreraDto> carreras = carreraDAO.obtenerTodasLasCarreras();
            System.out.println("Lista de carreras después de insertar:");
            for (CarreraDto c : carreras) {
                System.out.println(" - " + c);
            }

            // Suponiendo que la carrera insertada tiene ID=1 (dependerá de tu DB)
            // 3. Obtener carrera por ID
            CarreraDto carreraObtenida = carreraDAO.obtenerCarreraPorId(1);
            if (carreraObtenida != null) {
                System.out.println("Carrera obtenida por ID=1: " + carreraObtenida);

                // 4. Actualizar la carrera
                carreraObtenida.setNombre("Licenciatura en Física Teórica");
                carreraDAO.actualizarCarrera(carreraObtenida);

                // 5. Verificar la actualización
                CarreraDto carreraActualizada = carreraDAO.obtenerCarreraPorId(1);
                System.out.println("Carrera actualizada: " + carreraActualizada);
            } else {
                System.out.println("No se encontró la carrera con ID=1 (verifica la DB).");
            }

            // 6. Eliminar la carrera con ID=1
            carreraDAO.eliminarCarrera(1);

            // 7. Verificar la eliminación
            CarreraDto carreraEliminada = carreraDAO.obtenerCarreraPorId(1);
            System.out.println("Carrera luego de eliminar (debe ser null): " + carreraEliminada);

            System.out.println("\n=== PRUEBAS DE ESTUDIANTE ===");

            // Para probar Estudiante, necesitamos que exista (y no se elimine) una facultad y una carrera
            // Crearemos de nuevo la facultad y la carrera
            FacultadDto facultadEstudiante = new FacultadDto(0, "Facultad de Matemáticas");
            facultadDAO.insertarFacultad(facultadEstudiante);

            // Obtenemos la nueva facultad para encontrar su ID
            facultades = facultadDAO.obtenerTodasLasFacultades();
            facultadId = facultades.get(0).getId(); // Podrías refinar la lógica para buscar por nombre si hay varias

            // Insertamos la carrera asociada a esa facultad
            CarreraDto carreraEstudiante = new CarreraDto(0, "Matemáticas Puras", facultadId);
            carreraDAO.insertarCarrera(carreraEstudiante);

            // Obtenemos la nueva carrera para encontrar su ID
            carreras = carreraDAO.obtenerTodasLasCarreras();
            int carreraId = carreras.get(0).getId(); // Igual, podrías refinar por nombre

            // 1. Insertar un nuevo estudiante
            EstudianteDto nuevoEstudiante = new EstudianteDto(12345678L, "Carlos", "Gómez", 20, carreraId);
            estudianteDAO.insertarEstudiante(nuevoEstudiante);

            // 2. Obtener todos los estudiantes
            List<EstudianteDto> estudiantes = estudianteDAO.obtenerTodosLosEstudiantes();
            System.out.println("Lista de estudiantes después de insertar:");
            for (EstudianteDto e : estudiantes) {
                System.out.println(" - " + e);
            }

            // 3. Obtener estudiante por identificación
            EstudianteDto estudianteObtenido = estudianteDAO.obtenerEstudiantePorId(12345678L);
            System.out.println("Estudiante obtenido (ID=12345678): " + estudianteObtenido);

            // 4. Actualizar estudiante
            if (estudianteObtenido != null) {
                estudianteObtenido.setNombres("Carlos Andrés");
                estudianteObtenido.setApellidos("Gómez Martínez");
                estudianteObtenido.setEdad(21);
                estudianteDAO.actualizarEstudiante(estudianteObtenido);

                // Verificar actualización
                EstudianteDto estudianteActualizado = estudianteDAO.obtenerEstudiantePorId(12345678L);
                System.out.println("Estudiante actualizado: " + estudianteActualizado);
            }

            // 5. Eliminar estudiante
            estudianteDAO.eliminarEstudiante(12345678L);

            // 6. Verificar eliminación
            EstudianteDto estudianteEliminado = estudianteDAO.obtenerEstudiantePorId(12345678L);
            System.out.println("Estudiante luego de eliminar (debe ser null): " + estudianteEliminado);

        } finally {
            // IMPORTANTE: cerrar el EntityManagerFactory para liberar recursos
            ConexionBDPersistencia.closeEntityManagerFactory();
        }
    }
}
