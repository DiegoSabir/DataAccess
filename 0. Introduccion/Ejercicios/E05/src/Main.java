/**
 * Diseñar la clase Hora, que representa un instante de tiempo compuesto por la hora (de 0 a 23) y los minutos.
 * Dispone de los métodos:
 * - Hora (hora, minuto), que construye un objeto con los datos pasados como parámetros.
 * - void inc(), que incrementa la hora en un minuto.
 * - boolean setMinutos (valor), que asigna un valor, si es válido, a los minutos. Devuelve true o false
 *   según haya sido posible modificar los minutos o no.
 * - boolean setHora (valor), que asigna un valor, si está comprendido entre 0 y 23, a la hora. Devuelve true o
 *   false según haya sido posible cambiar la hora o no.
 * - String toString(), que devuelve un String con la representación de la hora.
 */
public class Main {
    public static void main(String[] args) {
        Hour hour = new Hour(21, 12);
        System.out.println(hour.toString());

        hour.inc();
        System.out.println(hour.toString());

        hour.setMinute(45);
        System.out.println(hour.toString());

        hour.setHour(23);
        System.out.println(hour.toString()); //
    }
}