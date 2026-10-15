package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;

public class XifradorMonoalfabetic implements Xifrador {

    private static final char[] majuscules = { 'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I',
            'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü',
            'V', 'W', 'X', 'Y', 'Z' };

    private char[] permutat;

    public XifradorMonoalfabetic() {
        permutat = permutaAlfabet(majuscules);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        String xifrat = xifraMonoAlfa(msg);
        return new TextXifrat(xifrat.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        String text = new String(xifrat.getBytes(), StandardCharsets.UTF_8);
        return desxifraMonoAlfa(text);
    }

    public char[] permutaAlfabet(char[] alfabet) {

        char[] resultado = Arrays.copyOf(alfabet, alfabet.length);
        Random rnd = new Random();

        for (int i = 0; i < alfabet.length - 1; i++) {
            int j = i + rnd.nextInt(resultado.length - i);
            char lletra = resultado[i];
            resultado[i] = resultado[j];
            resultado[j] = lletra;

        }

        return resultado;
    }

    public String xifraMonoAlfa(String cadena) {
        return transforma(cadena, majuscules, permutat);
    }

    public String desxifraMonoAlfa(String cadena) {
        return transforma(cadena, permutat, majuscules);
    }

    private String transforma(String cadena, char[] origen, char[] desti) {
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

    public int indexOf(char[] array, char c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c)
                return i;
        }
        return -1;
    }
}
