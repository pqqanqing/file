package com.wjs.file.api.dto;

import com.wjs.common.base.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileSystemDTO extends BaseDTO {

    private String downloadPath;

    public FileSystemDTO() {
    }
}
