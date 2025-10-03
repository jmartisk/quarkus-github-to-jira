package io.quarkus.githubtojira.model;

import java.util.List;

public class PullRequestInfo {

    private String url;
    private String title;
    private Integer number;
    private List<JiraInfo> existingJiras;

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

    public List<JiraInfo> getExistingJiras() {
        return existingJiras;
    }

    public void setExistingJiras(List<JiraInfo> existingJiras) {
        this.existingJiras = existingJiras;
    }

    @Override
    public String toString() {
        return "PullRequestInfo{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", number=" + number +
                ", existingJiras=" + existingJiras +
                '}';
    }
}
