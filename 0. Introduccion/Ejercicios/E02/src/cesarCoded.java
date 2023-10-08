public class cesarCoded {
    public static String cesarCoded(String chain) {
        StringBuilder coded = new StringBuilder();

        for (int i = 0; i < chain.length(); i++) {
            char character = chain.charAt(i);

            if (Character.isUpperCase(character)) {
                char characterCoded = (char) ('A' + (character - 'A' + 3) % 26);
                coded.append(characterCoded);
            }
            else {
                coded.append(character);
            }
        }
        return coded.toString();
    }
}
