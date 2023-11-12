import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

class EmployeeHandler extends DefaultHandler {
    private ArrayList<Employee> employees;
    private Employee currentEmployee;
    private String currentElement;

    public void EmployeeHandler(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        if ("employee".equals(qName)) {
            currentEmployee = new Employee("", "", "", 0.0);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();
        if (currentEmployee != null) {
            switch (currentElement) {
                case "dni":
                    currentEmployee.setDni(value);
                    break;
                case "name":
                    currentEmployee.setName(value);
                    break;
                case "surname":
                    currentEmployee.setSurname(value);
                    break;
                case "salary":
                    currentEmployee.setSalary(Double.parseDouble(value));
                    break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("employee".equals(qName)) {
            employees.add(currentEmployee);
            currentEmployee = null;
        }
        currentElement = "";
    }
}

