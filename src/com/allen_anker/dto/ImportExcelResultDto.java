package com.allen_anker.dto;

import com.allen_anker.entity.Student;

import java.util.List;

public class ImportExcelResultDto {

    private String title;
    private List<Student> students;
    private String message;

    public ImportExcelResultDto() {
    }

    public ImportExcelResultDto(String title, List<Student> students) {
        this.title = title;
        this.students = students;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
