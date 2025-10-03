package io.quarkus.githubtojira.model;

public class PullRequestInfo {

    private String url;
    private String title;
    private Integer number;
    private JiraInfo existingJira;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public JiraInfo getExistingJira() {
        return existingJira;
    }

    public void setExistingJira(JiraInfo existingJira) {
        this.existingJira = existingJira;
    }

    @Override
    public String toString() {
        return "PullRequestInfo{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", number=" + number +
                ", existingJira=" + existingJira +
                '}';
    }
}
