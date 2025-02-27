package com.mycompany.pruebaentradad;

import Controlador.Controlador;
import DTObject.CarreraDto;
import DTObject.EstudianteDto;
import DTObject.FacultadDto;
import Fachada.Fachada;
import FabricaGestorDocumentos.DocumentoFabrica;
import Fachada.SubsistemaInfoDto;
import Interfaces.Documento;
import java.util.List;

public class PruebaEntradaD {

    public static void main(String[] args) {
        System.out.println("🔸 Iniciando pruebas de Controlador 🔸\n");

        // 1. Crear instancia de Controlador con la Fachada
        Controlador controlador = new Controlador(new Fachada());

        // ============= PRUEBAS SOBRE DOCUMENTOS =================
        System.out.println("➡️  PRUEBAS PARA DOCUMENTO\n");

        // 6. Crear un documento PDF, mostrarlo e imprimirlo
        Documento docPDF = controlador.crearDocumento("Contenido de mi PDF", DocumentoFabrica.TipoDocumento.PDF);
        System.out.println("Dibujando Documento PDF:");
        controlador.mostrarDocumento(docPDF);  // dibujar()
        System.out.println("Imprimiendo Documento PDF:");
        controlador.imprimirDocumento(docPDF); // imprimir()

        // 7. Crear un documento HTML, mostrarlo e imprimirlo
        Documento docHTML = controlador.crearDocumento("Contenido HTML", DocumentoFabrica.TipoDocumento.HTML);
        System.out.println("\nDibujando Documento HTML:");
        String textoCompleto = controlador.mostrarDocumento(docHTML);
        System.out.println("Imprimiendo Documento HTML:");
        controlador.imprimirDocumento(docHTML);

        // 8. Crear un documento de Texto Plano, mostrarlo e imprimirlo
        Documento docTexto = controlador.crearDocumento("Contenido de Texto Plano", DocumentoFabrica.TipoDocumento.TEXTO_PLANO);
        System.out.println("\nDibujando Documento Texto Plano:");
        controlador.mostrarDocumento(docTexto);
        System.out.println("Imprimiendo Documento Texto Plano:");
        controlador.imprimirDocumento(docTexto);
        System.out.println();

        // ============= PRUEBAS SOBRE CARRERA =================
        System.out.println("➡️  PRUEBAS PARA CARRERA\n");

        // 9. Agregar carrera
        controlador.agregarCarrera("Ingeniería de Sistemas", 1);
        controlador.agregarCarrera("Ingeniería Industrial", 2);

        // 10. Listar carreras
        List<CarreraDto> listaCarreras = controlador.obtenerCarreras();
        System.out.println("Lista de todas las carreras:");
        listaCarreras.forEach(System.out::println);

        // 11. Obtener carrera por ID (asumiendo que la ID que pones coincide con la base de datos)
        if (!listaCarreras.isEmpty()) {
            int carreraId = listaCarreras.get(0).getId(); 
            CarreraDto carreraObtenida = controlador.obtenerCarreraPorId(carreraId);
            System.out.println("Carrera obtenida por ID: " + carreraObtenida);

            // 12. Actualizar la carrera
            controlador.actualizarCarrera(carreraId, "Ingeniería de Sistemas y Computación", 1);
            System.out.println("Carrera actualizada. Nueva lista de carreras:");
            controlador.obtenerCarreras().forEach(System.out::println);

            // 13. Eliminar la carrera
            controlador.eliminarCarrera(carreraId);
            System.out.println("Carrera eliminada. Nueva lista de carreras:");
            controlador.obtenerCarreras().forEach(System.out::println);
        }
        System.out.println();

        // ============= PRUEBAS SOBRE ESTUDIANTE =================
        System.out.println("➡️  PRUEBAS PARA ESTUDIANTE\n");

        // 14. Agregar estudiante
        controlador.agregarEstudiante(2001L, "Maria", "Lopez", 20, 1);
        controlador.agregarEstudiante(2002L, "Carlos", "Rojas", 21, 2);

        // 15. Listar estudiantes
        List<EstudianteDto> listaEstudiantes = controlador.obtenerEstudiantes();
        System.out.println("Lista de todos los estudiantes:");
        listaEstudiantes.forEach(System.out::println);

        // 16. Obtener estudiante por ID
        EstudianteDto estudianteObtenido = controlador.obtenerEstudiantePorId(2001L);
        System.out.println("Estudiante obtenido por ID (2001): " + estudianteObtenido);

        // 17. Actualizar estudiante
        if (estudianteObtenido != null) {
            controlador.actualizarEstudiante(2001L, "Maria Camila", "Lopez", 22, 2);
            System.out.println("Estudiante actualizado. Nueva lista de estudiantes:");
            controlador.obtenerEstudiantes().forEach(System.out::println);
        }

        // 18. Eliminar estudiante
        controlador.eliminarEstudiante(2002L);
        System.out.println("Estudiante con ID 2002 eliminado. Nueva lista de estudiantes:");
        controlador.obtenerEstudiantes().forEach(System.out::println);
        System.out.println();

        // ============= PRUEBAS SOBRE FACULTAD =================
        System.out.println("➡️  PRUEBAS PARA FACULTAD\n");

        // 19. Agregar facultad
        controlador.agregarFacultad("Facultad de Ingeniería");
        controlador.agregarFacultad("Facultad de Ciencias");

        // 20. Listar facultades
        List<FacultadDto> listaFacultades = controlador.obtenerFacultades();
        System.out.println("Lista de todas las facultades:");
        listaFacultades.forEach(System.out::println);

        // 21. Obtener facultad por ID
        if (!listaFacultades.isEmpty()) {
            int facultadId = listaFacultades.get(0).getId();
            FacultadDto facultadObtenida = controlador.obtenerFacultadPorId(facultadId);
            System.out.println("Facultad obtenida por ID: " + facultadObtenida);

            // 22. Actualizar facultad
            controlador.actualizarFacultad(facultadId, "Facultad de Ingeniería y Arquitectura");
            System.out.println("Facultad actualizada. Nueva lista de facultades:");
            controlador.obtenerFacultades().forEach(System.out::println);

            // 23. Eliminar facultad
            controlador.eliminarFacultad(facultadId);
            System.out.println("Facultad eliminada. Nueva lista de facultades:");
            controlador.obtenerFacultades().forEach(System.out::println);
        }
        
        // ============= PRUEBAS SOBRE SUBSISTEMAS =================
        System.out.println("\n➡️  PRUEBAS PARA SUBSISTEMAS\n");
        
        // 24. Enviar información a subsistemas
        String resultadoEnvio = controlador.enviarInformacionSubSistemas(200, "Juan", "Gomez", "destino@correo.com", "Mensaje de prueba para subsistemas.");
        System.out.println("Resultado del envío a subsistemas:");
        System.out.println(resultadoEnvio);
        
        // 25. Obtener información de subsistemas (tres listas agrupadas en el DTO SubsistemasInfo)
        SubsistemaInfoDto infoSubsistemas = controlador.obtenerInformacionSubsistemas();
        System.out.println("\nInformación obtenida de subsistemas:");
        
        System.out.println("\n[ClaseA]");
        infoSubsistemas.getListaA().forEach(a -> 
                System.out.println("ID: " + a.getId() + ", Nombres: " + a.getNombres() + ", Apellidos: " + a.getApellidos()));
        
        System.out.println("\n[ClaseB]");
        infoSubsistemas.getListaB().forEach(b -> 
                System.out.println(b.mensajeEnviado()));
        
        System.out.println("\n[ClaseC]");
        infoSubsistemas.getListaC().forEach(c -> 
                System.out.println("Texto: " + c.getTexto()));
        
        System.out.println("\n🔸 Fin de las pruebas del Controlador 🔸");
    }
}
