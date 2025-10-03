package io.quarkus.githubtojira.model;

import java.util.Arrays;

public class ProjectInfo {

    private String title;
    private Integer number;
    private String[] versions;

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

    public String[] getVersions() {
        return versions;
    }

    public void setVersions(String[] versions) {
        this.versions = versions;
    }

    @Override
    public String toString() {
        return "ProjectInfo{" +
                "title='" + title + '\'' +
                ", number=" + number +
                ", versions=" + Arrays.toString(versions) +
                '}';
    }
}
