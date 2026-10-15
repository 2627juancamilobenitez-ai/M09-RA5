package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;

public class XifradorRotX implements Xifrador {

    char[] minuscules = { 'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j',
            'k', 'l', 'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y',
            'z' };

    char[] majuscules = { 'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J',
            'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y',
            'Z' };

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplacament = validaClau(clau);
        String xifrat = xifratRotX(msg, desplacament);
        return new TextXifrat(xifrat.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desplacament = validaClau(clau);
        String text = new String(xifrat.getBytes(), StandardCharsets.UTF_8);
        return desXifratRotX(text, desplacament);
    }

    private int validaClau(String clau) throws ClauNoSuportada {
        try {
            int desplacament = Integer.parseInt(clau);
            if (desplacament < 0 || desplacament > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            return desplacament;
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    public String xifratRotX(String cadena, int desplacament) {

        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {

            boolean trobada = false;
            char lletra = cadena.charAt(i);

            for (int j = 0; j < majuscules.length; j++) {
                if (lletra == majuscules[j]) {
                    int posicioNova = (j + desplacament) % majuscules.length;
                    char lletraXifrada = majuscules[posicioNova];
                    resultat += lletraXifrada;
                    trobada = true;
                }
            }

            for (int k = 0; k < minuscules.length; k++) {
                if (lletra == minuscules[k]) {
                    int posicioNova = (k + desplacament) % minuscules.length;
                    char lletraXifrada = minuscules[posicioNova];
                    resultat += lletraXifrada;
                    trobada = true;
                }
            }

            if (!trobada) {
                resultat += lletra;
            }
        }

        return resultat;
    }

    public String desXifratRotX(String cadena, int desplacament) {

        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            boolean trobada = false;
            char lletra = cadena.charAt(i);

            for (int j = 0; j < majuscules.length; j++) {
                if (lletra == majuscules[j]) {
                    int posicioNova = (j - desplacament + majuscules.length) % majuscules.length;
                    char lletraXifrada = majuscules[posicioNova];
                    resultat += lletraXifrada;
                    trobada = true;
                }
            }

            for (int k = 0; k < minuscules.length; k++) {
                if (lletra == minuscules[k]) {
                    int posicioNova = (k - desplacament + minuscules.length) % minuscules.length;
                    char lletraXifrada = minuscules[posicioNova];
                    resultat += lletraXifrada;
                    trobada = true;
                }
            }

            if (!trobada) {
                resultat += lletra;
            }
        }

        return resultat;
    }

    public void forcaBrutaRotX(String cadenaXifrada) {

        for (int i = 0; i < majuscules.length; i++) {

            String resultat = "";

            for (int j = 0; j < cadenaXifrada.length(); j++) {

                char lletra = cadenaXifrada.charAt(j);
                boolean trobada = false;

                for (int k = 0; k < majuscules.length; k++) {

                    if (majuscules[k] == lletra) {

                        int posicio = (k + i) % majuscules.length;
                        resultat += majuscules[posicio];

                        trobada = true;
                        break;
                    }
                }

                if (!trobada) {

                    for (int l = 0; l < minuscules.length; l++) {

                        if (minuscules[l] == lletra) {

                            int posicio = (l + i) % minuscules.length;
                            resultat += minuscules[posicio];

                            trobada = true;
                            break;
                        }
                    }
                }

                if (!trobada) {
                    resultat += lletra;
                }
            }

            System.out.println("Desplaçament " + i + ": " + resultat);
        }
    }
}
