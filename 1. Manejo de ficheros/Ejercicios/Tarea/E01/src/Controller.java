import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Controller {
    private final int registerSize = 66;
    private RandomAccessFile raf;
    private String path;

    public Controller() {
        path = "./EmployeesDataList.dat";
        File file = new File(path);

        if (!file.exists()){
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean addEmployee(Employee worker) {
        //Nuestro fichero tiene un max de 10 emp.
        //La existencia o no de un emp venia dada por su posición en el mismo
        if(!positionInUse(getPosition(worker.getDni()))) {
            try {
                //Inserto al empleado
                raf = new RandomAccessFile(new File(this.path), "rw");
                if(raf.length() <= 0){
                    raf.write(0);
                }
                raf.seek(getPosition(worker.getDni()) * registerSize);

                //Escribo las cosas
                StringBuffer sb = new StringBuffer(worker.getDni());
                sb.setLength(9);
                raf.writeChars(sb.toString());
                sb = new StringBuffer(worker.getName());
                sb.setLength(10);
                raf.writeChars(sb.toString());
                sb = new StringBuffer(worker.getSurname());
                sb.setLength(10);
                raf.writeChars(sb.toString());
                raf.writeDouble(worker.getSalary());
                raf.close();
                return true;
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        else {
            return false;
        }
    }

    public boolean modifySalary(Employee worker) {
        if(positionInUse(getPosition(worker.getDni()))) {
            try {
                //Inserto al empleado
                raf = new RandomAccessFile(new File(this.path), "rw");
                raf.seek(getPosition(worker.getDni()) * registerSize);

                //Escribo las cosas
                StringBuffer sb = new StringBuffer(worker.getDni());
                sb.setLength(9);
                raf.writeChars(sb.toString());
                sb = new StringBuffer(worker.getName());
                sb.setLength(10);
                raf.writeChars(sb.toString());
                sb = new StringBuffer(worker.getSurname());
                sb.setLength(10);
                raf.writeChars(sb.toString());
                raf.writeDouble(worker.getSalary());
                raf.close();
                return true;
            }
            catch (FileNotFoundException e) {
                return false;
            }
            catch (IOException e) {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public boolean deleteEmploy(Employee worker) {
        if(positionInUse(getPosition(worker.getDni()))) {
            try {
                //Inserto al empleado
                raf = new RandomAccessFile(new File(this.path), "rw");
                raf.seek(getPosition(worker.getDni()) * registerSize);

                //Escribo las cosas
                StringBuffer sb = new StringBuffer(" ");
                sb.setLength(9);
                raf.writeChars(sb.toString());

                raf.close();
                return true;
            }
            catch (FileNotFoundException e) {
                return false;
            }
            catch (IOException e) {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public Employee consultEmployData(String dni) {
        if(positionInUse(getPosition(dni))) {
            try {
                //Inserto al empleado
                raf = new RandomAccessFile(new File(this.path), "r");
                raf.seek(getPosition(dni) * registerSize);

                //DNI
                dni = "";
                for(int i = 0; i < 9; i++)
                    dni += raf.readChar();
                //NOMBRE
                String name = "";
                for(int i = 0; i < 10; i++)
                    name += raf.readChar();
                //APELLIDO
                String surname = "";
                for(int i = 0; i < 10; i++)
                    surname += raf.readChar();
                //SALARIO
                double salary= raf.readDouble();
                return new Employee(dni, name, surname, salary);
            }
            catch (FileNotFoundException e) {
                return null;
            }
            catch (IOException e) {
                return null;
            }
        }
        else {
            return null;
        }
    }

    //Consultar todos
    public ArrayList<Employee> listWorkers(){
        try {
            ArrayList<Employee> workers = new ArrayList<Employee>();
            raf = new RandomAccessFile(new File(this.path), "r");
            for(int position = 0; position < raf.length(); position += this.registerSize) {

                //DNI
                String dni = "";
                for(int i = 0; i < 9; i++)
                    dni += raf.readChar();
                if(dni.trim().length() <= 0)
                    continue;
                //NOMBRE
                String name = "";
                for(int i = 0; i < 10; i++)
                    name += raf.readChar();
                //APELLIDO
                String surname = "";
                for(int i = 0; i < 10; i++)
                    surname += raf.readChar();
                //SALARIO
                double salary = raf.readDouble();
                workers.add(new Employee(dni, name, surname, salary));
            }
            return workers;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int getPosition(String nif) {
        return Integer.valueOf(nif.substring(0, nif.length() - 1)) % 10;
    }

    private boolean positionInUse(int position) {
        try {
            raf = new RandomAccessFile(new File(this.path), "r");
            raf.seek(position * this.registerSize);
            if(raf.readChar() == ' ') {
                raf.close();
                return false;
            }
            else {
                raf.close();
                return true;
            }
        }
        catch (FileNotFoundException e) {
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }
}
