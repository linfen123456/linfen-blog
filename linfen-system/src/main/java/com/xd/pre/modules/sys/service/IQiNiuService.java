package com.xd.pre.modules.sys.service;

import com.xd.pre.common.utils.R;

import java.io.InputStream;

public interface IQiNiuService {
    public R upload(InputStream fileInputStream,String fileKey);
}
