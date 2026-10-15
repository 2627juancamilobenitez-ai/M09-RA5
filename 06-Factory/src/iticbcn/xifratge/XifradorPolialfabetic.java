package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {

    static final String MAJUSCULES = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ";
    static final String MINUSCULES = "aàábcçdeèéfghiíïjklmnñoòópqrstuúüvwxyz";

    char[] permMaj = new char[MAJUSCULES.length()];
    char[] permMin = new char[MINUSCULES.length()];

    Random rnd;

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long llavor = convertirClau(clau,
                "La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        initRandom(llavor);
        String xifrat = xifraPoliAlfa(msg);
        return new TextXifrat(xifrat.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long llavor = convertirClau(clau,
                "La clau de Polialfabètic ha de ser un String convertible a long");
        initRandom(llavor);
        String text = new String(xifrat.getBytes(), StandardCharsets.UTF_8);
        return desxifraPoliAlfa(text);
    }

    private long convertirClau(String clau, String missatge) throws ClauNoSuportada {
        try {
            return Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada(missatge);
        }
    }

    public void initRandom(long clau) {
        rnd = new Random(clau);
    }

    public void permutaAlfabet() {
        permMaj = permuta(MAJUSCULES);
        permMin = permuta(MINUSCULES);
    }

    private char[] permuta(String alfabet) {
        List<Character> llista = new ArrayList<>();
        for (char c : alfabet.toCharArray()) {
            llista.add(c);
        }
        Collections.shuffle(llista, rnd);
        char[] resultat = new char[llista.size()];
        for (int i = 0; i < resultat.length; i++) {
            resultat[i] = llista.get(i);
        }
        return resultat;
    }

    private int indexDe(char[] array, char c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c)
                return i;
        }
        return -1;
    }

    public String xifraPoliAlfa(String msg) {
        StringBuilder sb = new StringBuilder();
        for (char c : msg.toCharArray()) {
            permutaAlfabet();
            int pos = MAJUSCULES.indexOf(c);
            if (pos >= 0) {
                sb.append(permMaj[pos]);
            } else {
                pos = MINUSCULES.indexOf(c);
                if (pos >= 0) {
                    sb.append(permMin[pos]);
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    public String desxifraPoliAlfa(String msgXifrat) {
        StringBuilder sb = new StringBuilder();
        for (char c : msgXifrat.toCharArray()) {
            permutaAlfabet();
            int pos = indexDe(permMaj, c);
            if (pos >= 0 && Character.isUpperCase(c)) {
                sb.append(MAJUSCULES.charAt(pos));
            } else {
                pos = indexDe(permMin, c);
                if (pos >= 0) {
                    sb.append(MINUSCULES.charAt(pos));
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
