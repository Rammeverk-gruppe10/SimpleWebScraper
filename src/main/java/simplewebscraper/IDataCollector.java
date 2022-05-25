package simplewebscraper;

/**
 * The interface Data collector.
 */
public interface IDataCollector {
    /**
     * Collect HtmlElements by using lambda expression.
     *
     * @param scraper the scraper
     * @return the html elements
     */
    HtmlElements collectHtmlElements(WebScraper scraper);
}
