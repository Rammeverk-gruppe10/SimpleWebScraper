package simplewebscraper.datadocument;

import simplewebscraper.IDataCollector;
import simplewebscraper.HtmlElements;
import simplewebscraper.WebScraper;


/**
 * The type Data document backend.
 */
public class DataDocumentBackend {

    /**
     * The Scraper.
     */
    WebScraper scraper;

    /**
     * Instantiates a new Data document backend.
     *
     * @param scraper the scraper
     */
    public DataDocumentBackend(WebScraper scraper) {
        this.scraper = scraper;
    }

    private HtmlElements collectData(IDataCollector collector) {
        return collector.collectHtmlElements(scraper);
    }

    /**
     * Gets data.
     *
     * @param xPath the x path
     * @return the data
     */
    public HtmlElements getData(String xPath) {
        return collectData(s -> s.getHtmlElementsByXpath(xPath));
    }


    /**
     * Gets data.
     *
     * @param dataCollector the data collector
     * @return the data
     */
    public HtmlElements getData(IDataCollector dataCollector) {
        return dataCollector.collectHtmlElements(scraper);
    }

}
