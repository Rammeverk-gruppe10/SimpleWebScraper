package simplewebscraper.datadocument;

import simplewebscraper.DataCollector;
import simplewebscraper.HtmlElements;
import simplewebscraper.WebScraper;

import java.util.HashMap;


public class DataDocumentBackend {

    WebScraper scraper;
    HashMap<String, HtmlElements> fields;

    public DataDocumentBackend(WebScraper scraper) {
        this.scraper = scraper;
        this.fields = new HashMap<>();
    }

    private HtmlElements collectData(DataCollector collector) {
        return collector.collectHtmlElements(scraper);
    }

    public void collectData(String field, String xPath) {
        fields.put(field, collectData(s -> s.getHtmlElementsByXpath(xPath)));
    }


    public void collectData(String field, DataCollector dataCollector) {
        fields.put(field, dataCollector.collectHtmlElements(scraper));
    }

    public HtmlElements getData(String field)
    {
        return fields.get(field);
    }
}
