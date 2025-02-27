/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloSistemaClientes;

/**
 *
 * @author juanr
 */
public class ClaseB {
    
    private String destino;
    private String remitente;
    private String mensaje;
    
    public ClaseB(String destino, String mensaje){
        this.destino = destino;
        this.remitente = "***miempresa***";
        this.mensaje = mensaje;
    }
    
    public String enviarMensaje(){
        System.out.println("Informacion enviada al sistema de mensajes.");
        System.out.println("Enviando mensaje: ");
        System.out.println("Destino: " + destino);
        System.out.println("Remitente: " + remitente);
        System.out.println("Mensaje: " + mensaje);
        System.out.println("Mensaje enviado");
        return """
               Informacion enviada al sistema de mensajes.
               Enviando mensaje: 
               Destino: """ + destino + "\n" + "Remitente: " + remitente + "\n" + "Mensaje: " + mensaje + "\n" + "Mensaje enviado"; 
    }
    
    public String mensajeEnviado(){
        return "\nDestino: " + destino + "\nRemitente: " + remitente + "\nMensaje: " + mensaje;
    }
    
    public void mensajeEnviadoFinal(){
        System.out.println("\nDestino: " + destino + "\nRemitente: " + remitente + "\nMensaje: " + mensaje);
    }

    public String getDestino() {
        return destino;
    }

    public String getRemitente() {
        return remitente;
    }

    public String getMensaje() {
        return mensaje;
    }
    
    
}
