/**
 * Crea un programa en Java utilizando DOM que analice el archivo XML estudiantes.xml
 * y que permita al usuario agregar nuevos estudiantes a dicho archivo.
 * Para agregar un nuevo estudiante, tendrás que proporcionar ID, nombre y edad.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

public class Main {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("StudentsDataList.xml");

            //Eliminar líneas en blanco del XML
            removeWhiteSpace(document);

            Scanner sc = new Scanner(System.in);

            // Mostrar información de estudiantes existentes
            System.out.println("Students List:");
            NodeList student = document.getElementsByTagName("student");
            for (int i = 0; i < student.getLength(); i++) {
                Element studentElement = (Element) student.item(i);
                String id = studentElement.getElementsByTagName("id").item(0).getTextContent();
                String name = studentElement.getElementsByTagName("name").item(0).getTextContent();
                String age = studentElement.getElementsByTagName("age").item(0).getTextContent();
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }

            // Agregar un nuevo estudiante
            Element newStudent = document.createElement("student");
            Element newId = document.createElement("id");
            Element newName = document.createElement("name");
            Element newAge = document.createElement("age");

            System.out.print("Enter Id Student: ");
            newId.setTextContent(sc.nextLine());

            System.out.print("Enter name: ");
            newName.setTextContent(sc.nextLine());

            System.out.print("Enter age: ");
            newAge.setTextContent(sc.nextLine());

            newStudent.appendChild(newId);
            newStudent.appendChild(newName);
            newStudent.appendChild(newAge);

            Element root = document.getDocumentElement();
            root.appendChild(newStudent);

            // Guardar los cambios en el archivo XML
            FileOutputStream output = new FileOutputStream(new File("StudentsDataList.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            //Codificacion UTF 8
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new javax.xml.transform.dom.DOMSource(document), new javax.xml.transform.stream.StreamResult(output));
            output.close();

            System.out.println("NEW STUDENT ADDED.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //MÉTODO PARA ELIMINAR LÍNEAS EN BLANCO DEL XML
    public static void removeWhiteSpace(Document document) {
        Set<Node> toRemove = new HashSet<Node>();
        DocumentTraversal t = (DocumentTraversal) document;
        NodeIterator it = t.createNodeIterator(document,
                NodeFilter.SHOW_TEXT, null, true);

        for (Node n = it.nextNode(); n != null; n = it.nextNode()) {
            if (n.getNodeValue().trim().isEmpty()) {
                toRemove.add(n);
            }
        }

        for (Node n : toRemove) {
            n.getParentNode().removeChild(n);
        }
    }
}