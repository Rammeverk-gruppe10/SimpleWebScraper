package simplewebscraper.datadocument;

import simplewebscraper.IDataCollector;
import simplewebscraper.HtmlElement;
import simplewebscraper.HtmlElements;
import simplewebscraper.datawriter.DataWriter;
import simplewebscraper.datawriter.JsonDataWriter;
import simplewebscraper.exception.FieldNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataDocument {
    DataDocumentBackend backend;

    HashMap<String, HtmlElements> fieldsHtmlElements;
    HashMap<String, List<HtmlElement>> fieldsHtmlElement;

    public DataDocument(DataDocumentBackend backend) {
        this.backend = backend;
        this.fieldsHtmlElements = new HashMap<>();
        this.fieldsHtmlElement = new HashMap<>();
    }

    public void addField(String fieldName, String xpath) throws FieldNotFoundException {
        if (!fieldsHtmlElements.containsKey(fieldName))
            fieldsHtmlElements.put(fieldName, backend.getData(xpath));
        else
            throw new FieldNotFoundException("Field already exists");
    }

    public void addField(String fieldName, IDataCollector collector) throws FieldNotFoundException {
        if (!fieldsHtmlElements.containsKey(fieldName))
            fieldsHtmlElements.put(fieldName, backend.getData(collector));
        else
            throw new FieldNotFoundException("Field already exists");
    }


    public HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> documentData = new HashMap<>();
        fieldsHtmlElements.forEach(
                (key, value)
                        -> {
                    if (!documentData.containsKey(key))
                        documentData.put(key, new ArrayList<>());
                        documentData.get(key).addAll(fieldsHtmlElements.get(key).toListAsString());
                }
        );
        return documentData;
    }

    public List<String> getDataByFieldName(String fieldName) throws FieldNotFoundException {
        ArrayList<String> text = new ArrayList<>();
        if (!fieldsHtmlElements.containsKey(fieldName))
            throw new FieldNotFoundException("Field does not exist");

        return fieldsHtmlElements.get(fieldName).toListAsString();
    }

    public void printFields() {
        fieldsHtmlElements.forEach(
                (key, value)
                        -> {
                    for (HtmlElement element : value){
                        System.out.println(element.getText());
                    }
                }
                );
    }

    public String getDataAsJsonString() {
        DataWriter writer = new JsonDataWriter("null");
        return writer.dataAsString(getData());
    }
}
