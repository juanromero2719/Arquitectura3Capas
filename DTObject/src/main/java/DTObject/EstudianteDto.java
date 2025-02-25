/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTObject;

/**
 *
 * @author juanr
 */
public class EstudianteDto {
    
     private Long identificacion;
    private String nombres;
    private String apellidos;
    private int edad;
    private int carreraId; // Referencia a Carrera

    // Constructor vacío
    public EstudianteDto() {}

    // Constructor con parámetros
    public EstudianteDto(Long identificacion, String nombres, String apellidos, int edad, int carreraId) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.carreraId = carreraId;
    }

    // Getters y Setters
    public Long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Long identificacion) {
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

    public int getCarreraId() {
        return carreraId;
    }

    public void setCarreraId(int carreraId) {
        this.carreraId = carreraId;
    }

    @Override
    public String toString() {
        return "EstudianteDTO{" + "identificacion=" + identificacion + ", nombres='" + nombres + '\'' + ", apellidos='" + apellidos + '\'' + ", edad=" + edad + ", carreraId=" + carreraId + '}';
    }

}
