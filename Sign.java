import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sign {

    public static void createMD5(String[] args) throws FileNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        File file = new File("keys/" + args[2]);
        FileInputStream fis = new FileInputStream(file);
        byte[] byteFile;
        byteFile = readWrite.getBytes(file, fis);
        String hash = "";

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digested = md.digest(byteFile);

        for (byte b : digested) {
            hash += String.format("%02x", b);
        }

        File folder = new File("hash/");
        if (!folder.exists()) {
            folder.mkdir();
        }

        encryptKey(args, hash);


    }

    public static void encryptKey(String[] args, String hash) throws FileNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        if (args[2].contains(".prv") || args[2].contains(".pub")) {
            Encrypt.encryptAsymmetricHash(args, hash);
        } else {
            Encrypt.encryptSymmetricHash(args, hash);
        }
    }
}
