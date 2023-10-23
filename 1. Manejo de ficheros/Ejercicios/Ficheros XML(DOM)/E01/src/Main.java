/**
 * Utiliza DOM en Java para leer el archivo libros.xml y mostrar la información.
 */

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try{
            // Crea un objeto DocumentBuilderFactory para obtener un objeto DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsea el archivo XML
            Document document = builder.parse(new File("BooksCollection.xml"));

            // Obtiene el elemento raíz
            Element root = document.getDocumentElement();

            // Obtiene una lista de nodos hijo (que son los elementos "libro" en este caso)
            NodeList nodeList = root.getElementsByTagName("book");

            // Itera a través de los nodos y muestra la información
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element book = (Element) node;
                    String title = book.getElementsByTagName("title").item(0).getTextContent();
                    String author = book.getElementsByTagName("author").item(0).getTextContent();
                    String year = book.getElementsByTagName("year").item(0).getTextContent();

                    System.out.println("Title: " + title);
                    System.out.println("Author: " + author);
                    System.out.println("Year: " + year);
                    System.out.println();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}