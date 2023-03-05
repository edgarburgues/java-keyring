import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, FileNotFoundException, IllegalBlockSizeException, BadPaddingException {


        if (args.length == 0) {
            printLogo();
            System.out.println("No arguments, type --help or -h for help");
        } else {
                switch (args[0]) {
                    case "--help":
                    case "-h":
                        printLogo();
                        printHelp();
                        break;
                    case "--version":
                    case "-v":
                        printVersion();
                        break;
                    case "create":
                        if (checkArgsLength(args, 9)) {
                            Acciones.create(args);
                        }
                        break;
                    case "decrypt":
                        if (checkArgsLength(args, 5)) {
                            Acciones.decrypt(args);
                        }
                        break;
                    case "encrypt":
                        if (checkArgsLength(args, 5)) {
                            Acciones.encrypt(args);
                        }
                        break;
                    case "sign":
                        if (checkArgsLength(args, 3)) {
                            Acciones.sign(args);
                        }
                        break;
                    case "view":
                        if (checkArgsLength(args, 3)) {
                            Acciones.view(args);
                        }
                        break;
                    case "list":
                        if (checkArgsLength(args, 1)) {
                            Acciones.list(args);
                        }
                        break;
                    default:
                        printLogo();
                        System.out.println("Type --help or -h for help");
                        break;
                }

        }
    }

    private static void printLogo() {
        System.out.println(" _  _________   ______  ___ _   _  ____ \n" +
                "| |/ / ____\\ \\ / /  _ \\|_ _| \\ | |/ ___|\n" +
                "| ' /|  _|  \\ V /| |_) || ||  \\| | |  _ \n" +
                "| . \\| |___  | | |  _ < | || |\\  | |_| |\n" +
                "|_|\\_\\_____| |_| |_| \\_\\___|_| \\_|\\____|\n");
    }

    public static void printHelp() {
        System.out.println("create");
        System.out.println("\t --type S|A --length length --algorithm algorithm");

        System.out.println("encrypt");
        System.out.println("\t --private *.RSA.prv --input file.txt");
        System.out.println("\t --private *.RSA.prv -i file.txt");

        System.out.println("decrypt");
        System.out.println("\t --public *.RSA.pub --input file.txt");
        System.out.println("\t --public *.RSA.pub -i file.txt");

        System.out.println("sign");
        System.out.println("\t --input keyName");
        System.out.println("\t -i keyName");

        System.out.println("view");
        System.out.println("\t --input key");
        System.out.println("\t -i key");

        System.out.println("list");
    }

    public static void printVersion() {
        System.out.println("Version 1.1");
    }

    public static boolean checkArgsLength(String[] args, int length) {
        if (args.length != length) {
            System.out.println("Error: incorrect number of arguments");
            System.out.println("To know all the commands, write --help");
            return false;
        }
        return true;
    }


}