/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Fachada;


import DTObject.PersonaDto;
import FabricaGestorDocumentos.DocumentoFabrica;
import Interfaces.Documento;
import java.util.List;

/**
 *
 * @author juanr
 */



public interface FachadaInterface {
    
    Documento crearDocumento(DocumentoFabrica.TipoDocumento tipo, String contenido);
    
    String mostrarDocumento(Documento documento);
    
    String imprimirDocumento(Documento documento);
    
    PersonaDto obtenerPersonaPorId(Double id);
    
    void guardarPersona(PersonaDto personaDTO);
    
    void actualizarPersona(PersonaDto personaDTO);
    
    void eliminarPersona(Double id);
    
    List<PersonaDto> obtenerTodasPersonas();
    
}
