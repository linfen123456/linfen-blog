package com.xd.pre.modules.sys.service;

import cn.linfenw.pre.common.common.utils.R;

import java.io.InputStream;

public interface IQiNiuService {
    public R upload(InputStream fileInputStream,String fileKey);
}
