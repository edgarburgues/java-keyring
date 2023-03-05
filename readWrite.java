import java.io.*;
import java.security.PublicKey;

public class readWrite {
    public static void writeBytesToFile(byte[] bytes, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.close();
        } catch (Exception e) {
            System.out.println("Error writing file");
        }
    }

    public static byte[] readKey(String name) {
        File folder = new File("keys");
        if (!folder.exists()) {
            folder.mkdir();
        }

        try {
            File file = new File("keys/" + name);
            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fis.read(bytes);
            fis.close();
            return bytes;
        } catch (Exception e) {
            System.out.println("Error reading file");
        }
        return null;
    }


    public static byte[] getBytes(File inputFile, FileInputStream fis) {
        byte[] content = new byte[(int) inputFile.length()];
        try {
            fis.read(content);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static void writeText(File file, String text) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
