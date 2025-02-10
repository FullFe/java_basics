import java.io.File;

public class Main {

    private final static int CORE_AMOUNT = 6;

    public static void main(String[] args) {
        /*
        Поменять эти строки на нужные
         */
//        String srcFolder = "/users/sortedmap/Desktop/src";
//        String dstFolder = "/users/sortedmap/Desktop/dst";

        String srcFolder = "C:/Users/alexp/OneDrive/Рабочий стол/PICS_N_VIDS/photo_from_army";
        String dstFolder = "C:/Users/alexp/OneDrive/Рабочий стол/PICS_N_VIDS/newArmy";
        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        if (files != null) {
            File[][] chunks = splitArray(files);

            for (File[] chunk : chunks) {
                new ImageResizer(chunk,start,dstFolder).start();
            }
        }

    }

    public static File[][] splitArray(File[] array) {

        int chunkSize = array.length / CORE_AMOUNT;
        int remainder = array.length % CORE_AMOUNT;
        File[][] chunks = new File[CORE_AMOUNT][];
        int count = 0;
        for (int i = 0; i < CORE_AMOUNT; i++) {

            int arrSize = chunkSize;
            if(remainder > 0) {
                arrSize++;
                remainder--;
            }
            File[] chunk = new File[arrSize];
            for (int j = 0; j < arrSize; j++) {
                chunk[j] = array[count++];
            }
            chunks[i] = chunk;
        }

        return chunks;
    }
}
