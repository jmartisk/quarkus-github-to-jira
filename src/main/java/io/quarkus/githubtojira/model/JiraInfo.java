package io.quarkus.githubtojira.model;

import java.util.List;

public class JiraInfo {

    private String key;
    private String url;
    private List<String> gitPullRequestUrls;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getGitPullRequestUrls() {
        return gitPullRequestUrls;
    }

    public void setGitPullRequestUrls(List<String> gitPullRequestUrls) {
        this.gitPullRequestUrls = gitPullRequestUrls;
    }
}
