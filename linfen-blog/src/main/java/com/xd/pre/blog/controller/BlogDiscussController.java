package com.xd.pre.blog.controller;


import com.alibaba.fastjson.JSON;
import com.xd.pre.blog.domain.BlogDiscuss;
import com.xd.pre.blog.service.IBlogDiscussService;
import com.xd.pre.blog.vo.BlogArticleVo;
import com.xd.pre.common.utils.R;
import com.xd.pre.log.annotation.SysOperaLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

