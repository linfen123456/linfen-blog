package cn.linfenw.blog.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LinkDTO implements Serializable {

    private List<LinkDTO> links;

    private String type;

    private String sort;

    private String count;

}
