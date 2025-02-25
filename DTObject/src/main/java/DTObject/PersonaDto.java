/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTObject;

/**
 *
 * @author juanr
 */
public class PersonaDto {
    
    private Double identificacion;
    private String nombres;
    private String apellidos;
    private int edad;

    public PersonaDto(Double identificacion, String nombres, String apellidos, int edad) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    public Double getIdentificacion() {
        return identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }
    
    public int getEdad() {
        return edad;
    }

    public void setIdentificacion(Double identificacion) {
        this.identificacion = identificacion;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    

    @Override
    public String toString() {
        return "PersonaDTO{" + "identificacion=" + identificacion + ", nombres=" + nombres + ", apellidos=" + apellidos + ", edad=" + edad + '}';
    }
    
}

// comentario
