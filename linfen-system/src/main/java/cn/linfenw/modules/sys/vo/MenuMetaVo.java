package cn.linfenw.modules.sys.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Classname MenuMetaVo
 * @Description
 * @Author linfen
 * @Date 2019-05-05 16:39
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class MenuMetaVo implements Serializable {

    private String title;
    private String icon;
}
