package Controlador;

import DTObject.CarreraDto;
import DTObject.EstudianteDto;
import DTObject.FacultadDto;

import FabricaGestorDocumentos.DocumentoFabrica;
import Fachada.FachadaInterface;
import Fachada.SubsistemaInfoDto; 
import Interfaces.Documento;

import Logica.CarreraService;
import Logica.EstudianteService;
import Logica.FacultadService;

import java.util.List;

/**
 *
 * @author juanr
 */
public class Controlador {
    
    private FachadaInterface fachada;
    
    private final CarreraService carreraService = new CarreraService();
    private final EstudianteService estudianteService = new EstudianteService();
    private final FacultadService facultadService = new FacultadService();

    public Controlador(FachadaInterface fachada) {
        this.fachada = fachada;
    }

    public Documento crearDocumento(String contenido, DocumentoFabrica.TipoDocumento tipo) {
        return fachada.crearDocumento(tipo, contenido);
    }

    public String mostrarDocumento(Documento documento) {
        String textoCompleto = fachada.mostrarDocumento(documento);
        return textoCompleto;
    }

    public String imprimirDocumento(Documento documento) {
        String textoCompleto = fachada.imprimirDocumento(documento);
        return textoCompleto;
    }
    
    // -------------------------------------------------------
    // Nuevos métodos para CarreraService
    // -------------------------------------------------------
    
    public void agregarCarrera(String nombre, int facultadId) {
        carreraService.agregarCarrera(nombre, facultadId);
    }

    public List<CarreraDto> obtenerCarreras() {
        return carreraService.obtenerCarreras();
    }

    public void actualizarCarrera(int id, String nuevoNombre, int nuevaFacultadId) {
        carreraService.actualizarCarrera(id, nuevoNombre, nuevaFacultadId);
    }

    public void eliminarCarrera(int id) {
        carreraService.eliminarCarrera(id);
    }

    public CarreraDto obtenerCarreraPorId(int id) {
        return carreraService.obtenerCarreraPorId(id);
    }
    
    // -------------------------------------------------------
    // Nuevos métodos para EstudianteService
    // -------------------------------------------------------
    
    public void agregarEstudiante(Long identificacion, String nombres, String apellidos, int edad, int carreraId) {
        estudianteService.agregarEstudiante(identificacion, nombres, apellidos, edad, carreraId);
    }

    public List<EstudianteDto> obtenerEstudiantes() {
        return estudianteService.obtenerEstudiantes();
    }

    public EstudianteDto obtenerEstudiantePorId(Long identificacion) {
        return estudianteService.obtenerEstudiantePorId(identificacion);
    }

    public void actualizarEstudiante(Long identificacion, String nombres, String apellidos, int edad, int nuevaCarreraId) {
        estudianteService.actualizarEstudiante(identificacion, nombres, apellidos, edad, nuevaCarreraId);
    }

    public void eliminarEstudiante(Long identificacion) {
        estudianteService.eliminarEstudiante(identificacion);
    }

    // -------------------------------------------------------
    // Nuevos métodos para FacultadService
    // -------------------------------------------------------
    
    public void agregarFacultad(String nombre) {
        facultadService.agregarFacultad(nombre);
    }

    public List<FacultadDto> obtenerFacultades() {
        return facultadService.obtenerFacultades();
    }

    public void actualizarFacultad(int id, String nuevoNombre) {
        facultadService.actualizarFacultad(id, nuevoNombre);
    }

    public void eliminarFacultad(int id) {
        facultadService.eliminarFacultad(id);
    }

    public FacultadDto obtenerFacultadPorId(int id) {
        return facultadService.obtenerFacultadPorId(id);
    }
    
    // -------------------------------------------------------
    // Nuevos métodos para Subsistemas
    // -------------------------------------------------------
    
    public String enviarInformacionSubSistemas(double id, String nombres, String apellidos, String email_destino, String texto_mensaje) {
        return fachada.enviarInformacionSubSistemas(id, nombres, apellidos, email_destino, texto_mensaje);
    }
    
    public SubsistemaInfoDto obtenerInformacionSubsistemas() {
        return fachada.informacionEnviadaSubsistemas();
    }
}
