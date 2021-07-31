package com.betterschool.co.news.model;

import java.util.List;

public class newsRoot {
    private int pageId;
    private int eachPerPage;
    private List<news> news;

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getEachPerPage() {
        return eachPerPage;
    }

    public void setEachPerPage(int eachPerPage) {
        this.eachPerPage = eachPerPage;
    }

    public List<com.betterschool.co.news.model.news> getNews() {
        return news;
    }

    public void setNews(List<com.betterschool.co.news.model.news> news) {
        this.news = news;
    }
}
