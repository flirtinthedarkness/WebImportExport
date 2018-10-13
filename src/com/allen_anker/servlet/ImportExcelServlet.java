package com.allen_anker.servlet;

import com.allen_anker.dto.ImportExcelDto;
import com.allen_anker.dto.ImportExcelResultDto;
import com.allen_anker.dto.ParamDto;
import com.allen_anker.service.ExcelService;
import com.allen_anker.utils.RequestUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ImportExcelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (ServletFileUpload.isMultipartContent(request)) {
            ParamDto result = RequestUtil.parseParam(request);
            ImportExcelDto importExcelDto  = new ImportExcelDto();
            importExcelDto.setTitle(result.getParamMap().get("title"));
            importExcelDto.setExcel(result.getFileMap().get("excel"));
            ExcelService excelService = new ExcelService();
            ImportExcelResultDto resultDto = excelService.imp(importExcelDto);
            request.setAttribute("result", resultDto);
        } else {

        }
        request.getRequestDispatcher("/WEB-INF/jsp/importExcelResult.jsp").forward(request, response);
    }
}
