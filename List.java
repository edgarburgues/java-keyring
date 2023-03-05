import java.io.File;

public class List {

    public static void listKeys(File folder) {
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles.length == 0) {
            System.out.println("No keys found");
        } else {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                }
            }
        }
    }
}
