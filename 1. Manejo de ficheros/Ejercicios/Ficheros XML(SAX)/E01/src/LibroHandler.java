import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

//Para empezar con SAX, necesitamos crear una clase que herede de la clase DefaultHandler.
public class LibroHandler extends DefaultHandler {

    //Necesitaremos un StringBuilder para ir almacenando el contenido de la etiqueta.
    private StringBuilder value;

    public LibroHandler() {
        this.value = new StringBuilder();
    }

    /**
     * Tenemos 3 métodos que vamos a sobrescribir: startElement, characters y endElement.
     * Con SAX, para cada etiqueta, pasará por estos 3 métodos.
     * Empecemos por startElement
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.value.setLength(0);
        if (qName.equals("book")) {
            String id = attributes.getValue("id");
            System.out.println("ID: " + id);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        this.value.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "book":
                System.out.println("");
                break;
            case "title":
                System.out.println("Title: " + this.value.toString());
                break;
            case "author":
                System.out.println("Author: " + this.value.toString());
                break;
            case "year":
                System.out.println("Year: " + this.value.toString());
                break;
        }
    }
}
