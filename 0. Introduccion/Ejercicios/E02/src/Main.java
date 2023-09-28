public class Main {
    public static void main(String[] args) {
        String textoOriginal = "HELLO WORLD";
        String textoCifrado = CifradoCesar.cifradoCesar(textoOriginal);
        System.out.println("Texto original: " + textoOriginal);
        System.out.println("Texto cifrado: " + textoCifrado);
    }
}
