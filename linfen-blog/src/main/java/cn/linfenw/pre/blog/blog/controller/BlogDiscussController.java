package cn.linfenw.pre.blog.blog.controller;


import cn.linfenw.pre.blog.blog.domain.BlogDiscuss;
import cn.linfenw.pre.blog.blog.service.IBlogDiscussService;
import com.alibaba.fastjson.JSON;
import cn.linfenw.pre.common.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
@Slf4j
@RestController
@RequestMapping("/blog/discuss")
public class BlogDiscussController {

    @Autowired
    public IBlogDiscussService discussService;

    @GetMapping("/byArticleId")
    public R getBlogDiscussByArticleId(Integer articleId) {
        return R.ok(discussService.getRootBlogDiscussByArticleId(articleId));
    }

    /**
     * 保存评论
     *
     * @param blogDiscuss
     * @return
     */
    @PostMapping
//    @PreAuthorize("hasAuthority('blog:article:add')")
    public R insert(@RequestBody BlogDiscuss blogDiscuss) {
        log.info(JSON.toJSONString(blogDiscuss));
        return R.ok(discussService.saveBlogDiscuss(blogDiscuss));
    }

}

