public class Hora {
    private int hora;
    private int minuto;

    // Constructor
    public Hora(int hora, int minuto) {
        if (setHora(hora) && setMinutos(minuto)) {
            this.hora = hora;
            this.minuto = minuto;
        } else {
            // En caso de valores inválidos, asignamos valores por defecto
            this.hora = 0;
            this.minuto = 0;
        }
    }

    // Método para incrementar la hora en un minuto
    public void inc() {
        if (minuto < 59) {
            minuto++;
        } else {
            minuto = 0;
            if (hora < 23) {
                hora++;
            } else {
                hora = 0;
            }
        }
    }

    // Método para establecer los minutos
    public boolean setMinutos(int valor) {
        if (valor >= 0 && valor <= 59) {
            minuto = valor;
            return true;
        } else {
            return false;
        }
    }

    // Método para establecer la hora
    public boolean setHora(int valor) {
        if (valor >= 0 && valor <= 23) {
            hora = valor;
            return true;
        } else {
            return false;
        }
    }

    // Método para obtener la representación en String de la hora
    public String toString() {
        return String.format("%02d:%02d", hora, minuto);
    }
}
