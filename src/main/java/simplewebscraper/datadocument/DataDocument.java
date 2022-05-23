package simplewebscraper.datadocument;

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

    public void addField(String fieldName) {
        if (!fields.containsKey(fieldName))
            fields.put(fieldName, backend.getData(fieldName));
        else
            try {
                throw new InvalidColumnException("Field already exists");
            } catch (InvalidColumnException e) {
                e.printStackTrace();
            }
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
