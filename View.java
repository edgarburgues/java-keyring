import java.io.File;

public class View {
    public static void view(String[] args) {
        String fileName = args[2];
        byte[] byteKey = readWrite.readKey(fileName);

        int j = 0;
        String key = "";

        for (byte b : byteKey) {
            key +=
                    String.format("%02x", b);
            j++;
            if (j%25 == 0 && j != 0) {
                key += "\n";
            }

        }

        System.out.println(key);


    }

}
