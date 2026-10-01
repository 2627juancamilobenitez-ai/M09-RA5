
import java.util.Arrays;
import java.util.Random;

public class Monoalfabetic {

    static char[] majuscules = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 
        'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};

    static char[] permutat = permutaAlfabet(majuscules);

    public static char[] permutaAlfabet(char[] alfabet) {

        char[] resultado = Arrays.copyOf(alfabet, alfabet.length);
        Random rnd = new Random();

        for (int i = 0; i < alfabet.length - 1; i++) {
            int j = i + rnd.nextInt(resultado.length - 1);
            char lletra = resultado[i];
            resultado [i] = resultado[j];
            resultado [j] = lletra;

        }

        return resultado;
    }


    public static String xifraMonoAlfa(String cadena){
        return transforma(cadena, majuscules, permutat);
    }


    public static String desxifraMonoAlfa(String cadena) {
        return transforma(cadena, permutat, majuscules);
    }

    private static String transforma(String cadena, char[] origen, char[] desti) {
        StringBuilder sb = new StringBuilder();

        for (int k = 0; k < cadena.length(); k++) {
            char c = cadena.charAt(k);
            boolean esMinuscula = Character.isLowerCase(c);
            char mayus = Character.toUpperCase(c);

            int pos = indexOf(origen, mayus);

            if (pos == -1) {
                sb.append(c); 
            } else {
                char nou = desti[pos];
                sb.append(esMinuscula ? Character.toLowerCase(nou) : nou);
            }
        }
        return sb.toString();
    }


    private static int indexOf(char[] array, char c) {
    for (int i = 0; i < array.length; i++) {
        if (array[i] == c) return i;
    }
    return -1;
}

    public static void main(String[] args) {
        System.out.println(new String(majuscules).replaceAll("", " ").trim());
        System.out.println(new String(permutat).replaceAll("", " ").trim());

        String[] tests = {
            "Test 01 àrbitre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };

        System.out.println("Xifratge:");
        for (String t : tests) {
            System.out.println(t + " -> " + xifraMonoAlfa(t));
        }

        System.out.println("Desxifratge:");
        for (String t : tests) {
            String xifrat = xifraMonoAlfa(t);
            System.out.println(xifrat + " -> " + desxifraMonoAlfa(xifrat));
        }
    }

}
