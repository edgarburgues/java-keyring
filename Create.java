import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class Create {
    public static void create(String[] args) throws NoSuchAlgorithmException {
        char type = args[2].toUpperCase().charAt(0);
        String name = args[4];
        String length = args[6];
        String algorithm = args[8].toUpperCase();
        byte[] keyGen = null;
        KeyPair KeyPair = null;

        if (type == 'S') {
            keyGen = Create.createSymmetricKey(name,algorithm, length);
        } else if (type == 'A') {
            KeyPair = Create.createAsymmetricKey(name, algorithm, length);
        } else {
            System.out.println("Error: type must be S for symmetric or A for asymmetric");
        }

        if (keyGen != null) {
            Create.saveSymmetricKey(keyGen, name, algorithm);
        } else if (KeyPair != null) {
            Create.saveAsymmetricKey(KeyPair, name, algorithm);
        } else {
            System.out.println("Error: key not created");
        }
    }


    public static byte[] createSymmetricKey(String name, String length, String algorithm) throws NoSuchAlgorithmException {
        byte[] llaveBytes = null;
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128);
        SecretKey llave = generator.generateKey();
        llaveBytes = llave.getEncoded();

        return llaveBytes;
    }

    public static void saveSymmetricKey(byte[] llaveBytes, String name, String algorithm) {
        File file = new File("keys/" + name + "." + algorithm);
        try {
            if (file.createNewFile()) {
                readWrite.writeBytesToFile(llaveBytes, file);
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            System.out.println("Error creating file");
        }

    }

    public static KeyPair createAsymmetricKey(String name, String length, String algorithm) {
        KeyPairGenerator generador;
        try {
            generador = KeyPairGenerator.getInstance("RSA");
            generador.initialize(2048);
            KeyPair llaves = generador.generateKeyPair();
            return llaves;
        } catch (Exception e) {
            System.out.println("Key generation failed");
        }
        return null;
    }


    public static void saveAsymmetricKey(KeyPair KeyPair, String name, String algorithm) {
        KeyPair llaves = KeyPair;
        File filePublic = new File("keys/" + name + "." + algorithm + ".pub");
        File filePrivate = new File("keys/" + name + "." + algorithm + ".prv");

        try {
            if (filePublic.createNewFile() && filePrivate.createNewFile()) {
                readWrite.writeBytesToFile(llaves.getPublic().getEncoded(), filePublic);
                readWrite.writeBytesToFile(llaves.getPrivate().getEncoded(), filePrivate);
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            System.out.println("Error creating file");
        }
    }
}
