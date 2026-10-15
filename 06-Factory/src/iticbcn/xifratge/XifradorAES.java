package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] xifrat = xifraAES(msg, clau);
            return new TextXifrat(xifrat);
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1);
        }
        return null;
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            return desxifraAES(xifrat.getBytes(), clau);
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1);
        }
        return null;
    }

    public byte[] xifraAES(String msg, String password) throws Exception {

        byte[] newMsg = msg.getBytes(StandardCharsets.UTF_8);
        byte[] newIv = generaIv();

        IvParameterSpec ivPar = new IvParameterSpec(newIv);
        SecretKeySpec clau = generaHash(password);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, clau, ivPar);
        byte[] xifrat = cipher.doFinal(newMsg);

        byte[] resultat = new byte[MIDA_IV + xifrat.length];
        System.arraycopy(newIv, 0, resultat, 0, MIDA_IV);
        System.arraycopy(xifrat, 0, resultat, MIDA_IV, xifrat.length);
        return resultat;
    }

    public String desxifraAES(byte[] bMsgXifrat, String password) throws Exception {
        byte[] ivExtret = Arrays.copyOfRange(bMsgXifrat, 0, MIDA_IV);
        byte[] xifrat = Arrays.copyOfRange(bMsgXifrat, MIDA_IV, bMsgXifrat.length);

        SecretKeySpec clau = generaHash(password);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, clau, new IvParameterSpec(ivExtret));
        byte[] original = cipher.doFinal(xifrat);

        return new String(original, StandardCharsets.UTF_8);
    }

    private byte[] generaIv() {
        byte[] ivec = new byte[MIDA_IV];

        SecureRandom random = new SecureRandom();

        random.nextBytes(ivec);

        return ivec;
    }

    private SecretKeySpec generaHash(String password) throws Exception {

        MessageDigest msg = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] passToByte = password.getBytes(StandardCharsets.UTF_8);
        byte[] hash = msg.digest(passToByte);

        return new SecretKeySpec(hash, ALGORISME_XIFRAT);

    }
}
