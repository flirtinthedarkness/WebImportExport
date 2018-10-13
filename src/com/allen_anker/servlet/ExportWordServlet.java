package com.allen_anker.servlet;

import com.allen_anker.service.WordService;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExportWordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        WordService service = new WordService();
        Map<String, String> replacements = new HashMap<>();
        replacements.put("${name}", request.getParameter("name"));
        replacements.put("${age}", request.getParameter("age"));
        replacements.put("${time}", request.getParameter("time"));
        ServletOutputStream outputStream = response.getOutputStream();

        if (request.getParameter("isDocx") != null && !"".equals(request.getParameter("isDocx"))) {
            XWPFDocument docx = service.export07(replacements);
            System.out.println("Invoked 07");
            response.setHeader("Content-Disposition", "attachment;filename=export.docx");
            docx.write(outputStream);
            docx.close();
        } else {
            HWPFDocument doc = service.export03(replacements);
            System.out.println("Invoked 03");
            response.setHeader("Content-Disposition", "attachment;filename=export.doc");
            doc.write(outputStream);
            doc.close();
        }
        outputStream.flush();
        outputStream.close();
    }
}
