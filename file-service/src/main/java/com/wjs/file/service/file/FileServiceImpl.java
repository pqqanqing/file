package com.wjs.file.service.file;

import com.wjs.file.api.dto.FileSystemDTO;
import com.wjs.file.domain.filesystem.FileSystem;
import com.wjs.file.domain.filesystem.FileSystemRpt;
import com.wjs.file.domain.module.Module;
import com.wjs.file.domain.module.ModuleRpt;
import com.wjs.file.domain.qiniu.QiNiu;
import com.wjs.file.domain.qiniu.QiNiuRpt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.wjs.file.common.util.FileUtil.inputStream2File;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private ModuleRpt moduleRpt;
    @Autowired
    private QiNiuRpt qiNiuRpt;
    @Autowired
    private FileSystemRpt fileSystemRpt;

    @Override
    @Transactional
    public FileSystemDTO upload(Long moduleId, Long qiniuId, File file) {
        Module module = moduleRpt.getById(moduleId);
        QiNiu qiniu = qiNiuRpt.getById(qiniuId);
        FileSystem fileSystem = new FileSystem(module, qiniu, file);
        fileSystem.upload();
        fileSystemRpt.save(fileSystem);
        return fileSystem.makeDTO();
    }

    @Override
    @Transactional
    public List<FileSystemDTO> uploads(long moduleId, long qiniuId, MultipartFile[] files) {
        List<FileSystemDTO> fileSystemDTOS = new ArrayList<>();
        Module module = moduleRpt.getById(moduleId);
        QiNiu qiniu = qiNiuRpt.getById(qiniuId);
        for (MultipartFile file : files) {
            FileSystem fileSystem = new FileSystem(module, qiniu, inputStream2File(file));
            fileSystem.upload();
            fileSystemRpt.save(fileSystem);
            fileSystemDTOS.add(fileSystem.makeDTO());
        }
        return fileSystemDTOS;
    }
}
