package com.qf.utils;

public class FileUtils {

    public static String getNewFileName(String fileName) {

        String uuid = UUIDUtils.uuid().replace("-","");
        fileName = uuid + fileName.substring(fileName.lastIndexOf("."));
        return fileName;
    }
}