import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class EmployeeHandler extends DefaultHandler {
    private String currentElement;
    private Employee currentEmployee;
    private ArrayList<Employee> employees = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        if ("Employee".equals(qName)) {
            currentEmployee = new Employee("123456789", "Defecto1", "Defecto2", 1111);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();
        if (currentEmployee != null) {
            if ("DNI".equals(currentElement)) {
                currentEmployee.setDni(value);
            }
            else if ("Name".equals(currentElement)) {
                currentEmployee.setName(value);
            }
            else if ("Surname".equals(currentElement)) {
                currentEmployee.setSurname(value);
            }
            else if ("Salary".equals(currentElement)) {
                currentEmployee.setSalary(Double.parseDouble(value));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("Employee".equals(qName)) {
            employees.add(currentEmployee);
            currentEmployee = null;
        }
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
}
