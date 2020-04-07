package cn.linfenw.pre.blog.blog.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PigeonholeDTO implements Serializable {
    private static final long serialVersionUID=1L;

    public List<BlogArticleDTO> articles;

    public String month;

    public String year;

    public Integer count;
}
