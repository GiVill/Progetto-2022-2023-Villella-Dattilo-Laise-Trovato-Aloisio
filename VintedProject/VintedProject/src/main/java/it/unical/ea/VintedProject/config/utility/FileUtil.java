package it.unical.ea.VintedProject.config.utility;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class FileUtil {
    private static final AtomicLong counter = new AtomicLong();

    public static String assignProgressiveName(MultipartFile file) {

        if (file.getOriginalFilename() != null) {
            String originalFileName = file.getOriginalFilename();
            String fileExtension = getFileExtension(originalFileName);
            UUID uid = UUID.randomUUID();
            return "file_" + uid.toString() + "." + fileExtension;
        }
        return "Errore";
    }

    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            return "";
        }
        return fileName.substring(dotIndex + 1);
    }

}