import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Controlador {
    private final int tamañoRegistro = 120;
    private RandomAccessFile raf;
    private String direccion;

    public Controlador(){
        direccion = "./pacientes.dat";
        File archivo = new File(direccion);

        if (!archivo.exists()){
            try {
                archivo.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean insercion(Paciente p) {
        if(!posicionEnUso(getPosicion(p.getDni()))) {
            try {
                raf = new RandomAccessFile(new File(this.direccion), "rw");
                if(raf.length() <= 0){
                    raf.write(0);
                }
                raf.seek(getPosicion(p.getDni()) * tamañoRegistro);

                StringBuffer sb = new StringBuffer(p.getDni());
                sb.setLength(9);
                raf.writeChars(sb.toString());

                sb = new StringBuffer(p.getNombre());
                sb.setLength(15);
                raf.writeChars(sb.toString());

                sb = new StringBuffer(p.getApellidos());
                sb.setLength(25);
                raf.writeChars(sb.toString());

                sb = new StringBuffer(p.getDireccion());
                sb.setLength(30);
                raf.writeChars(sb.toString());


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

    private int getPosicion(String nif) {
        return Integer.valueOf(nif.substring(0, nif.length() - 1)) % 10;
    }

    private boolean posicionEnUso(int position) {
        try {
            raf = new RandomAccessFile(new File(this.direccion), "r");
            raf.seek(position * this.tamañoRegistro);
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
