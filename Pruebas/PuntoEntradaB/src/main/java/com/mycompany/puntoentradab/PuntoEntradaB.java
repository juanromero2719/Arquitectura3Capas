package com.mycompany.puntoentradab;

import DTObject.PersonaDto;
import DaoSistemaClientes.PersonaDAO;
import FabricaSistemaClientes.FabricaPersonaDAO;

import java.util.List;

public class PuntoEntradaB {

    public static void main(String[] args) {
        System.out.println("🔹 Iniciando pruebas del Sistema de Gestión de Personas 🔹");

        // Obtener una instancia de la fábrica de PersonaDAO
        FabricaPersonaDAO fabrica = FabricaPersonaDAO.getInstancia();
        PersonaDAO personaDAO = fabrica.obtenerPersonaDAO();

        // 1️⃣ Crear una nueva persona
        PersonaDto nuevaPersona = new PersonaDto(1001.0, "Juan", "Ramirez", 30);
        personaDAO.guardarPersona(nuevaPersona);

        // 2️⃣ Obtener persona por ID
        System.out.println("\n🔍 Buscando persona con ID 1001...");
        PersonaDto personaRecuperada = personaDAO.obtenerPorId(1001.0);
        if (personaRecuperada != null) {
            System.out.println("✅ Persona encontrada: " + personaRecuperada);
        } else {
            System.out.println("❌ No se encontró la persona.");
        }

        // 3️⃣ Actualizar datos de la persona
        System.out.println("\n✏️ Actualizando persona con ID 1001...");
        nuevaPersona.setNombres("Juan Carlos");
        nuevaPersona.setEdad(35);
        personaDAO.actualizarPersona(nuevaPersona);

        // 4️⃣ Obtener todas las personas
        System.out.println("\n📋 Listando todas las personas registradas:");
        List<PersonaDto> listaPersonas = personaDAO.obtenerTodas();
        if (!listaPersonas.isEmpty()) {
            listaPersonas.forEach(System.out::println);
        } else {
            System.out.println("⚠ No hay personas registradas.");
        }

        // 5️⃣ Eliminar persona por ID
        System.out.println("\n🗑 Eliminando persona con ID 1001...");
        personaDAO.eliminarPersona(1001.0);

        // 6️⃣ Intentar buscar nuevamente la persona eliminada
        System.out.println("\n🔍 Verificando eliminación de la persona...");
        PersonaDto personaEliminada = personaDAO.obtenerPorId(1001.0);
        if (personaEliminada == null) {
            System.out.println("✅ La persona fue eliminada correctamente.");
        } else {
            System.out.println("❌ La persona aún existe en la base de datos.");
        }

        // 7️⃣ Cerrar la conexión con la base de datos
        ConexionSistemaClientes.ConexionBDSistemaClientes.cerrarEntityManagerFactory();

        System.out.println("\n🏁 🔹 Fin de pruebas del Sistema de Gestión de Personas 🔹");
    }
}
