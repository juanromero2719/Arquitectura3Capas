/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Fachada;


import FabricaGestorDocumentos.DocumentoFabrica;
import Interfaces.Documento;


/**
 *
 * @author juanr
 */

public interface FachadaInterface {
    
    Documento crearDocumento(DocumentoFabrica.TipoDocumento tipo, String contenido);
    
    String mostrarDocumento(Documento documento);
    
    String imprimirDocumento(Documento documento);
    
    String enviarInformacionSubSistemas( double id, String nombres, String apellidos, String email_destino, String texto_mensaje);
    
    SubsistemaInfoDto informacionEnviadaSubsistemas();
    
}
