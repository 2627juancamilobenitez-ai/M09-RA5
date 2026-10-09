
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import javax.crypto.SecretKey;

public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "DnTLOkToDip12";



    public static byte[] xifraAES(String msg, String password) throws Exception{
        
        byte [] newMsg = msg.getBytes(StandardCharsets.UTF_8);
        byte [] newIv = generaIv();

        IvParameterSpec ivPar = new IvParameterSpec(newIv);
        SecretKeySpec clau = generaHash(password);

        Cipher cipher =Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, clau, ivPar);  
        byte[] xifrat = cipher.doFinal(newMsg);

        byte[] resultat = new byte[MIDA_IV + xifrat.length];
        System.arraycopy(newIv, 0, resultat, 0, MIDA_IV);
        System.arraycopy(xifrat, 0, resultat, MIDA_IV, xifrat.length);
        return resultat;
    }

    public static String desxifraAES(byte[] bMsgXifrat, String password ) throws Exception{
        byte[] ivExtret = Arrays.copyOfRange(bMsgXifrat, 0, MIDA_IV);
        byte[] xifrat = Arrays.copyOfRange(bMsgXifrat, MIDA_IV, bMsgXifrat.length);

        SecretKeySpec clau = generaHash(password);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, clau, new IvParameterSpec(ivExtret));
        byte [] original = cipher.doFinal(xifrat);

        return new String(original, StandardCharsets.UTF_8);
    }
    

    private static byte[] generaIv(){
        byte[] ivec = new byte[MIDA_IV];

        SecureRandom random = new SecureRandom();

        random.nextBytes(ivec);

        return ivec;
    }

    private static SecretKeySpec generaHash(String password) throws Exception {

        MessageDigest msg = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] passToByte = password.getBytes(StandardCharsets.UTF_8);
        byte[] hash = msg.digest(passToByte);

        return new SecretKeySpec(hash, ALGORISME_XIFRAT);

    }
    
     public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                         "Hola Andrés cómo está tu cuñado",
                         "Àgora ïlla Ôtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                        + e.getLocalizedMessage());
            }
            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
     }

}

