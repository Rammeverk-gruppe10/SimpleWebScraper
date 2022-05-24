package simplewebscraper.datadocument;

import simplewebscraper.DataCollector;
import simplewebscraper.HtmlElements;
import simplewebscraper.WebScraper;

import java.util.HashMap;


public class DataDocumentBackend {

    WebScraper scraper;

    public DataDocumentBackend(WebScraper scraper) {
        this.scraper = scraper;
    }

    private HtmlElements collectData(DataCollector collector) {
        return collector.collectHtmlElements(scraper);
    }

    public HtmlElements getData(String xPath) {
        return collectData(s -> s.getHtmlElementsByXpath(xPath));
    }


    public HtmlElements getData(DataCollector dataCollector) {
        return dataCollector.collectHtmlElements(scraper);
    }

}
