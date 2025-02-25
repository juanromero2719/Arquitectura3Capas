/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import DTObject.EstudianteDto;
import DaoPersistencia.EstudianteDAO;
import java.util.List;

public class EstudianteService {
    private final EstudianteDAO estudianteDAO = new EstudianteDAO();

    // Método para agregar un nuevo estudiante
    public void agregarEstudiante(Long identificacion, String nombres, String apellidos, int edad, int carreraId) {
        EstudianteDto nuevoEstudiante = new EstudianteDto(identificacion, nombres, apellidos, edad, carreraId);
        estudianteDAO.insertarEstudiante(nuevoEstudiante);
    }

    // Método para obtener todos los estudiantes
    public List<EstudianteDto> obtenerEstudiantes() {
        return estudianteDAO.obtenerTodosLosEstudiantes();
    }

    // Método para obtener un estudiante por ID
    public EstudianteDto obtenerEstudiantePorId(Long identificacion) {
        return estudianteDAO.obtenerEstudiantePorId(identificacion);
    }

    // Método para actualizar un estudiante
    public void actualizarEstudiante(Long identificacion, String nombres, String apellidos, int edad, int nuevaCarreraId) {
        EstudianteDto estudianteActualizado = new EstudianteDto(identificacion, nombres, apellidos, edad, nuevaCarreraId);
        estudianteDAO.actualizarEstudiante(estudianteActualizado);
    }

    // Método para eliminar un estudiante
    public void eliminarEstudiante(Long identificacion) {
        estudianteDAO.eliminarEstudiante(identificacion);
    }
}

