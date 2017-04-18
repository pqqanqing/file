package com.wjs.file.rpt.qiniu;

import com.wjs.common.base.rpt.BaseRptImpl;
import com.wjs.file.domain.qiniu.QiNiu;
import com.wjs.file.domain.qiniu.QiNiuRpt;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class QiNiuRptImpl extends BaseRptImpl<QiNiu, Serializable> implements QiNiuRpt {
}
