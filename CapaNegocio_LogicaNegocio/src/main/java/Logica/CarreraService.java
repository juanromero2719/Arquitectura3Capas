/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;


import DTObject.CarreraDto;
import DaoPersistencia.CarreraDAO;
import java.util.List;

public class CarreraService {
    private final CarreraDAO carreraDAO = new CarreraDAO();

    // Método para agregar una nueva carrera
    public void agregarCarrera(String nombre, int facultadId) {
        CarreraDto nuevaCarrera = new CarreraDto(1, nombre, facultadId);
        carreraDAO.insertarCarrera(nuevaCarrera);
    }

    // Método para obtener todas las carreras
    public List<CarreraDto> obtenerCarreras() {
        return carreraDAO.obtenerTodasLasCarreras();
    }

    // Método para actualizar una carrera
    public void actualizarCarrera(int id, String nuevoNombre, int nuevaFacultadId) {
        CarreraDto carreraActualizada = new CarreraDto(id, nuevoNombre, nuevaFacultadId);
        carreraDAO.actualizarCarrera(carreraActualizada);
    }

    // Método para eliminar una carrera
    public void eliminarCarrera(int id) {
        carreraDAO.eliminarCarrera(id);
    }

    // Método para obtener una carrera por ID
    public CarreraDto obtenerCarreraPorId(int id) {
        return carreraDAO.obtenerCarreraPorId(id);
    }
}
