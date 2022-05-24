package simplewebscraper.datawriter;

import org.json.CDL;
import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The type Json data writer.
 */
public class JsonDataWriter implements DataWriter {
    /**
     * The File.
     */
    String fileName;

    /**
     * Instantiates a new Json data writer.
     *
     * @param fileName the file name
     */
    public JsonDataWriter(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Writes data to a JSON-file
     * @param columnNames name of all columns created
     * @param columns HashMap of all data created
     */
    @Override
    public void writeDataToFile(ArrayList<String> columnNames, HashMap<String, List<String>> columns) {
        ArrayList<String> dataContent = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        JSONArray ja = new JSONArray();
        for (String key : columns.keySet()) {
            ja.put(key);
        }

        int max = 0;
        for (String s : columns.keySet()) {
            if (columns.get(s).size() >= max)
                max = columns.get(s).size();
        }

        for (int i = 0; i < max; i++) {
            for (String s : columns.keySet()) {
                if (columns.get(s).get(i) != null)
                    dataContent.add(columns.get(s).get(i));
                else
                    dataContent.add("Null");
            }

            for (int j = 0; j < dataContent.size(); j++) {
                sb.append(dataContent.get(j).replace(",", "."));

                if (j == dataContent.size() - 1) {
                    sb.append("\n");
                } else {
                    sb.append(", ");
                }
            }

            dataContent.clear();
        }

        JSONArray result = CDL.toJSONArray(ja, sb.toString());

        try {
            FileWriter file = new FileWriter(fileName);
            file.write(result.toString(4));
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


