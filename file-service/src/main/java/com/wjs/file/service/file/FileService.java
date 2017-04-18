package com.wjs.file.service.file;


import com.wjs.file.api.dto.FileSystemDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FileService {
    FileSystemDTO upload(Long moduleId,Long qiniuId, File file);

    List<FileSystemDTO> uploads(long moduleId, long qiniuId, MultipartFile[] files);
}
