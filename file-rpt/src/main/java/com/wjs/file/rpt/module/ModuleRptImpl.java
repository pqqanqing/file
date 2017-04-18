package com.wjs.file.rpt.module;

import com.wjs.common.base.rpt.BaseRptImpl;
import com.wjs.file.domain.module.Module;
import com.wjs.file.domain.module.ModuleRpt;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class ModuleRptImpl extends BaseRptImpl<Module, Serializable> implements ModuleRpt {
}
