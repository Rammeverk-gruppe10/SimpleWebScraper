package simplewebscraper;

import java.io.File;

public class WebScraperBuilder {
    public String url;
    public File file;

    public WebScraperBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public WebScraperBuilder setFile(File file) {
        this.file = file;
        return this;
    }

    public WebScraper build() throws Exception {
        if (this.url != null) {
            return WebScraper.get(this.url);
        }
        else if (this.file != null)
        {
            return WebScraper.get(file);
        }
        else {
            throw new Exception("Either url or file have to be set to create a WebScraper class");
        }
    }
}
