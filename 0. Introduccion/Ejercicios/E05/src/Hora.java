public class Hora {
    private int hora;
    private int minuto;

    // Constructor
    public Hora(int hora, int minuto) {
        if (setHora(hora) && setMinutos(minuto)) {
            this.hora = hora;
            this.minuto = minuto;
        }
        else {
            this.hora = 0;
            this.minuto = 0;
        }
    }

    public void inc() {
        if (minuto < 59) {
            minuto++;
        }
        else {
            minuto = 0;
            if (hora < 23) {
                hora++;
            }
            else {
                hora = 0;
            }
        }
    }

    public boolean setMinutos(int valor) {
        if (valor >= 0 && valor <= 59) {
            minuto = valor;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean setHora(int valor) {
        if (valor >= 0 && valor <= 23) {
            hora = valor;
            return true;
        }
        else {
            return false;
        }
    }


    public String toString() {
        return String.format("%02d:%02d", hora, minuto);
    }
}
