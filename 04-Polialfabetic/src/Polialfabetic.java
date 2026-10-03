import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabetic {

    
    static final String MAJUSCULES = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ";
    static final String MINUSCULES = "aàábcçdeèéfghiíïjklmnñoòópqrstuúüvwxyz";


    static char[] permMaj = new char[MAJUSCULES.length()];
    static char[] permMin = new char[MINUSCULES.length()];

    static Random rnd;
    static final int clauSecreta = 12345;

    public static void initRandom(int clau) {
        rnd = new Random(clau);
    }

    public static void permutaAlfabet() {
        permMaj = permuta(MAJUSCULES);
        permMin = permuta(MINUSCULES);
    }

    private static char[] permuta(String alfabet) {
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

    private static int indexDe(char[] array, char c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) return i;
        }
        return -1;
    }

    public static String xifraPoliAlfa(String msg) {
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

    public static String desxifraPoliAlfa(String msgXifrat) {
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

    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbitre, coixí, Perímetre",
                "Test 02 Taüll, DÍA, año",
                "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }
}