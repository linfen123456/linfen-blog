package com.xd.pre.blog.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.blog.domain.BlogDownload;
import com.xd.pre.blog.service.IBlogDownloadService;
import com.xd.pre.common.utils.R;
import com.xd.pre.log.annotation.SysOperaLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 文章下载 前端控制器
 * </p>
 *
 * @author linfen
 * @since 2020-03-13
 */
@RestController
@RequestMapping("/blog/download")
public class BlogDownloadController {

    @Autowired
    private IBlogDownloadService blogDownloadService;

    /**
     * 保存下载文件
     *
     * @param blogDownload
     * @return
     */
    @SysOperaLog(descrption = "保存下载文件")
    @PostMapping
    @PreAuthorize("hasAuthority('blog:download:add')")
    public R insert(@RequestBody BlogDownload blogDownload) {
        return R.ok(blogDownloadService.save(blogDownload));
    }

    /**
     * 查询下载文件集合
     *
     * @param page
     * @param blogDownload
     * @return
     */
    @GetMapping
//    @PreAuthorize("hasAuthority('blog:download:view')")
    public R getList(Page page, BlogDownload blogDownload) {
        return R.ok(blogDownloadService.page(page, Wrappers.query(blogDownload).lambda().orderByDesc(BlogDownload::getCreateTime)));
    }

    /**
     * 查询所有分类集合
     *
     * @param page
     * @param blogDownload
     * @return
     */
    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('blog:category:view')")
    public R getAllList(Page page, BlogDownload blogDownload) {
        return R.ok(blogDownloadService.list(Wrappers.query(blogDownload)));
    }

    /**
     * 更新下载文件
     *
     * @param blogDownload
     * @return
     */
    @SysOperaLog(descrption = "更新下载文件")
    @PutMapping
    @PreAuthorize("hasAuthority('blog:download:update')")
    public R update(@RequestBody BlogDownload blogDownload) {
        return R.ok(blogDownloadService.updateById(blogDownload));
    }

    /**
     * 根据id删除下载文件
     *
     * @param id
     * @return
     */
    @SysOperaLog(descrption = "根据id删除下载文件")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('blog:download:delete')")
    public R delete(@PathVariable("id") Integer id) {
        return R.ok(blogDownloadService.removeById(id));
    }


}

