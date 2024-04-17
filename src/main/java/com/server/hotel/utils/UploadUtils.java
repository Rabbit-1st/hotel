package com.server.hotel.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class UploadUtils {
    private static final Logger logger = LoggerFactory.getLogger(UploadUtils.class);

    public static String getImagePath(ApplicationHome applicationHome) {
        return applicationHome.getDir().getParentFile()
                .getParentFile().getAbsolutePath() + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "images";
    }

    public static boolean saveFile(MultipartFile file, String filename, ApplicationHome applicationHome) {
        if (file.isEmpty()) {
            return false;
        }

        try {
            String path = getImagePath(applicationHome);
            logger.info("Saving file to path: {}", path);
            File files = new File(path, filename);
            File parentFile = files.getParentFile();
            if (!parentFile.exists()) {
                if (!parentFile.mkdirs()) {
                    logger.error("Failed to create directories: {}", parentFile.getAbsolutePath());
                    return false;
                }
            }
            file.transferTo(files);
            return true;
        } catch (IOException e) {
            logger.error("Error saving file: {}", e.getMessage(), e);
        }
        return false;
    }

    public static boolean removeFile(String filename, ApplicationHome applicationHome) {
        try {
            String path = getImagePath(applicationHome);
            File file = new File(path, filename);
            if (file.exists()) {
                if (file.delete()) {
                    logger.info("File {} deleted successfully", filename);
                    return true;
                } else {
                    logger.error("Failed to delete file: {}", filename);
                }
            }
        } catch (Exception e) {
            logger.error("Error removing file: {}", e.getMessage(), e);
        }
        return false;
    }
}
