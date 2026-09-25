
import javax.sound.sampled.SourceDataLine;

public class RotX{

    char[] minuscules =  {'a','á','à','b','c','ç','d','e','é','è','f','g','h','i','í','ì','ï','j','k','l','m','n',
            'ñ','o','ó','ò','p','q','r','s','t','u','ú','ù','ü','v','w','x','y','z'};

    char[] majuscules = {'A','Á','À','B','C','Ç','D','E','É','È','F','G','H','I','Í','Ì','Ï','J','K','L','M','N','Ñ','O','Ó','Ò','P','Q','R','S','T','U','Ú','Ù','Ü','V','W','X','Y','Z'};

    public String xifratRotX (String cadena, int desplaçament){

        String resultat = "";


        for(int i = 0; i < cadena.length(); i++ ){

            boolean trobada = false;
            char lletra = cadena.charAt(i);

            for(int j = 0; j < majuscules.length; j++){
                if(lletra == majuscules[j]){
                    int posicioNova = (j + desplaçament) % majuscules.length;
                    char lletraXifrada = majuscules[posicioNova];
                    resultat += lletraXifrada;
                    trobada = true;
                }

            }

            for(int k = 0; k < minuscules.length; k++){
                if(lletra == minuscules[k]){
                    int posicioNova = (k + desplaçament) % minuscules.length;
                    char lletraXifrada = minuscules[posicioNova];
                    resultat += lletraXifrada;
                    trobada = true;
                }
            }

            if (!trobada){
                resultat += lletra;
            }


        }

        return resultat;

    }

    public String desXifratRotX(String cadena, int desplaçament) {

        String resultat = "";


        for (int i = 0; i < cadena.length(); i++) {
            boolean trobada = false;
            char lletra = cadena.charAt(i);

            for (int j = 0; j < majuscules.length; j++) {
                if (lletra == majuscules[j]) {
                    int posicioNova = (j - desplaçament + majuscules.length) % majuscules.length;
                    char lletraXifrada = majuscules[posicioNova];
                    resultat += lletraXifrada;
                    trobada = true;
                }

            }

            for (int k = 0; k < minuscules.length; k++) {
                if (lletra == minuscules[k]) {
                    int posicioNova = (k - desplaçament + minuscules.length) % minuscules.length;
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


    void main(String [] args) {

        String frase1 = "ABC";
        String frase2 = "XYZ";
        String frase3 = "Hola, Mr. calçot";
        String frase4 = "Perdó, per tu què és?";

        System.out.println("Xifrat");
        System.out.println("--------");

        System.out.printf("%-20s> %s%n", frase1, xifratRotX(frase1, 0));
        System.out.printf("%-20s> %s%n", frase2, xifratRotX(frase2, 2));
        System.out.printf("%-20s> %s%n", frase3, xifratRotX(frase3, 4));
        System.out.printf("%-20s> %s%n", frase4, xifratRotX(frase4, 6));

        System.out.println();
        System.out.println("Desxifrat");
        System.out.println("---------");

        String fraseXifrada1 = xifratRotX(frase1, 0);
        String fraseXifrada2 = xifratRotX(frase2, 2);
        String fraseXifrada3 = xifratRotX(frase3, 4);
        String fraseXifrada4 = xifratRotX(frase4, 6);

        System.out.printf("%-20s => %s%n", fraseXifrada1, desXifratRotX(fraseXifrada1, 0));
        System.out.printf("%-20s => %s%n", fraseXifrada2, desXifratRotX(fraseXifrada2, 2));
        System.out.printf("%-20s=> %s%n", fraseXifrada3, desXifratRotX(fraseXifrada3, 4));
        System.out.printf("%-20s=> %s%n", fraseXifrada4, desXifratRotX(fraseXifrada4, 6));

        System.out.println("Missatge Xifrat " + fraseXifrada4);
        System.out.println("---------");
        forcaBrutaRotX(fraseXifrada4);

    }




}