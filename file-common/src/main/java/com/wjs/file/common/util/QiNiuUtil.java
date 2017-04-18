package com.wjs.file.common.util;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.File;

import static com.wjs.common.base.properties.ProjectProperties.getProperty;

public class QiNiuUtil {
    private static UploadManager uploadManager;
    private static Auth auth;

    static {
        init();
    }

    private static void init() {
        auth = Auth.create(getProperty("qiniu.accesskey"), getProperty("qiniu.secretkey"));
        Zone z = Zone.zone0();
        Configuration c = new Configuration(z);
        uploadManager = new UploadManager(c);
    }

    public static Response upload(String bucketname, File file, String key) {
        return upload(file, key, auth.uploadToken(bucketname));
    }

    public static Response upload(File file, String key, String token) {
        try {
            return uploadManager.put(file, key, token);
        } catch (QiniuException e) {
            throw new RuntimeException("上传文件到七牛服务器异常:" + e.getMessage(), e);
        }
    }

    public static String download(String domain, String fileName) {
        String downloadRUL = auth.privateDownloadUrl(domain + "/" + fileName, 3600);
        return downloadRUL;
    }

}
