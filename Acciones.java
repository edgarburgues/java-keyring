import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Acciones {
    public static void create(String[] args) throws NoSuchAlgorithmException {
        File folder = new File("keys/");
        if (!folder.exists()) {
            folder.mkdir();
        }
        Create.create(args);
    }

    public static void encrypt(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, FileNotFoundException, IllegalBlockSizeException, BadPaddingException {
        File folder = new File("files");
        if (!folder.exists()) {
            folder.mkdir();
        }
        if (args[1].equals("--public")) {
            Encrypt.encryptAsymmetric(args);
        } else {
            Encrypt.encryptSymmetric(args);
        }
    }

    public static void decrypt(String[] args) throws FileNotFoundException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        File folder = new File("files");
        if (!folder.exists()) {
            folder.mkdir();
        }
        if (args[1].equals("--private")) {
            Decrypt.decryptAsymmetric(args);
        } else {
            Decrypt.decryptSymmetric(args);
        }
    }

    public static void sign(String[] args) throws FileNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        File folder = new File("keys/");
        if (!folder.exists()) {
            folder.mkdir();
        }
        Sign.createMD5(args);
    }

    public static void view(String[] args) {

        View.view(args);
    }

    public static void list(String[] args) {
        File folder = new File("keys/");
        if (!folder.exists()) {
            folder.mkdir();
        }
        List.listKeys(folder);
    }

}
