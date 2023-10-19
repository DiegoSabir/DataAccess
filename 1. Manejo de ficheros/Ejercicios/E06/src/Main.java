import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.parsers.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();

            Document registroEmpleados = builder.newDocument();

            registroEmpleados.setXmlVersion("1.0");

            //Paso 2:Crear elementos y agregarlos al documento
            Element elemRaiz = registroEmpleados.createElement("empleado");
            registroEmpleados.appendChild(elemRaiz);

            Element elemPersona = registroEmpleados.createElement("persona");
            elemRaiz.appendChild(elemPersona);

            Element elemNombre = registroEmpleados.createElement("nombre");
            elemNombre.appendChild(registroEmpleados.createTextNode("Antonio"));
            elemPersona.appendChild(elemNombre);

            Element elemApellidos = registroEmpleados.createElement("apellidos");
            elemApellidos.appendChild(registroEmpleados.createTextNode("Ribas"));
            elemPersona.appendChild(elemApellidos);

            elemRaiz.appendChild(elemPersona);

            elemNombre.appendChild(registroEmpleados.createTextNode("Pablo"));
            elemPersona.appendChild(elemNombre);

            elemApellidos.appendChild(registroEmpleados.createTextNode("Sierra"));
            elemPersona.appendChild(elemApellidos);


            //Paso: Escribir el contenido del documento XML a un archivo
            Transformer transformador = TransformerFactory.newInstance().newTransformer();
            DOMSource origen = new DOMSource(registroEmpleados);
            StreamResult resultado = new StreamResult(new File("Empleados.xml"));
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            transformador.transform(origen, resultado);


            /**
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();

            Document regisroEmpleados = builder.parse(new File("Empleados.xml"));

            regisroEmpleados.getDocumentElement().normalize();


            System.out.println("El elemento raiz es " +);
             */

    } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}