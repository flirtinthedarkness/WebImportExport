package com.allen_anker.dto;

import org.apache.commons.fileupload.FileItem;

public class ImportWordResultDto {

    private String title;
    private String content;
    private String message;

    public ImportWordResultDto() {
    }

    public ImportWordResultDto(String title, String content, String message) {
        this.title = title;
        this.content = content;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
