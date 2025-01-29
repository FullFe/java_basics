package FileParsing;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileFinder {

    static List<String> jsonFiles = new ArrayList<>();
    static List<String> csvFiles = new ArrayList<>();
    public static List<List<String>> find(String path){

        File f = new File(path);
        Arrays.stream(Objects.requireNonNull((f).listFiles())).forEach(file -> {
                if(file.isDirectory()){
                    find(file.getAbsolutePath());
                }
                if(file.isFile() && (file.getName().endsWith(".csv") || file.getName().endsWith(".json"))){
                    if(file.getName().endsWith(".csv")){
                        csvFiles.add(file.getAbsolutePath());
                    }else if(file.getName().endsWith(".json")){
                        jsonFiles.add(file.getAbsolutePath());
                    }
                    System.out.println("File Found " + file.getName());
                    System.out.println("Path to file: "+ file.getAbsolutePath());
                    System.out.println("File weight: "+ file.length() + " b"+ "\n");
                }
            }
        );
        List<List<String>> results = new ArrayList<>();
        results.add(jsonFiles);
        results.add(csvFiles);
        return results;
    }
}
