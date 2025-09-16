package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Post;
import com.ruoyi.system.service.IPostService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 岗位信息Controller
 * 
 * @author ruoyi
 * @date 2025-09-03
 */
@RestController
@RequestMapping("/system/teacherPost")
public class TeacherPostController extends BaseController
{
    @Autowired
    private IPostService postService;

    /**
     * 查询岗位信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:post:list')")
    @GetMapping("/list")
    public TableDataInfo list(Post post)
    {
        startPage();
        List<Post> list = postService.selectPostList(post);
        return getDataTable(list);
    }

    /**
     * 导出岗位信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:post:export')")
    @Log(title = "岗位信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Post post)
    {
        List<Post> list = postService.selectPostList(post);
        ExcelUtil<Post> util = new ExcelUtil<Post>(Post.class);
        util.exportExcel(response, list, "岗位信息数据");
    }

    /**
     * 获取岗位信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:post:query')")
    @GetMapping(value = "/{postId}")
    public AjaxResult getInfo(@PathVariable("postId") Long postId)
    {
        return success(postService.selectPostByPostId(postId));
    }

    /**
     * 新增岗位信息
     */
    @PreAuthorize("@ss.hasPermi('system:post:add')")
    @Log(title = "岗位信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Post post)
    {
        return toAjax(postService.insertPost(post));
    }

    /**
     * 修改岗位信息
     */
    @PreAuthorize("@ss.hasPermi('system:post:edit')")
    @Log(title = "岗位信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Post post)
    {
        return toAjax(postService.updatePost(post));
    }

    /**
     * 删除岗位信息
     */
    @PreAuthorize("@ss.hasPermi('system:post:remove')")
    @Log(title = "岗位信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{postIds}")
    public AjaxResult remove(@PathVariable Long[] postIds)
    {
        return toAjax(postService.deletePostByPostIds(postIds));
    }
}
