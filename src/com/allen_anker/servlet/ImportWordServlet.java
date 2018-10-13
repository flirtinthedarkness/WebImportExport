package com.allen_anker.servlet;

import com.allen_anker.dto.ImportWordDto;
import com.allen_anker.dto.ImportWordResultDto;
import com.allen_anker.dto.ParamDto;
import com.allen_anker.service.WordService;
import com.allen_anker.utils.RequestUtil;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ImportWordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (ServletFileUpload.isMultipartContent(request)) {
            WordService service = new WordService();
            ParamDto paramDto = RequestUtil.parseParam(request);
            ImportWordDto dto = new ImportWordDto();
            dto.setTitle(paramDto.getParamMap().get("title"));
            dto.setWord(paramDto.getFileMap().get("word"));
            ImportWordResultDto resultDto = service.iml(dto);
            request.setAttribute("result", resultDto);
        } else {

        }
        request.getRequestDispatcher("/WEB-INF/jsp/importWordResult.jsp").forward(request, response);
    }
}
