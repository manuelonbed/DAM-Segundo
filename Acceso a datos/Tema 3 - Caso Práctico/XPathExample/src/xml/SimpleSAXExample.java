package xml;

import org.xml.sax.Attributes; // Importa la clase Attributes para manejar atributos de los elementos XML
import org.xml.sax.SAXException; // Importa la clase SAXException para manejar excepciones de SAX
import org.xml.sax.helpers.DefaultHandler; // Importa la clase DefaultHandler que implementa la interfaz Handler de SAX

import java.util.List;

import javax.xml.parsers.SAXParser; // Importa la clase SAXParser para parsear el XML
import javax.xml.parsers.SAXParserFactory; // Importa la clase SAXParserFactory para crear un SAXParser

//SAXParserFactory es una clase en Java que pertenece al paquete javax.xml.parsers 
//y se utiliza para crear instancias de SAX (Simple API for XML) parsers. 
//SAX es una alternativa ligera al modelo DOM para procesar documentos XML. 
//En lugar de cargar todo el documento en memoria (como lo hace DOM), SAX analiza el 
//XML secuencialmente y emite eventos cuando encuentra elementos, atributos, texto, etc.

public class SimpleSAXExample {
    public static void main(String[] args) {
        try {
            // Crear una instancia de SAXParserFactory
        	//SAXParserFactory se utiliza para crear un SAXParser, que es el componente que realmente 
        	//analiza el XML de manera secuencial y basada en eventos.
            SAXParserFactory factory = SAXParserFactory.newInstance();
            // Crear un SAXParser a partir de la factory
            SAXParser saxParser = factory.newSAXParser();
            
            // Crear un manejador que extiende DefaultHandler para procesar el XML
            DefaultHandler handler = new DefaultHandler() {
                boolean isTitulo = false; // Bandera para verificar si estamos en un elemento "titulo"

                // Método que se llama al comienzo de un elemento
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    // Comprobar si el elemento es "titulo"
                    if (qName.equalsIgnoreCase("titulo")) {
                        isTitulo = true; // Establecer la bandera si estamos en un elemento "titulo"
                    }
                }

                // Método que se llama al final de un elemento
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    // Comprobar si hemos cerrado un elemento "titulo"
                    if (qName.equalsIgnoreCase("titulo")) {
                        isTitulo = false; // Restablecer la bandera
                    }
                }

                // Método que se llama cuando se encuentra texto dentro de un elemento
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (isTitulo) {
                        // Si estamos en un elemento "titulo", imprimir el texto
                        System.out.println("Título: " + new String(ch, start, length));
                    }
                }
            };

            // Parsear el archivo XML utilizando el manejador
            saxParser.parse("libros.xml", handler);

        } catch (Exception e) {
            // Imprimir el error en caso de que ocurra alguna excepción
            e.printStackTrace();
        }
    }

	public List<String> getBookTitles1(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getBookTitles(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
