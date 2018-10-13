package com.allen_anker.dto;

import org.apache.commons.fileupload.FileItem;

public class ImportExcelDto {

    private String title;
    private FileItem excel;

    public ImportExcelDto() {
    }

    public ImportExcelDto(String title, FileItem excel) {
        this.title = title;
        this.excel = excel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FileItem getExcel() {
        return excel;
    }

    public void setExcel(FileItem excel) {
        this.excel = excel;
    }
}
