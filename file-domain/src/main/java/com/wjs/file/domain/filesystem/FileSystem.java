package com.wjs.file.domain.filesystem;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.wjs.common.base.base.BaseEntity;
import com.wjs.common.base.execption.BusinessExecption;
import com.wjs.file.api.dto.FileSystemDTO;
import com.wjs.file.common.util.QiNiuUtil;
import com.wjs.file.domain.module.Module;
import com.wjs.file.domain.qiniu.QiNiu;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

import static com.wjs.common.base.util.BeanPropertiesUtil.copyProperties;
import static com.wjs.file.common.util.QiNiuUtil.download;
import static java.lang.System.currentTimeMillis;
import static java.util.Objects.isNull;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

@Setter
@Getter
public class FileSystem extends BaseEntity {
    private Module module;
    private QiNiu qiniu;
    private File file;
    private String fileName;
    private String fileName4Sha256;
    private String contentType;
    private String address;
    private int statusCode;
    private String bodyString;
    private String downloadPath;

    public FileSystem() {
    }

    public FileSystem(Module module, QiNiu qiniu, File file) {
        this.module = module;
        this.qiniu = qiniu;
        this.file = file;
        this.file = null;
        this.fileName4Sha256 = generateFileName4Sha256(fileName);
    }

    private String generateFileName4Sha256(String fileName) {
        int pointIndex = fileName.lastIndexOf(".");
        String name = fileName.substring(0, pointIndex);
        String fileName4Sha256 = sha256Hex(name + currentTimeMillis()) + fileName.substring(pointIndex);
        return fileName4Sha256;
    }

    public void upload() {
        checkQiniu();
        Response response = QiNiuUtil.upload(qiniu.getBucketName(), file, fileName4Sha256);
        this.contentType = response.contentType();
        this.statusCode = response.statusCode;
        this.address = response.address;
        this.downloadPath = download(qiniu.getDomain(), fileName4Sha256);
        try {
            this.bodyString = response.bodyString();
        } catch (QiniuException e) {
            throw new BusinessExecption("file1");
        }
    }

    private void checkQiniu() {
        if (isNull(qiniu)) throw new BusinessExecption("file2");
        if (isNull(qiniu.getBucketName())) throw new BusinessExecption("file3");
        if (isNull(qiniu.getDomain())) throw new BusinessExecption("file4");
    }

    public FileSystemDTO makeDTO() {
        return copyProperties(this, FileSystemDTO.class);
    }
}
