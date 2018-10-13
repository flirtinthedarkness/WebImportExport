package com.allen_anker.service;

import com.allen_anker.dto.ImportExcelDto;
import com.allen_anker.dto.ImportExcelResultDto;
import com.allen_anker.entity.Student;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelService {

    public ImportExcelResultDto imp(ImportExcelDto importExcelDto) {
        ImportExcelResultDto importExcelResultDto = new ImportExcelResultDto();
        importExcelResultDto.setTitle(importExcelDto.getTitle());
        List<Student> students = new ArrayList<>();

        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(importExcelDto.getExcel().getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Student student = new Student();
                student.setName(row.getCell(0).getStringCellValue());
                student.setAge((int) row.getCell(1).getNumericCellValue());
                student.setTime(row.getCell(2).getDateCellValue());
                students.add(student);
            }
        } catch (Exception e) {
            importExcelResultDto.setMessage("Parse Excel File Failed.");
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        importExcelResultDto.setStudents(students);

        return importExcelResultDto;
    }

    public Workbook export(boolean isXlsx) {
        Workbook workbook;
        if (isXlsx) {
            workbook = new XSSFWorkbook();
        } else {
            workbook = new HSSFWorkbook();
        }
        Sheet sheet = workbook.createSheet("My Sheet 1");
        List<List<String>> content = getContent();
        for (int i = 0; i < content.size(); i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < content.get(i).size(); j++) {
                row.createCell(j).setCellValue(content.get(i).get(j));
            }
        }

        return workbook;
    }

    private List<List<String>> getContent() {
        List<List<String>> result = new ArrayList<>();
        List<String> row = new ArrayList<>();
        result.add(row);
        row.add("No");
        row.add("Name");
        row.add("Age");
        row.add("Date");

        row = new ArrayList<>();
        result.add(row);
        row.add("1");
        row.add("A");
        row.add("18");
        row.add("2010-01-01");

        row = new ArrayList<>();
        result.add(row);
        row.add("2");
        row.add("B");
        row.add("19");
        row.add("2010-01-02");

        row = new ArrayList<>();
        result.add(row);
        row.add("3");
        row.add("C");
        row.add("20");
        row.add("2010-01-03");
        return result;
    }
}
