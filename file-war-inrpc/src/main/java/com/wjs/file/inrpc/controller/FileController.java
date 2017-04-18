package com.wjs.file.inrpc.controller;

import com.wjs.common.base.rpc.RpcResponse;
import com.wjs.file.api.dto.FileSystemDTO;
import com.wjs.file.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

import static com.wjs.file.common.util.FileUtil.inputStream2File;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/upload", method = POST)
    @ResponseBody
    @Deprecated
    public RpcResponse<FileSystemDTO> upload(MultipartFile file) {
        return new RpcResponse<>(fileService.upload(1L, 1L, inputStream2File(file)));
    }

    @RequestMapping(value = "/uploads", method = POST)
    @ResponseBody
    public RpcResponse<List<FileSystemDTO>> uploads(MultipartFile[] files) {
        return new RpcResponse<>(fileService.uploads(1L,1L, files));
    }

    @RequestMapping(value = "/uploads/{moduleId}/{qiniuId}", method = POST)
    @ResponseBody
    public RpcResponse<List<FileSystemDTO>> uploads(@PathVariable Long moduleId, @PathVariable Long qiniuId, MultipartFile[] files) {
        return new RpcResponse<>(fileService.uploads(moduleId, qiniuId, files));
    }

    @RequestMapping(value = "/upload/{moduleId}/{qiniuId}", method = POST)
    @ResponseBody
    public RpcResponse<FileSystemDTO> upload(@PathVariable Long moduleId, @PathVariable Long qiniuId, MultipartFile file) {
        return new RpcResponse<>(fileService.upload(moduleId, qiniuId, inputStream2File(file)));
    }
}
