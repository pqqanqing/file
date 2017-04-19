package com.wjs.file.inrpc.controller;

import com.wjs.common.base.rpc.RpcResponse;
import com.wjs.file.api.dto.FileSystemDTO;
import com.wjs.file.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin
@Controller
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/uploads", method = POST)
    @ResponseBody
    public RpcResponse<List<FileSystemDTO>> uploads(MultipartFile[] files) {
        return new RpcResponse<>(fileService.uploads(1L, 1L, files));
    }
}
