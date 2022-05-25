package simplewebscraper.datadocument;

import simplewebscraper.IDataCollector;
import simplewebscraper.HtmlElements;
import simplewebscraper.WebScraper;


public class DataDocumentBackend {

    WebScraper scraper;

    public DataDocumentBackend(WebScraper scraper) {
        this.scraper = scraper;
    }

    private HtmlElements collectData(IDataCollector collector) {
        return collector.collectHtmlElements(scraper);
    }

    public HtmlElements getData(String xPath) {
        return collectData(s -> s.getHtmlElementsByXpath(xPath));
    }


    public HtmlElements getData(IDataCollector dataCollector) {
        return dataCollector.collectHtmlElements(scraper);
    }

}
