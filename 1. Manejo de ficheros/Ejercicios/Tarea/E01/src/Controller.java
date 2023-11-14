import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Controller {
    /**
     * Representa el tamaño total de un registro de empleado en el archivo.
     * El DNI se almacena como una cadena de 9 caracteres.
     * El nombre se almacena como una cadena de 10 caracteres.
     * El apellido se almacena como una cadena de 10 caracteres.
     * El salario es double, que generalmente ocupa 8 bytes.
     *
     * Sumando estos tamaños: 9 + 10 + 10 + 8 = 37
     *
     * 66 se eligió para que haya suficiente espacio para almacenar cada campo,
     * incluso si los datos reales son más cortos.
     */
    private final int registerSize = 66;

    /**
     * Se utiliza para realizar operaciones de lectura y escritura en un archivo de acceso aleatorio.
     */
    private RandomAccessFile raf;
    private String path;

    /**
     * Establece la ruta predeterminada del archivo en "./EmployeesDataList.dat".
     * Comprueba si el archivo existe y, de no ser así, lo crea.
     */
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

    /**
     * Añade un nuevo empleado al archivo.
     * Verifica si la posición para los datos del empleado en el archivo no está en uso.
     * Si la posición está disponible, abre el archivo en modo lectura-escritura, busca la posición adecuada y escribe la información del empleado (DNI, nombre, apellido, salario) en el archivo.
     */
    public boolean addEmployee(Employee worker) {
        //Nuestro fichero tiene un max de 10 emp.
        //La existencia o no de un emp venia dada por su posición en el mismo
        if(!positionInUse(getPosition(worker.getDni()))) {
            try {
                /**
                 * Mode: Son los permisos --> read(r) write(w)
                 */
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

    /**
     * Modifica el salario de un empleado existente en el archivo.
     * Comprueba si la posición para los datos del empleado está en uso.
     * Si la posición es válida, abre el archivo en modo lectura-escritura, busca la posición adecuada y actualiza la información del salario.
     */
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

    /**
     * Elimina a un empleado existente del archivo.
     * Comprueba si la posición para los datos del empleado está en uso.
     * Si la posición es válida, abre el archivo en modo lectura-escritura, busca la posición adecuada y escribe caracteres vacíos para marcar la posición como no utilizada.
     */
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

    /**
     * Recupera y devuelve los datos de un empleado según el DNI proporcionado.
     * Comprueba si la posición para los datos del empleado está en uso.
     * Si la posición es válida, abre el archivo en modo lectura, busca la posición adecuada, lee la información del empleado y la devuelve como un objeto Employee.
     */
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

    /**
     * Recupera y devuelve una lista de todos los empleados en el archivo.
     * Lee el archivo secuencialmente, extrayendo la información del empleado y agregándola a un ArrayList<Employee>.
     * Omite las posiciones donde no hay datos de empleados.
     */
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

    /**
     * Calcula y devuelve una posición en el archivo según el último dígito del número de identificación nacional proporcionado (DNI).
     */
    private int getPosition(String nif) {
        return Integer.valueOf(nif.substring(0, nif.length() - 1)) % 10;
    }

    /**
     * Comprueba si la posición especificada en el archivo está en uso leyendo el carácter en esa posición.
     * Devuelve true si la posición está en uso, false en caso contrario.
     */
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
