/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTObject;

/**
 *
 * @author juanr
 */
public class CarreraDto {
    private int id;
    private String nombre;
    private int facultadId; // Referencia a Facultad

    // Constructor vacío
    public CarreraDto() {}

    // Constructor con parámetros
    public CarreraDto(int id, String nombre, int facultadId) {
        this.id = id;
        this.nombre = nombre;
        this.facultadId = facultadId;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFacultadId() {
        return facultadId;
    }

    public void setFacultadId(int facultadId) {
        this.facultadId = facultadId;
    }

    @Override
    public String toString() {
        return "CarreraDTO{" + "id=" + id + ", nombre='" + nombre + '\'' + ", facultadId=" + facultadId + '}';
    }

}
