package simplewebscraper.datadocument;

import simplewebscraper.IDataCollector;
import simplewebscraper.HtmlElements;
import simplewebscraper.WebScraper;


/**
 * The type Data document backend. Is used to get data in DataDocument.
 */
public class DataDocumentBackend {

    /**
     * The Scraper.
     */
    WebScraper scraper;


    /**
     * Create DataDocumentBackend
     *
     * @param scraper the scraper
     * @return the data document backend
     */
    public static DataDocumentBackend create(WebScraper scraper) {
        return new DataDocumentBackend(scraper);
    }

    private DataDocumentBackend(WebScraper scraper) {
        this.scraper = scraper;
    }

    private HtmlElements collectData(IDataCollector collector) {
        return collector.collectHtmlElements(scraper);
    }

    /**
     * Gets data as HtmlElements by using xpath syntax.
     *
     * @param xPath the x path
     * @return the data as HtmlElements
     */
    public HtmlElements getData(String xPath) {
        return collectData(s -> s.getHtmlElementsByXpath(xPath));
    }


    /**
     * Gets data as HtmlElements by using IDataCollector (lambda expression).
     *
     * @param dataCollector the data collector
     * @return the data
     */
    public HtmlElements getData(IDataCollector dataCollector) {
        return dataCollector.collectHtmlElements(scraper);
    }

}
