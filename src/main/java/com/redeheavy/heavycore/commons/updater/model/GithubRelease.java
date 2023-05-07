package com.redeheavy.heavycore.commons.updater.model;

public class GithubRelease {

    private final String name;
    private final String tag_name;
    private final String html_url;
    private final boolean prerelease;
    private final boolean draft;
    public GithubRelease(String name, String tag_name, String html_url, boolean prerelease, boolean draft) {
        this.name = name;
        this.html_url = html_url;
        this.tag_name = tag_name;
        this.prerelease = prerelease;
        this.draft = draft;
    }

    public String getReleaseName() {
        return name;
    }

    public String getVersion() {
        return tag_name;
    }

    public String getDownloadURL() {
        return html_url;
    }

    public boolean isPreRelease() {
        return prerelease;
    }

    public boolean isDraft() {
        return draft;
    }
}
