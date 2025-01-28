import FileParsing.DataFromTables;
import FileParsing.FullJsonObj;
import FileParsing.jsonStation;
import WebParsing.HtmlSubWay.HtmlJsonObj;
import WebParsing.HtmlSubWay.htmlLine;
import WebParsing.HtmlSubWay.htmlStation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class JsonCreate {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void firstJson(List<htmlStation> subStations, List<htmlLine> lines) {
        try {

            MAPPER.enable(SerializationFeature.INDENT_OUTPUT);

            List<HtmlJsonObj> stationsOnLine = getHtmlJsonObjs(subStations, lines);

            MAPPER.writeValue(new File("FilesAndNetwork/DataCollector/src/Result/output.json"), stationsOnLine);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    private static List<HtmlJsonObj> getHtmlJsonObjs(List<htmlStation> subStations, List<htmlLine> lines) {
        List<HtmlJsonObj> stationsOnLine = new ArrayList<>();
        for(htmlLine line : lines) {
            List<String> tmp = new ArrayList<>();
            for(htmlStation station : subStations) {
                if ( line.number().equals(station.stationNumber())){
                    tmp.add(station.stationName());
                }
            }
            HtmlJsonObj result = new HtmlJsonObj(line.number()+ " - "+ line.name(), tmp);
            stationsOnLine.add(result);
        }
        return stationsOnLine;
    }
    public static void secondJson(List<htmlStation> subStations, List<htmlLine> lines, List<jsonStation> depths, List<DataFromTables> dates){


        List<FullJsonObj> stations = new ArrayList<>();
//        for(htmlStation station : subStations){
//            String lineName = "";
//
//        }
        for(htmlStation station : subStations){
            String lineName = "";
            String stationDepth = "";
            String stationDate = "";
            Iterator<htmlLine> iter = lines.iterator();
            Iterator<jsonStation> iter2 = depths.iterator();
            Iterator<DataFromTables> iter3 = dates.iterator();
            while(iter.hasNext()){
                htmlLine tmpLine = iter.next();
                if(tmpLine.number().equals(station.stationNumber())){
                    lineName = tmpLine.name();
                }
            }
            while(iter2.hasNext()){
                jsonStation tmpDepth = iter2.next();
                if(tmpDepth.getStation_name().equals(station.stationName())){
                    stationDepth = tmpDepth.getDepth();
                    iter2.remove();
                }
            }
            while(iter3.hasNext()){
                DataFromTables tmpDate = iter3.next();
                if(tmpDate.getName().equals(station.stationName())){
                    stationDate = tmpDate.getDate();
                    iter3.remove();
                }
            }
            stations.add(new FullJsonObj(station.stationName(), lineName, stationDate, stationDepth));
        }

        MAPPER.enable(SerializationFeature.INDENT_OUTPUT);

        HashMap<String, List<FullJsonObj>> result = new HashMap<>();
        result.put("stations", stations);
        try {
            MAPPER.writerWithDefaultPrettyPrinter()
                    .writeValue(new File("FilesAndNetwork/DataCollector/src/Result/output2.json"), result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //stations.forEach(System.out::println);


    }
}
