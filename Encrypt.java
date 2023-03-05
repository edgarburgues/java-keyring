import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Encrypt {
    public static void encryptSymmetric(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, FileNotFoundException, IllegalBlockSizeException, BadPaddingException {
        byte[] key = null;
        File inputFile = null;
        FileInputStream fis = null;

        String keyName = args[2];
        String inputFileName = args[4];
        String algorithm = args[2].split("\\.")[1];
        String outputFileName = args[4];

        key = readWrite.readKey(keyName);

        inputFile = new File("files/" + inputFileName);

        fis = new FileInputStream(inputFile);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(key, algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] content = readWrite.getBytes(inputFile, fis);
        byte[] encrypted = cipher.doFinal(content);

        readWrite.writeBytesToFile(encrypted, new File("files/" + outputFileName + "_encrypted." + algorithm));

    }


    public static void encryptAsymmetric(String[] args) {

        String publicKeyName = args[2];

        String inputFileName = args[4];
        String outputFileName = args[4];

        String algorithm = args[2].split("\\.")[1];

        byte[] publicKeyBytes = readWrite.readKey(publicKeyName);

        try {
            PublicKey publicKey = KeyFactory.getInstance(algorithm).generatePublic(new X509EncodedKeySpec(publicKeyBytes));

            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            File fileEntrada = new File("files/" + inputFileName);
            FileInputStream fis = new FileInputStream(fileEntrada);

            byte[] content = readWrite.getBytes(fileEntrada, fis);
            byte[] encrypted = cipher.doFinal(content);

            readWrite.writeBytesToFile(encrypted, new File("files/" + outputFileName + "_encrypted." + algorithm));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void encryptSymmetricHash(String[] args, String hash) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, FileNotFoundException {
        byte[] key;

        String keyName = args[2];
        String algorithm = args[2].split("\\.")[1];
        String outputFileName = args[2] + ".sign";

        key = readWrite.readKey(keyName);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(key, algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] content = hash.getBytes();
        byte[] encrypted = cipher.doFinal(content);

        readWrite.writeBytesToFile(encrypted, new File("hash/" + outputFileName));

    }

    public static void encryptAsymmetricHash(String[] args, String hash) {

        String privateKeyName = args[2];

        String algorithm = args[2].split("\\.")[1];

        byte[] privateKeyBytes = readWrite.readKey(privateKeyName);

        try {
            PrivateKey privateKey = KeyFactory.getInstance(algorithm).generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));

            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);

            byte[] content = hash.getBytes();
            byte[] encrypted = cipher.doFinal(content);

            readWrite.writeBytesToFile(encrypted, new File("hash/" + privateKeyName + ".sign"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
