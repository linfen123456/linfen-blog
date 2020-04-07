package cn.linfenw.modules.sys.service;

import cn.linfenw.common.utils.R;

import java.io.InputStream;

public interface IQiNiuService {
    public R upload(InputStream fileInputStream,String fileKey);
}
