package simplewebscraper.datadocument;

import simplewebscraper.DataCollector;
import simplewebscraper.HtmlElement;
import simplewebscraper.HtmlElements;
import simplewebscraper.InvalidColumnException;

import java.util.HashMap;

public class DataDocument {
    DataDocumentBackend backend;

    HashMap<String, HtmlElements> fields;

    public DataDocument(DataDocumentBackend backend) {
        this.backend = backend;
        this.fields = new HashMap<>();
    }

    public void addField(String fieldName, String xpath) {
        if (!fields.containsKey(fieldName))
            fields.put(fieldName, backend.getData(xpath));
        else
            try {
                throw new InvalidColumnException("Field already exists");
            } catch (InvalidColumnException e) {
                e.printStackTrace();
            }
    }

    public void addField(String fieldName, DataCollector collector) {
        if (!fields.containsKey(fieldName))
            fields.put(fieldName, backend.getData(collector));
        else
            try {
                throw new InvalidColumnException("Field already exists");
            } catch (InvalidColumnException e) {
                e.printStackTrace();
            }
    }

    // Test
    public HashMap<String, HtmlElements> getCollection() {
        return fields;
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
