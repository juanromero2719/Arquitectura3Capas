package Fachada;


import DTObject.PersonaDto;
import DaoSistemaClientes.PersonaDAO;
import FabricaGestorDocumentos.DocumentoBuilder;
import FabricaGestorDocumentos.DocumentoFabrica;
import FabricaSistemaClientes.FabricaPersonaDAO;
import Interfaces.Documento;
import java.util.List;

public class Fachada implements FachadaInterface {

    private PersonaDAO personaDAO;
    
    public Fachada() {
        personaDAO = FabricaPersonaDAO.getInstancia().obtenerPersonaDAO();
    }
    
    @Override
    public Documento crearDocumento(DocumentoFabrica.TipoDocumento tipo, String contenido) {
        return new DocumentoBuilder()
                .conTipo(tipo)
                .conContenido(contenido)
                .build();
    }

    @Override
    public String mostrarDocumento(Documento documento) {
        String textoCompleto = documento.dibujar();
        return textoCompleto;
    }

    @Override
    public String imprimirDocumento(Documento documento) {
        String textoCompleto = documento.imprimir();
        return textoCompleto;
    }
    
    @Override
    public PersonaDto obtenerPersonaPorId(Double id) {
        return personaDAO.obtenerPorId(id);
    }

    @Override
    public void guardarPersona(PersonaDto personaDTO) {
        personaDAO.guardarPersona(personaDTO);
    }

    @Override
    public void actualizarPersona(PersonaDto personaDTO) {
        personaDAO.actualizarPersona(personaDTO);
    }

    @Override
    public void eliminarPersona(Double id) {
        personaDAO.eliminarPersona(id);
    }

    @Override
    public List<PersonaDto> obtenerTodasPersonas() {
        return personaDAO.obtenerTodas();
    }
    
}
