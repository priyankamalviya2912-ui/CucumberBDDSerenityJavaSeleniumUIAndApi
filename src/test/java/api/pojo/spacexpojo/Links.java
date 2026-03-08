package api.pojo.spacexpojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Links {

    private String webcast;
    private String article;
    private String wikipedia;

    public String getWebcast() {
        return webcast;
    }

    public void setWebcast(String webcast) {
        this.webcast = webcast;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }
}