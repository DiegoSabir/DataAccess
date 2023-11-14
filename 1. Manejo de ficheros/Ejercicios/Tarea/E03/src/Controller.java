import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Controller {
    private String path;
    File file;

    /**
     * Inicializa el atributo path con la ruta del archivo XML.
     * Inicializa el atributo file con un objeto File basado en la ruta.
     * Si el archivo no existe, intenta crear uno nuevo.
     */
    public Controller() {
        path = "./EmployeesDataList.xml";
        file = new File(path);
        if (!file.exists()){
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Agrega un nuevo empleado al archivo XML.
     * Utiliza el API DOM para crear un documento XML o parsea el existente si ya tiene contenido.
     * Crea elementos XML (<Employee>, <Dni>, <Name>, <Surname>, <Salary>) y los agrega al documento.
     * Utiliza un objeto Transformer para escribir el documento XML en el archivo.Utiliza un objeto Transformer para escribir el documento XML en el archivo.
     * Devuelve true si la operación fue exitosa; de lo contrario, devuelve false.
     */
    public boolean addEmployee(Employee worker) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document registerWorkers = null;

            if(file.exists() && file.length() > 0) {
                registerWorkers = db.parse(file);
            }
            else {
                DOMImplementation dom = db.getDOMImplementation();
                registerWorkers = dom.createDocument(null, "Employees", null);
                registerWorkers.setXmlVersion("1.0");
            }

            //Creamos un nodo empleado
            Element empleado= registerWorkers.createElement("Employee");

            //Lo añadimos como hijo de empleados
            registerWorkers.getDocumentElement().appendChild(empleado);

            //Creamos el nodo ID
            Element id = registerWorkers.createElement("Dni");

            //Creamos el nodo texto con el valor de la ID
            Text text = registerWorkers.createTextNode(worker.getDni());

            //Añadimos el valor al nodo ID
            id.appendChild(text);

            //Añadimos el nodo ID a empleado
            empleado.appendChild(id);
            Element name = registerWorkers.createElement("Name");
            text= registerWorkers.createTextNode(worker.getName());
            name.appendChild(text);
            empleado.appendChild(name);

            Element surname = registerWorkers.createElement("Surname");
            text= registerWorkers.createTextNode(worker.getSurname());
            surname.appendChild(text);
            empleado.appendChild(surname);

            Element salary = registerWorkers.createElement("Salary");
            text= registerWorkers.createTextNode(String.valueOf(worker.getSalary()));
            salary.appendChild(text);
            empleado.appendChild(salary);

            Source source = new DOMSource(registerWorkers);
            Result result = new StreamResult(file);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            return true;
        }
        catch (DOMException e) {
            e.printStackTrace();
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (SAXException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        }
        catch (TransformerException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Modifica la información de un empleado en el archivo XML.
     * Utiliza el API DOM para obtener y modificar los nodos correspondientes.
     * Utiliza un objeto Transformer para escribir el documento XML modificado en el archivo.
     * Devuelve true si la operación fue exitosa; de lo contrario, devuelve false.
     */
    public boolean modifyEmployee(Employee worker) {
        try {
            ArrayList<Employee> workers = new ArrayList<Employee>();

            //Creamos el DocumentBuilder para poder obtener el Document
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            //Leemos el Document desde el fichero
            Document registerWorkers = db.parse(file);

            //Normalizamos el documento para evitar errores de lectura.
            registerWorkers.getDocumentElement().normalize();

            //Creamos una lista de todos los nodos empleado
            NodeList employee = registerWorkers.getElementsByTagName("Employee");

            //Recorremos la lista.
            for(int i = 0; i < employee.getLength(); i++) {
                //Obtenemos el primer nodo de la lista
                Node employe = employee.item(i);

                //En caso de que ese nodo sea un Elemento
                if(employe.getNodeType() == Node.ELEMENT_NODE) {

                    //Creamos el elemento empleado y leemos su información
                    Element employ = (Element)employe;

                    if(employ.getElementsByTagName("Dni").item(0).getTextContent().equals(worker.getDni())) {
                        //empleado.getParentNode().removeChild(empleado);
                        employ.getElementsByTagName("Dni").item(0).setTextContent(worker.getDni());
                        employ.getElementsByTagName("Name").item(0).setTextContent(worker.getName());
                        employ.getElementsByTagName("Surname").item(0).setTextContent(worker.getSurname());
                        employ.getElementsByTagName("Salary").item(0).setTextContent(String.valueOf(worker.getSalary()));

                        Source source = new DOMSource(registerWorkers);
                        Result result = new StreamResult(file);
                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        transformer.transform(source, result);
                        return true;
                    }
                }
            }
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (SAXException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        }
        catch (TransformerException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Elimina un empleado del archivo XML.
     * Utiliza el API DOM para encontrar y eliminar el nodo correspondiente al empleado.
     * Utiliza un objeto Transformer para escribir el documento XML modificado en el archivo.
     * Devuelve true si la operación fue exitosa; de lo contrario, devuelve false.
     */
    public boolean deleteEmployee(Employee worker) {
        try {
            ArrayList<Employee> workers = new ArrayList<Employee>();

            //Creamos el DocumentBuilder para poder obtener el Document
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            //Leemos el Document desde el fichero
            Document registerWorkers = db.parse(file);

            //Normalizamos el documento para evitar errores de lectura.
            registerWorkers.getDocumentElement().normalize();

            //Creamos una lista de todos los nodos empleado
            NodeList employee = registerWorkers.getElementsByTagName("Employee");

            //Recorremos la lista.
            for(int i = 0; i < employee.getLength(); i++) {
                //Obtenemos el primer nodo de la lista
                Node employe = employee.item(i);

                //En caso de que ese nodo sea un Elemento
                if(employe.getNodeType() == Node.ELEMENT_NODE) {
                    //Creamos el elemento empleado y leemos su información
                    Element employ = (Element)employe;

                    if(employ.getElementsByTagName("Dni").item(0).getTextContent().equals(worker.getDni())) {
                        employ.getParentNode().removeChild(employ);

                        Source source = new DOMSource(registerWorkers);
                        Result result = new StreamResult(file);
                        Transformer transform = TransformerFactory.newInstance().newTransformer();
                        transform.transform(source, result);
                        return true;
                    }
                }
            }
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (SAXException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        }
        catch (TransformerException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Consulta la información de un empleado por su DNI.
     * Utiliza el API DOM para buscar el nodo del empleado y crea un nuevo objeto Employee con los datos encontrados.
     * Devuelve el objeto Employee o null si no se encuentra.
     */
    public Employee consultEmployeeData(String dni) {
        try {
            ArrayList<Employee> workers = new ArrayList<Employee>();

            //Creamos el DocumentBuilder para poder obtener el Document
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            //Leemos el Document desde el fichero
            Document registerWorkers = db.parse(file);

            //Normalizamos el documento para evitar errores de lectura.
            registerWorkers.getDocumentElement().normalize();

            //Creamos una lista de todos los nodos empleado
            NodeList employee = registerWorkers.getElementsByTagName("Employee");

            //Recorremos la lista.
            for(int i = 0; i < employee.getLength(); i++) {
                //Obtenemos el primer nodo de la lista
                Node employe = employee.item(i);

                //En caso de que ese nodo sea un Elemento
                if(employe.getNodeType()==Node.ELEMENT_NODE) {

                    //Creamos el elemento empleado y leemos su información
                    Element empleado = (Element)employe;
                    if(empleado.getElementsByTagName("Dni").item(0).getTextContent().equals(dni)) {

                        return new Employee(empleado.getElementsByTagName("Dni").item(0).getTextContent(),
                                empleado.getElementsByTagName("Name").item(0).getTextContent(),
                                empleado.getElementsByTagName("Surname").item(0).getTextContent(),
                                Double.valueOf(empleado.getElementsByTagName("Salary").item(0).getTextContent()));
                    }
                }
            }
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (SAXException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Lista todos los empleados almacenados en el archivo XML.
     * Utiliza el API DOM para obtener la lista de nodos de empleados y construye objetos Employee.
     * Devuelve una lista de empleados.
     */
    public ArrayList<Employee> listEmployees(){
        try {
            ArrayList<Employee> workers = new ArrayList<Employee>();

            //Creamos el DocumentBuilder para poder obtener el Document
            DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            //Leemos el Document desde el fichero
            Document registerWorkers = db.parse(file);

            //Normalizamos el documento para evitar errores de lectura.
            registerWorkers.getDocumentElement().normalize();

            //Creamos una lista de todos los nodos empleado
            NodeList employee = registerWorkers.getElementsByTagName("Employee");
            //Mostramos el nº de elementos empleado que hemos encontrado

            //Recorremos la lista.
            for(int i = 0; i < employee.getLength(); i++) {
                //Obtenemos el primer nodo de la lista
                Node employe = employee.item(i);

                //En caso de que ese nodo sea un Elemento
                if(employe.getNodeType() == Node.ELEMENT_NODE) {
                    //Creamos el elemento empleado y leemos su información
                    Element employ = (Element)employe;

                    workers.add(new Employee(
                            employ.getElementsByTagName("Dni").item(0).getTextContent(),
                            employ.getElementsByTagName("Name").item(0).getTextContent(),
                            employ.getElementsByTagName("Surname").item(0).getTextContent(),
                            Double.valueOf(employ.getElementsByTagName("Salary").item(0).getTextContent())));
                }
            }
            return workers;
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (SAXException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Utiliza SAX para manejar el XML.
     * Define un DefaultHandler que procesa los eventos SAX, como startElement, characters, y endElement.
     * Construye objetos Employee mientras procesa los elementos del XML.
     * Devuelve una lista de empleados construida con SAX.
     */
    public ArrayList<Employee> EmployeeHandler() {
        ArrayList<Employee> workers = new ArrayList<>();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                Employee worker;
                String valorActual;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if ("Employee".equals(qName)) {
                        worker = new Employee();
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    valorActual = new String(ch, start, length);
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if ("Dni".equals(qName)) {
                        worker.setDni(valorActual);
                    }
                    else if ("Name".equals(qName)) {
                        worker.setName(valorActual);
                    }
                    else if ("Surname".equals(qName)) {
                        worker.setSurname(valorActual);
                    }
                    else if ("Salary".equals(qName)) {
                        worker.setSalary(Double.parseDouble(valorActual));
                    }
                    else if ("Employee".equals(qName)) {
                        workers.add(worker);
                    }
                }
            };
            parser.parse(file, handler);
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return workers;
    }
}
