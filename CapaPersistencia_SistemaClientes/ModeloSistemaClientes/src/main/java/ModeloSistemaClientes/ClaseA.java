/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloSistemaClientes;

/**
 *
 * @author juanr
 */
public class ClaseA {
    
     private double id;
    private String nombres;
    private String apellidos;
    
    public String cargarInformacionTerceros(){
        System.out.println("Informacion enviada al sistema contable.");
        return "Informacion enviada al sistema contable.";
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
}
