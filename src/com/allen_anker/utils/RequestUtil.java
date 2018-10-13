package com.allen_anker.utils;

import com.allen_anker.dto.ParamDto;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RequestUtil {

    public static ParamDto parseParam(HttpServletRequest request) {
        ParamDto result = new ParamDto();
        ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
        fileUpload.setHeaderEncoding("utf-8");
        try {
            List<FileItem> fileItemList = fileUpload.parseRequest(request);
            for (FileItem fileItem : fileItemList) {
                if (fileItem.isFormField()) {
                    result.getParamMap().put(fileItem.getFieldName(), fileItem.getString("utf-8"));
                } else {
                    result.getFileMap().put(fileItem.getFieldName(), fileItem);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
