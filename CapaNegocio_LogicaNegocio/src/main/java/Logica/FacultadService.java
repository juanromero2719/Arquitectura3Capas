package Logica;

import DTObject.FacultadDto;
import DaoPersistencia.FacultadDAO;
import java.util.List;

public class FacultadService {
    private final FacultadDAO facultadDAO = new FacultadDAO();

    // Método para agregar una nueva facultad
    public void agregarFacultad(String nombre) {
        FacultadDto nuevaFacultad = new FacultadDto(1, nombre);
        facultadDAO.insertarFacultad(nuevaFacultad);
    }

    // Método para obtener todas las facultades
    public List<FacultadDto> obtenerFacultades() {
        return facultadDAO.obtenerTodasLasFacultades();
    }

    // Método para actualizar una facultad
    public void actualizarFacultad(int id, String nuevoNombre) {
        FacultadDto facultadActualizada = new FacultadDto(id, nuevoNombre);
        facultadDAO.actualizarFacultad(facultadActualizada);
    }

    // Método para eliminar una facultad
    public void eliminarFacultad(int id) {
        facultadDAO.eliminarFacultad(id);
    }

    // Método para obtener una facultad por ID
    public FacultadDto obtenerFacultadPorId(int id) {
        return facultadDAO.obtenerFacultadPorId(id);
    }
}
