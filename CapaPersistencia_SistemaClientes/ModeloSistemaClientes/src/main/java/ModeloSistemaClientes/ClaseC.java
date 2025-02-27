/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloSistemaClientes;

/**
 *
 * @author juanr
 */
public class ClaseC {
    
    private String texto;
    
    public ClaseC(String texto){
        this.texto = texto;
    }
    
    public String procesarInformacion(){
        System.out.println("Informacion procesada para configuracion");
        System.out.println("Correo electronico y demas configuraciones creadas: " + texto.toUpperCase());
        return """
               Informacion procesada para configuracion
               Correo electronico y demas configuraciones creadas: """ + texto.toUpperCase();
    }
    
    public String getTexto(){
        return texto.toUpperCase();
    }
    
}
