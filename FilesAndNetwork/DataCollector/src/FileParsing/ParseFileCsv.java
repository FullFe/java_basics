package FileParsing;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ParseFileCsv {
public static List<DataFromTables> parseCsv(String fileName){
    List<DataFromTables> list = new ArrayList<>();
    try {
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(true)
                .build();

        CSVReader csvReader = new CSVReaderBuilder(new FileReader(fileName))
                .withSkipLines(1)
                .withCSVParser(parser)
                .build();
        String[] line;
        while ((line = csvReader.readNext()) != null) {
           list.add(new DataFromTables(line[0], line[1]));
        }

        csvReader.close();
    }
    catch (Exception e) {
        e.printStackTrace();
    }
    return list;
    }
}
