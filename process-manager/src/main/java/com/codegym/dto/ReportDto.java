package com.codegym.dto;

public class ReportDto {
    private Integer id;
    private String url;
    private String title;
    private String content;
    private Integer topicProcessId;
    private String date;

    public ReportDto() {
    }

    public ReportDto(Integer id, String url, String title, String content, Integer topicProcessId, String date) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.content = content;
        this.topicProcessId = topicProcessId;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTopicProcessId() {
        return topicProcessId;
    }

    public void setTopicProcessId(Integer topicProcessId) {
        this.topicProcessId = topicProcessId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
