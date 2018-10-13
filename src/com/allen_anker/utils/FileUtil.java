package com.allen_anker.utils;

import org.apache.commons.fileupload.FileItem;

import java.io.File;

public class FileUtil {

    public static final String SAVE_PATH = "/Users/barryallen/Desktop/Development/IDEA/WebImportExport/upload/";

    public static String save(FileItem fileItem, String path) throws Exception {

        String fileName = System.currentTimeMillis() + "_" + fileItem.getFieldName() + ".xlsx";
        fileItem.write(new File(path + fileName));
        return fileName;
    }
}
