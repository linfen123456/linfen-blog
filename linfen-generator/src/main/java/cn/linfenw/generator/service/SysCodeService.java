package cn.linfenw.generator.service;

import cn.linfenw.generator.domain.CodeGenConfig;
import cn.linfenw.generator.domain.SysColumnEntity;
import cn.linfenw.generator.domain.SysTableEntity;

import java.util.List;

/**
 * @Classname SysCodeService
 * @Description 代码生成服务类
 * @Author linfen
 * @Date 2019-08-02 14:21
 * @Version 1.0
 */
public interface SysCodeService {


    List<SysTableEntity> findTableList(String tableSchema);

    List<SysColumnEntity> findColumnList(String tableName, String tableSchema);

    /**
     * 代码生成
     * @param codeGenConfig
     * @return
     */
    boolean generatorCode(CodeGenConfig codeGenConfig);;
}
