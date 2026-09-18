public class Rot13{
    
    char[] miniscules =  {'a','á','à','b','c','ç','d','e','é','è','f','g','h','i','í','ì','ï','j','k','l','m','n',
            'ñ','q','r','s','t','u','ú','ù','ü','v','w','x','y','z'};

    char[] majuscules = {'A','Á','À','B','C','Ç','D','E','É','È','F','G','H','I','Í','Ì','Ï','J','K','L','M','N','Ñ','Q','R','S','T','U','Ú','Ù','Ü','V','W','X','Y','Z'};

    public String xifratRot13 (String cadena){

        String resultat = "";
        
        for(int i = 0; i < cadena.length(); i++ ){ 
            
            char lletra = cadena.charAt(i);

            for(int j = 0; j < majuscules.length; j++){
                if(lletra == majuscules[j]){
                    int posicioNova = (j + 13) % majuscules.length;
                    char lletraXifrada = majuscules[posicioNova];
                    resultat += lletraXifrada;
                }

            }

            for(int k = 0; k < miniscules.length; k++){
                if(lletra == miniscules[k]){
                    int posicioNova = (k + 13) % miniscules.length;
                    char lletraXifrada = miniscules[posicioNova];
                    resultat += lletraXifrada;
                }
            }

        }

        return resultat;

    }

    public String desXifratRot13(String cadena){

        String resultat = "";

        for(int i = 0; i < majuscules.length; i++){

        }

        return resultat;
    }


    void main() {

        String frase = "ABC";

        System.out.println("Xifrat");

        System.out.println(xifratRot13(frase));

    }





}