package simplewebscraper;

public interface IHtmlDocument {
    HtmlElements getHtmlElementsByTag(String tag);
    HtmlElements getHtmlElementsByClassName(String className);
    HtmlElement getHtmlElementById(String id);
    HtmlElements getHtmlElementsByAttributeAndValue(String attr, String value);
    HtmlElements getHtmlElementsThatContains(String text);
    HtmlElements getHtmlElementsUsingSelector(String text);
    HtmlElements getHtmlElementsByXpath(String xpathString);
    String title();
}
