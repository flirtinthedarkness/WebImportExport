package com.allen_anker.service;

import com.allen_anker.dto.ImportWordDto;
import com.allen_anker.dto.ImportWordResultDto;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.IOException;
import java.util.List;

public class WordService {

    public ImportWordResultDto iml(ImportWordDto dto) {
        ImportWordResultDto result = new ImportWordResultDto();
        result.setTitle(dto.getTitle());
        HWPFDocument doc = null;
        boolean isDocx = false;
        try {
            doc = new HWPFDocument(dto.getWord().getInputStream());
            result.setContent(doc.getDocumentText().replace("\r", "<br>"));
        } catch (IllegalArgumentException e) {
            System.out.println("This might be a 2007 word file.");
            isDocx = true;
        } catch (Exception e) {
            result.setMessage("The file format is not right.");
            return result;
        } finally {
            if (doc != null) {
                try {
                    doc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (isDocx) {
            XWPFDocument docx = null;
            try {
                docx = new XWPFDocument(dto.getWord().getInputStream());
                List<XWPFParagraph> paragraphs = docx.getParagraphs();
                StringBuilder content = new StringBuilder();
                for (int i = 0; i < paragraphs.size(); i++) {
                    if (i != 0) content.append("<br>");
                    content.append(paragraphs.get(i).getText());
                }
                result.setContent(content.toString());
            } catch (Exception e) {
                result.setMessage("The file format is not right.");
                return result;
            } finally {
                if (docx != null) {
                    try {
                        docx.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }
}
