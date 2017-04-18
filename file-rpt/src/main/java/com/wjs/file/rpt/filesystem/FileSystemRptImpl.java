package com.wjs.file.rpt.filesystem;

import com.wjs.common.base.rpt.BaseRptImpl;
import com.wjs.file.domain.filesystem.FileSystem;
import com.wjs.file.domain.filesystem.FileSystemRpt;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class FileSystemRptImpl extends BaseRptImpl<FileSystem, Serializable> implements FileSystemRpt {
}
