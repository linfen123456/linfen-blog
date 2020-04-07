package cn.linfenw.modules.sys.service.impl;

import cn.linfenw.common.utils.R;
import cn.linfenw.modules.sys.service.IQiNiuService;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class QiNiuServiceImpl implements IQiNiuService {
    @Override
    public R upload(InputStream fileInputStream, String fileKey) {
        return null;
    }
}
