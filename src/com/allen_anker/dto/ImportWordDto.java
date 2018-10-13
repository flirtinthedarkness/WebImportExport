package com.allen_anker.dto;

import org.apache.commons.fileupload.FileItem;

public class ImportWordDto {

    private String title;
    private FileItem word;

    public ImportWordDto() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FileItem getWord() {
        return word;
    }

    public void setWord(FileItem word) {
        this.word = word;
    }
}
