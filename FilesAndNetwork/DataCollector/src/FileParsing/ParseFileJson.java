package FileParsing;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParseFileJson {

public static List<jsonStation> parseJSON(String fileName) {
    List<jsonStation> stations = new ArrayList<>();

    try {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File(fileName);
        JsonNode jsnode = objectMapper.readTree(file);
        if (jsnode.isArray()) {
            // yes, loop the JsonNode and display one by one
            for (JsonNode node : jsnode) {

                stations.add(new jsonStation(node.get("station_name").asText(), node.get("depth").asText()));
            }
        }
//        jsonArray.forEach(item -> {
//            SubStation subStation = new SubStation(((JSONObject) item).get("station_name").toString(), ((JSONObject) item).get("depth").toString());
//            stations.add(subStation);
//        });
    }catch (Exception fe){
        fe.printStackTrace();
    }


    return stations;
}

}
