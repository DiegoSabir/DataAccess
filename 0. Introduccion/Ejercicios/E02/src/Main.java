import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a text:");
        String text = sc.nextLine();

        String coded = CesarCoded.cesarCoded(text);
        System.out.println("Normal: " + text);
        System.out.println("Coded: " + coded);
    }
}
