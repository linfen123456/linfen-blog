package cn.linfenw.log.service.Impl;

import cn.hutool.core.util.StrUtil;
import cn.linfenw.log.domain.SysLog;
import cn.linfenw.log.mapper.SysLogMapper;
import cn.linfenw.log.service.ISysLogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author linfen
 * @since 2019-04-27
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

    @Override
    public IPage<SysLog> selectLogList(Integer page, Integer pageSize, Integer type, String userName) {
        Page<SysLog> logPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<SysLog> sysLogLambdaQueryWrapper = Wrappers.<SysLog>lambdaQuery().eq(SysLog::getType, type).orderByDesc(SysLog::getStartTime);
        if (StrUtil.isNotEmpty(userName)) {
            sysLogLambdaQueryWrapper.like(SysLog::getUserName, userName);
        }
        return baseMapper.selectPage(logPage, sysLogLambdaQueryWrapper);
    }
}
