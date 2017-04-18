package com.wjs.file.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import static com.wjs.common.base.properties.ProjectProperties.getProperty;
import static com.wjs.common.base.util.CloseableUtil.close;

public class FileUtil {

    public static File inputStream2File(MultipartFile file) {
        try {
            return inputStream2File(file.getInputStream(), file.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static File inputStream2File(InputStream inputStream, String fileName) {
        File file = getFile(fileName);
        try (OutputStream outputStream = new FileOutputStream(file)) {
            int bytes = 0;
            byte[] buffer = new byte[8192];
            while ((bytes = inputStream.read(buffer, 0, 8192)) != -1) {
                outputStream.write(buffer, 0, bytes);
            }
        } catch (Exception e) {
            throw new RuntimeException("转换文件失败:" + e.getMessage(), e);
        } finally {
            close(inputStream);
        }
        return file;
    }

    private static File getFile(String fileName) {
        File file = new File(getProperty("qiniu.temp") + File.separator + fileName);
        if (file.exists()) file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("创建文件失败:" + e.getMessage(), e);
        }
        return file;
    }


}
