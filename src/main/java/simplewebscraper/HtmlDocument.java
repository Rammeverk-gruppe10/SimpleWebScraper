package simplewebscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * The type Html document.
 */
public class HtmlDocument implements IHtmlDocument {
    /**
     * The Document.
     */
    Document document;

    /**
     * Instantiates a new Html document.
     *
     * @param url the url
     */
    public HtmlDocument(String url) {
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Instantiates a new Html document.
     *
     * @param file the file
     */
    public HtmlDocument(File file) {
        try {
            document = Jsoup.parse(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets html elements by tag.
     *
     * @param tag the tag
     * @return the html elements by tag
     */
    public HtmlElements getHtmlElementsByTag(String tag) {
        return getElements(document.getElementsByTag(tag));
    }

    /**
     * Gets html elements by class name.
     *
     * @param className the class name
     * @return the html elements by class name
     */
    public HtmlElements getHtmlElementsByClassName(String className)
    {
        return getElements(document.getElementsByClass(className));
    }

    /**
     * Gets html element by id.
     *
     * @param id the id
     * @return HtmlElement by id.
     */
    public HtmlElement getHtmlElementById(String id) {
        return new HtmlElement(document.getElementById(id));
    }

    /**
     * Gets html elements by attribute and value.
     *
     * @param attr  the attr
     * @param value the value
     * @return HtmlElements with attribute equal to value.
     */
    public HtmlElements getHtmlElementsByAttributeAndValue(String attr, String value)
    {
        return getElements(document.getElementsByAttributeValue(attr, value));
    }

    /**
     * Gets html elements that contains text.
     *
     * @param text the text
     * @return the html elements that contains text
     */
    public HtmlElements getHtmlElementsThatContains(String text)
    {
        return getElements(document.getElementsContainingText(text));
    }

    /**
     *  Gets html elements by selector syntax
     *
     * @param text the text
     * @return HtmlElements
     */
    public HtmlElements getHtmlElementsUsingSelector(String text) {
        return getElements(document.select(text));
    }

    /**
     * Gets html elements by xpath.
     *
     * @param xpathString the xpath string
     * @return HtmlElements
     */
    public HtmlElements getHtmlElementsByXpath(String xpathString) {
        return getElements(document.selectXpath(xpathString));
    }

    /**
     * Gets the title in the html scraped by WebScraper.
     *
     * @return String of title of page.
     */
    public String title() {
        return document.title();
    }


    private HtmlElements getElements(Elements elements) {
        HtmlElements list = new HtmlElements();

        for (Element element : elements) {
            list.add(new HtmlElement(element));
        }
        return list;
    }
}

