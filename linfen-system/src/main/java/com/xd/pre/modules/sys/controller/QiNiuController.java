package com.xd.pre.modules.sys.controller;

import cn.hutool.core.date.DateUtil;
import com.qiniu.storage.model.DefaultPutRet;
import cn.linfenw.pre.common.common.utils.R;
import com.xd.pre.modules.sys.util.QiniuUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/qiniu")
public class QiNiuController {

    @Autowired
    private QiniuUtil qiniuUtil;

    /**
     * 七牛云文件上传
     * @param file 文件
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public R upload(MultipartFile[] file) {
        if (file.length <= 0) {
            return R.error("上传文件不能为空");
        }
        if (file[0].isEmpty()) {
            return R.error("上传文件不能为空");
        }
        try {
            BufferedInputStream fileInputStream = (BufferedInputStream) file[0].getInputStream();
            String originalFilename = file[0].getOriginalFilename();
            String fileExtend = originalFilename.substring(originalFilename.lastIndexOf("."));
            String yyyyMMddHHmmss = DateUtil.format(new Date(), "yyyyMMddHHmmss");
            //默认不指定key的情况下，以文件内容的hash值作为文件名
            String fileKey = UUID.randomUUID().toString().replace("-", "")+"-"+yyyyMMddHHmmss + fileExtend;
            Map<String, Object> map = new HashMap<>();
            DefaultPutRet uploadInfo = qiniuUtil.upload(fileInputStream, fileKey);
            map.put("fileName", uploadInfo.key);
            map.put("originName", originalFilename);
            map.put("size", file[0].getSize());
            return R.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("上传失败");
        }
    }

    /**
     * 七牛云文件下载
     * @param filename 文件名
     * @return
     */
    @RequestMapping("/file/{filename}")
    public void download(@PathVariable("filename") String filename, HttpServletResponse response) {
        if (filename.isEmpty()) {
            return;
        }

        try {
            String privateFile = qiniuUtil.getPrivateFile(filename);
            log.info("文件下载地址：" + privateFile);
            response.sendRedirect(privateFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
