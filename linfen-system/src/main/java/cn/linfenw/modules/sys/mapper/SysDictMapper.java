package cn.linfenw.modules.sys.mapper;

import cn.linfenw.modules.sys.domain.SysDict;
import cn.linfenw.modules.sys.domain.SysDictItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author linfen
 * @since 2019-05-17
 */
public interface SysDictMapper extends BaseMapper<SysDict> {



    @Select("SELECT sdi.id,sdi.item_text,sdi.item_value FROM sys_dict AS sd LEFT JOIN sys_dict_item AS sdi ON sd.id = sdi.dict_id WHERE sd.dict_name=#{dictName}")
    List<SysDictItem> queryDictItemByDictName(String dictName);
}
