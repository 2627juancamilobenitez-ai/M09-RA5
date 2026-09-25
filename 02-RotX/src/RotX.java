public class RotX{

        char[] miniscules =  {'a','á','à','b','c','ç','d','e','é','è','f','g','h','i','í','ì','ï','j','k','l','m','n',
                'ñ','q','r','s','t','u','ú','ù','ü','v','w','x','y','z'};

        char[] majuscules = {'A','Á','À','B','C','Ç','D','E','É','È','F','G','H','I','Í','Ì','Ï','J','K','L','M','N','Ñ','Q','R','S','T','U','Ú','Ù','Ü','V','W','X','Y','Z'};

        public String xifratRot13 (String cadena){

            String resultat = "";


            for(int i = 0; i < cadena.length(); i++ ){

                boolean trobada = false;
                char lletra = cadena.charAt(i);

                for(int j = 0; j < majuscules.length; j++){
                    if(lletra == majuscules[j]){
                        int posicioNova = (j + 13) % majuscules.length;
                        char lletraXifrada = majuscules[posicioNova];
                        resultat += lletraXifrada;
                        trobada = true;
                    }

                }

                for(int k = 0; k < miniscules.length; k++){
                    if(lletra == miniscules[k]){
                        int posicioNova = (k + 13) % miniscules.length;
                        char lletraXifrada = miniscules[posicioNova];
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

        public String desXifratRot13(String cadena) {

            String resultat = "";


            for (int i = 0; i < cadena.length(); i++) {
                boolean trobada = false;
                char lletra = cadena.charAt(i);

                for (int j = 0; j < majuscules.length; j++) {
                    if (lletra == majuscules[j]) {
                        int posicioNova = (j - 13 + majuscules.length) % majuscules.length;
                        char lletraXifrada = majuscules[posicioNova];
                        resultat += lletraXifrada;
                        trobada = true;
                    }

                }

                for (int k = 0; k < miniscules.length; k++) {
                    if (lletra == miniscules[k]) {
                        int posicioNova = (k - 13 + miniscules.length) % miniscules.length;
                        char lletraXifrada = miniscules[posicioNova];
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
            void main(String [] args) {

                String frase1 = "ABC";
                String frase2 = "XYZ";
                String frase3 = "Hola, Mr. calçot";
                String frase4 = "Perdó, per tu què és?";

                System.out.println("Xifrat");
                System.out.println("--------");

                System.out.printf("%-20s> %s%n", frase1, xifratRot13(frase1));
                System.out.printf("%-20s> %s%n", frase2, xifratRot13(frase2));
                System.out.printf("%-20s> %s%n", frase3, xifratRot13(frase3));
                System.out.printf("%-20s> %s%n", frase4, xifratRot13(frase4));

                System.out.println();
                System.out.println("Desxifrat");
                System.out.println("---------");

                String fraseXifrada1 = xifratRot13(frase1);
                String fraseXifrada2 = xifratRot13(frase2);
                String fraseXifrada3 = xifratRot13(frase3);
                String fraseXifrada4 = xifratRot13(frase4);

                System.out.printf("%-20s => %s%n", fraseXifrada1, desXifratRot13(fraseXifrada1));
                System.out.printf("%-20s => %s%n", fraseXifrada2, desXifratRot13(fraseXifrada2));
                System.out.printf("%-20s=> %s%n", fraseXifrada3, desXifratRot13(fraseXifrada3));
                System.out.printf("%-20s=> %s%n", fraseXifrada4, desXifratRot13(fraseXifrada4));
            }




        }