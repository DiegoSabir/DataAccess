/**
 * Crea un programa en Java que utilizando el fichero libros2.xml,
 * analicemos el archivo XML y calcularemos el precio promedio de los libros.
 */

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Main {
    public static void main(String[] args) {
        try {
            // Crear una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Crear un DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Analizar el archivo XML
            Document document = builder.parse("BooksCollection.xml");

            // Obtener la lista de nodos de libros
            NodeList book = document.getElementsByTagName("book");

            double totalPrice = 0;
            int booksCounter = book.getLength();

            // Iterar a través de los nodos de libros
            for (int i = 0; i < booksCounter; i++) {
                Element elementBook = (Element) book.item(i);
                double price = Double.parseDouble(elementBook.getElementsByTagName("price").item(0).getTextContent());
                totalPrice += price;
            }

            double averagePrice = totalPrice / booksCounter;
            System.out.println("Books average price: $" + averagePrice);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}