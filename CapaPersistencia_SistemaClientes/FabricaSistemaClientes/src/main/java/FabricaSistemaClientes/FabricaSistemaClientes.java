/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FabricaSistemaClientes;

import DaoSistemaClientes.*;

public class FabricaSistemaClientes {

    private final ClaseADao claseADAO;
    private final ClaseBDao claseBDAO;
    private final ClaseCDao claseCDAO;

    public FabricaSistemaClientes() {

        this.claseADAO = new ClaseADao();
        this.claseBDAO = new ClaseBDao();
        this.claseCDAO = new ClaseCDao();
    }

    public ClaseADao getClaseADAO() {
        return claseADAO;
    }

    public ClaseBDao getClaseBDAO() {
        return claseBDAO;
    }

    public ClaseCDao getClaseCDAO() {
        return claseCDAO;
    }
}
