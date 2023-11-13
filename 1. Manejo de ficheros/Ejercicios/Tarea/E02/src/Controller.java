import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * La estructura XML asume que cada empleado tiene atributos como DNI, Nombre, Apellido y Salario.
 * Las operaciones de entrada/salida de archivos y el análisis XML se realizan utilizando la API DOM (Document Object Model) de Java.
 * Se implementa el manejo de errores mediante bloques try-catch, imprimiendo trazas de pila en caso de excepciones.
 */
public class Controller {
    private String path;
    File file;

    /**
     * Inicializa los atributos path y file.
     * Si el archivo no existe, crea un archivo XML vacío.
     */
    public Controller() {
        path = "./EmployeesDataList.xml";
        file = new File(path);
        if (!file.exists())
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     * Agrega un nuevo empleado al archivo XML.
     * Lee el archivo XML existente o crea uno nuevo si no existe.
     * Crea elementos XML para cada atributo del empleado (DNI, Nombre, Apellido, Salario) y los agrega al documento XML.
     * Escribe el documento XML actualizado de nuevo en el archivo.
     */
    public boolean addEmployee(Employee worker) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document registerWorkers = null;

            if(file.exists() && file.length() > 0) {
                registerWorkers= db.parse(file);
            }
            else {
                DOMImplementation dom = db.getDOMImplementation();
                registerWorkers = dom.createDocument(null, "Employees", null);
                registerWorkers.setXmlVersion("1.0");
            }
            //Creamos un nodo empleado
            Element employee = registerWorkers.createElement("Employee");

            //Lo añadimos como hijo de empleados
            registerWorkers.getDocumentElement().appendChild(employee);

            //Creamos el nodo ID
            Element id = registerWorkers.createElement("Dni");

            //Creamos el nodo texto con el valor de la ID
            Text text = registerWorkers.createTextNode(worker.getDni());

            //Añadimos el valor al nodo ID
            id.appendChild(text);

            //Añadimos el nodo ID a empleado
            employee.appendChild(id);
            Element name = registerWorkers.createElement("Name");
            text = registerWorkers.createTextNode(worker.getName());
            name.appendChild(text);
            employee.appendChild(name);

            Element surname = registerWorkers.createElement("Surname");
            text = registerWorkers.createTextNode(worker.getSurname());
            surname.appendChild(text);
            employee.appendChild(surname);

            Element salary = registerWorkers.createElement("Salary");
            text = registerWorkers.createTextNode(String.valueOf(worker.getSalary()));
            salary.appendChild(text);
            employee.appendChild(salary);

            Source source = new DOMSource(registerWorkers);
            Result result = new StreamResult(file);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
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
     * Modifica los datos de un empleado existente en el archivo XML.
     * Lee el archivo XML y busca al empleado con el DNI proporcionado.
     * Si se encuentra al empleado, actualiza los elementos XML correspondientes (DNI, Nombre, Apellido, Salario) con los nuevos valores.
     * Escribe el documento XML modificado de nuevo en el archivo.
     */
    public boolean modifyEmployee(Employee worker) {
        try {
            ArrayList<Employee> workers = new ArrayList<Employee>();

            //Creamos el DocumentBuilder para poder obtener el Document
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder dc = dbf.newDocumentBuilder();

            //Leemos el Document desde el fichero
            Document registerWorkers = dc.parse(file);

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
     * Elimina a un empleado existente del archivo XML.
     * Lee el archivo XML y busca al empleado con el DNI proporcionado.
     * Si se encuentra al empleado, elimina el elemento XML correspondiente del documento.
     * Escribe el documento XML actualizado de nuevo en el archivo.
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
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Recupera los datos del empleado basándose en el DNI proporcionado.
     * Lee el archivo XML y busca al empleado con el DNI especificado.
     * Si se encuentra, crea y devuelve un objeto Employee con los detalles del empleado.
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
                if(employe.getNodeType() == Node.ELEMENT_NODE) {
                    //Creamos el elemento empleado y leemos su información
                    Element employ = (Element)employe;
                    
                    if(employ.getElementsByTagName("Dni").item(0).getTextContent().equals(dni)) {
                        return new Employee(employ.getElementsByTagName("Dni").item(0).getTextContent(),
                                employ.getElementsByTagName("Name").item(0).getTextContent(),
                                employ.getElementsByTagName("Surname").item(0).getTextContent(),
                                Double.valueOf(employ.getElementsByTagName("Salary").item(0).getTextContent()));
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
     * Recupera una lista de todos los empleados almacenados en el archivo XML.
     * Lee el archivo XML e itera a través de los elementos "Employee", creando un objeto Employee para cada uno.
     * Devuelve un ArrayList<Employee> que contiene a todos los empleados.
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
                if(employe.getNodeType()==Node.ELEMENT_NODE) {
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
}
