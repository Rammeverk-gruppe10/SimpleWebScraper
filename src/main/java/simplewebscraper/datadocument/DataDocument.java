package simplewebscraper.datadocument;

import simplewebscraper.DataCollector;
import simplewebscraper.HtmlElement;
import simplewebscraper.HtmlElements;
import simplewebscraper.InvalidColumnException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataDocument {
    DataDocumentBackend backend;

    HashMap<String, HtmlElements> fields;


    public DataDocument(DataDocumentBackend backend) {
        this.backend = backend;
        this.fields = new HashMap<>();
    }

    public void addField(String fieldName, String xpath) throws InvalidColumnException {
        if (!fields.containsKey(fieldName))
            fields.put(fieldName, backend.getData(xpath));
        else
            throw new InvalidColumnException("Field already exists");

    }

    public void addField(String fieldName, DataCollector collector) throws InvalidColumnException {
        if (!fields.containsKey(fieldName))
            fields.put(fieldName, backend.getData(collector));
        else
            throw new InvalidColumnException("Field already exists");

    }

    // Test
    public HashMap<String, List<String>> getCollection() {
        HashMap<String, List<String>> documentData = new HashMap<>();
        fields.forEach(
                (key, value)
                        -> {
                    if (!documentData.containsKey(key))
                        documentData.put(key, new ArrayList<>());
                        documentData.get(key).addAll(fields.get(key).toListAsString());
                }
        );
        return documentData;
    }

    // For testing only
    public void testWrite() {
        fields.forEach(
                (key, value)
                        -> {
                    for (HtmlElement element : value){
                        System.out.println(element.getText());
                    }
                }
                );
    }

}
