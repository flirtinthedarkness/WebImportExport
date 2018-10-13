package com.allen_anker.service;

import com.allen_anker.dto.ImportWordDto;
import com.allen_anker.dto.ImportWordResultDto;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public HWPFDocument export03(Map<String, String> replacements) {

        HWPFDocument doc = null;
        try {
            doc = new HWPFDocument(new FileInputStream("/Users/barryallen/Desktop/Development/" +
                    "IDEA/WebImportExport/web/templates/template_03.doc"));
            Range range = doc.getRange();
            for (Map.Entry<String, String> entry : replacements.entrySet()) {
                range.replaceText(entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
            return null;
        }

        return doc;
    }

    public XWPFDocument export07(Map<String, String> replacements) {

        XWPFDocument docx = null;
        try {
            docx = new XWPFDocument(new FileInputStream("/Users/barryallen/Desktop/Development/" +
                    "IDEA/WebImportExport/web/templates/template_07.docx"));
            List<XWPFParagraph> paragraphList = docx.getParagraphs();
            for (XWPFParagraph paragraph : paragraphList) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    String text = run.getText(run.getTextPosition());
                    for (Map.Entry<String, String> entry : replacements.entrySet()) {
                        text.replace(entry.getKey(), entry.getValue());
                    }
                    run.setText(text, 0);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return docx;
    }
}
