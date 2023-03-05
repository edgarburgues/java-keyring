import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;

public class Decrypt {
    public static void decryptSymmetric(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, FileNotFoundException, IllegalBlockSizeException, BadPaddingException {
        byte[] key = null;
        File inputFile = null;
        FileInputStream fis = null;

        String keyName = args[2];
        String algorithm = args[2].split("\\.")[1];
        String inputFileName = args[4];
        String outputFileName = args[4];

        key = readWrite.readKey(keyName);
        inputFile = new File("files/" + inputFileName);
        fis = new FileInputStream(inputFile);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(key, algorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] content = readWrite.getBytes(inputFile, fis);
        byte[] decrypted = cipher.doFinal(content);

        readWrite.writeBytesToFile(decrypted, new File("files/" + outputFileName + ".decrypted.txt"));


    }

    public static void decryptAsymmetric(String[] args) {
        String privateKeyName = args[2];

        String inputFileName = args[4];
        String outputFileName = args[4];

        String algorithm = args[2].split("\\.")[1];

        File outputFile = new File("files/" + outputFileName);

        byte[] privateKeyBytes = readWrite.readKey(privateKeyName);

        try {
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
            PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));

            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            File entrada = new File("files/" + inputFileName);
            FileInputStream fis = new FileInputStream(entrada);

            byte[] content = readWrite.getBytes(entrada, fis);
            byte[] decrypted = cipher.doFinal(content);

            readWrite.writeBytesToFile(decrypted, new File(outputFile + ".decrypted.txt"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
