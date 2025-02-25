
package DocumentoTextoPlano;

import Interfaces.Documento;


public class DocumentoTexto implements Documento {
    private String contenido;
   
    @Override
    public void setContenido(String contenido) {
        this.contenido=contenido;
    }
   
    @Override
    public String dibujar() {
        System.out.println("Dibujando Documento plano en la aplicación." + contenido);
        return "Dibujando Documento plano en la aplicación." + contenido;
    }
   
    @Override
    public String imprimir() {
        System.out.println("Imprimiendo Documento plano." + contenido);
        return "Imprimiendo Documento plano." + contenido;
    }    
}
