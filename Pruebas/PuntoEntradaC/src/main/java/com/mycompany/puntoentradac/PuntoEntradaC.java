package com.mycompany.puntoentradac;

import FabricaGestorDocumentos.DocumentoBuilder;
import FabricaGestorDocumentos.DocumentoFabrica;
import Interfaces.Documento;

public class PuntoEntradaC {

    public static void main(String[] args) {
        System.out.println("--------------------------------------");
        System.out.println("Pruebas del Sistema Gestor Documentos");
        System.out.println("--------------------------------------");
        
        // Prueba de documento PDF
        Documento pdfDoc = new DocumentoBuilder()
                .conTipo(DocumentoFabrica.TipoDocumento.PDF)
                .conContenido("Este es el contenido del documento PDF.")
                .build();
        System.out.println("PDF - Dibujar:");
        System.out.println(pdfDoc.dibujar());
        System.out.println("PDF - Imprimir:");
        System.out.println(pdfDoc.imprimir());
        System.out.println("--------------------------------------");
        
        // Prueba de documento HTML
        Documento htmlDoc = new DocumentoBuilder()
                .conTipo(DocumentoFabrica.TipoDocumento.HTML)
                .conContenido("Este es el contenido del documento HTML.")
                .build();
        System.out.println("HTML - Dibujar:");
        System.out.println(htmlDoc.dibujar());
        System.out.println("HTML - Imprimir:");
        System.out.println(htmlDoc.imprimir());
        System.out.println("--------------------------------------");
        
        // Prueba de documento de TEXTO PLANO
        Documento textoDoc = new DocumentoBuilder()
                .conTipo(DocumentoFabrica.TipoDocumento.TEXTO_PLANO)
                .conContenido("Este es el contenido del documento de texto plano.")
                .build();
        System.out.println("TEXTO PLANO - Dibujar:");
        System.out.println(textoDoc.dibujar());
        System.out.println("TEXTO PLANO - Imprimir:");
        System.out.println(textoDoc.imprimir());
        System.out.println("--------------------------------------");
    }
}
