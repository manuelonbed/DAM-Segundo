package xml;

//La clase DocumentBuilder en Java es parte de la API DOM (Document Object Model), 
//que se utiliza para procesar, analizar y crear documentos XML. 
//Es parte del paquete javax.xml.parsers, y su principal objetivo es proporcionar una 
//forma fácil de convertir un archivo XML en una estructura de árbol que puede ser 
//manipulada en memoria.

import javax.xml.parsers.DocumentBuilder; // Importa la clase DocumentBuilder para construir el documento XML
import javax.xml.parsers.DocumentBuilderFactory; // Importa la clase DocumentBuilderFactory para crear un DocumentBuilder
import org.w3c.dom.Document; // Importa la clase Document que representa el documento XML
import org.w3c.dom.Element; // Importa la clase Element que representa un elemento en el documento XML
import org.w3c.dom.NodeList; // Importa la clase NodeList que representa una lista de nodos en el documento XML

//DocumentBuilder lee un archivo XML y lo transforma en un objeto Document, 
//que representa todo el contenido del documento XML en memoria como un árbol de nodos. 
//Este documento puede ser manipulado y consultado.
//Puedes configurar el DocumentBuilderFactory para validar el XML contra un esquema o un DTD, lo cual es útil 
//si necesitas garantizar que el documento XML siga un formato específico.
public class SimpleDOMExample {
    public static void main(String[] args) {
        try {
            // Crear una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Crear un DocumentBuilder a partir de la factory
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Cargar y parsear el archivo XML
            Document document = builder.parse("libros.xml");

            // Normalizar el documento para asegurar que la estructura es correcta
            document.getDocumentElement().normalize();

            // Obtener la lista de elementos "libro" del documento XML
            NodeList libroList = document.getElementsByTagName("libro");

            // Imprimir los títulos de los libros
            System.out.println("Títulos de los libros:");
            for (int i = 0; i < libroList.getLength(); i++) {
                // Obtener cada elemento "libro" de la lista
                Element libro = (Element) libroList.item(i);
                // Obtener el contenido del elemento "titulo" dentro de cada "libro"
                String titulo = libro.getElementsByTagName("titulo").item(0).getTextContent();
                // Imprimir el título del libro
                System.out.println(titulo);
            }

        } catch (Exception e) {
            // Imprimir el error en caso de que ocurra alguna excepción
            e.printStackTrace();
        }
    }
}
