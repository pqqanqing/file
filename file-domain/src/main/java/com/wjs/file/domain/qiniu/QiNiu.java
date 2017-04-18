package com.wjs.file.domain.qiniu;

import com.wjs.common.base.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QiNiu extends BaseEntity {
    // 存储空间名
    private String bucketName;
    // 空间绑定的域名
    private String domain;

    public QiNiu() {
    }
}
