/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloSistemaClientes;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author juanr
 */

@Entity
@Table(name = "persona")
public class Persona {
    
    @Id
    private Double identificacion;
    private String nombres;
    private String apellidos;
    private int edad;

    public Persona() {}

    public Persona(Double identificacion, String nombres, String apellidos, int edad) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    public Double getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Double identificacion) {
        this.identificacion = identificacion;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona{" + "identificacion=" + identificacion + ", nombres=" + nombres + ", apellidos=" + apellidos + ", edad=" + edad + '}';
    }
}