public class CesarCoded {
    public static String cesarCoded(String chain) {
        StringBuilder coded = new StringBuilder();

        for (int i = 0; i < chain.length(); i++) {
            char character = chain.charAt(i);
            char characterCoded;

            if (Character.isUpperCase(character)) {
                characterCoded = (char) ('A' + (character - 'A' + 3) % 26);
            }
            else if (Character.isLowerCase(character)) {
                characterCoded = (char) ('a' + (character - 'a' + 3) % 26);
            }
            else {
                characterCoded = character;
            }

            coded.append(characterCoded);
        }
        return coded.toString();
    }
}

