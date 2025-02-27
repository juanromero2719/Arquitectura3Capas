package com.mycompany.puntoentradab;

import ModeloSistemaClientes.ClaseA;
import ModeloSistemaClientes.ClaseB;
import ModeloSistemaClientes.ClaseC;
import DaoSistemaClientes.ClaseADao;
import DaoSistemaClientes.ClaseBDao;
import DaoSistemaClientes.ClaseCDao;
import FabricaSistemaClientes.FabricaSistemaClientes;
import java.util.List;

public class PuntoEntradaB {
    
    public static void main(String[] args) {

        // Crear la fábrica de DAOs
        FabricaSistemaClientes fabricaSistemaClientes = new FabricaSistemaClientes();

        // Obtener los DAOs
        ClaseADao claseADAO = fabricaSistemaClientes.getClaseADAO();
        ClaseBDao claseBDAO = fabricaSistemaClientes.getClaseBDAO();
        ClaseCDao claseCDAO = fabricaSistemaClientes.getClaseCDAO();

        // -------------------------------
        // 🔹 Insertar datos en ClaseA
        // -------------------------------
        ClaseA persona = new ClaseA();
        persona.setId(1);
        persona.setNombres("Juan");
        persona.setApellidos("Pérez");

        claseADAO.insertar(persona);
        System.out.println("✅ Se insertó un registro en ClaseA.");

        // -------------------------------
        // 🔹 Insertar datos en ClaseB
        // -------------------------------
        ClaseB mensaje = new ClaseB("cliente@correo.com", "Este es un mensaje de prueba.");
        claseBDAO.insertar(mensaje);
        System.out.println("✅ Se insertó un registro en ClaseB.");

        // -------------------------------
        // 🔹 Insertar datos en ClaseC
        // -------------------------------
        ClaseC configuracion = new ClaseC("Configuración inicial del sistema.");
        claseCDAO.insertar(configuracion);
        System.out.println("✅ Se insertó un registro en ClaseC.");

        // Recuperar y mostrar los registros en consola
        System.out.println("\n--- Registros en ClaseA ---");
        List<ClaseA> listaA = claseADAO.obtenerTodos();
        for (ClaseA a : listaA) {
            System.out.println("ID: " + a.getId() + ", Nombres: " + a.getNombres() + ", Apellidos: " + a.getApellidos());
        }

        System.out.println("\n--- Registros en ClaseB ---");
        List<ClaseB> listaB = claseBDAO.obtenerTodos();
        for (ClaseB b : listaB) {
            // Si la clase ClaseB no cuenta con getters, podemos utilizar el método mensajeEnviado() para mostrar la información.
            System.out.println(b.mensajeEnviado());
        }

        System.out.println("\n--- Registros en ClaseC ---");
        List<ClaseC> listaC = claseCDAO.obtenerTodos();
        for (ClaseC c : listaC) {
            System.out.println("Texto: " + c.getTexto());
        }
    }
}
