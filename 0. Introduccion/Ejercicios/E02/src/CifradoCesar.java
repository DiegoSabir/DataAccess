public class CifradoCesar {
    public static String cifradoCesar(String cadenaACifrar) {
        StringBuilder cifrado = new StringBuilder();

        for (int i = 0; i < cadenaACifrar.length(); i++) {
            char caracter = cadenaACifrar.charAt(i);

            if (Character.isUpperCase(caracter)) {
                char cifradoCaracter = (char) ('A' + (caracter - 'A' + 3) % 26);
                cifrado.append(cifradoCaracter);
            }
            else {
                cifrado.append(caracter);
            }
        }
        return cifrado.toString();
    }
}
