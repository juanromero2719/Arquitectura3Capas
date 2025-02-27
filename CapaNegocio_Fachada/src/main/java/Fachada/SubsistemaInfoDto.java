/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fachada;

import ModeloSistemaClientes.*;
import java.util.List;

/**
 *
 * @author juanr
 */
public class SubsistemaInfoDto {
    
    private List<ClaseA> listaA;
    private List<ClaseB> listaB;
    private List<ClaseC> listaC;
    
    public SubsistemaInfoDto(List<ClaseA> listaA, List<ClaseB> listaB, List<ClaseC> listaC) {
        this.listaA = listaA;
        this.listaB = listaB;
        this.listaC = listaC;
    }
    
    // Getters
    public List<ClaseA> getListaA() {
        return listaA;
    }
    
    public List<ClaseB> getListaB() {
        return listaB;
    }
    
    public List<ClaseC> getListaC() {
        return listaC;
    }
    
    // Setters si es necesario
    public void setListaA(List<ClaseA> listaA) {
        this.listaA = listaA;
    }
    
    public void setListaB(List<ClaseB> listaB) {
        this.listaB = listaB;
    }
    
    public void setListaC(List<ClaseC> listaC) {
        this.listaC = listaC;
    }
}
