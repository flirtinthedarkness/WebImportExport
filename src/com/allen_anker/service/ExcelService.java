package com.allen_anker.service;

import com.allen_anker.dto.ImportExcelDto;
import com.allen_anker.dto.ImportExcelResultDto;
import com.allen_anker.entity.Student;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

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
}
