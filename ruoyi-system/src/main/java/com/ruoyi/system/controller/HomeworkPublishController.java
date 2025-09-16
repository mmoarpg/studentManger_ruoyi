package com.ruoyi.system.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.HomeworkPublish;
import com.ruoyi.system.service.IHomeworkPublishService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.ruoyi.common.utils.PageUtils.startPage;

@RestController
@RequestMapping("/system/publish")
public class HomeworkPublishController extends BaseController {

    @Resource
    private IHomeworkPublishService publishService;

    /** 查询发布列表 */
    @GetMapping("/list")
    public TableDataInfo list(HomeworkPublish publish) {
        startPage();
        List<HomeworkPublish> list = publishService.selectHomeworkPublishList(publish);
        return getDataTable(list);
    }

    /** 查询详情 */
    @GetMapping("/{publishId}")
    public AjaxResult getInfo(@PathVariable("publishId") Long publishId) {
        return AjaxResult.success(publishService.selectHomeworkPublishById(publishId));
    }

    /** 新增 */
    @PostMapping
    public AjaxResult add(@RequestBody HomeworkPublish publish) {
        return toAjax(publishService.insertHomeworkPublish(publish));
    }

    /** 修改 */
    @PutMapping
    public AjaxResult edit(@RequestBody HomeworkPublish publish) {
        return toAjax(publishService.updateHomeworkPublish(publish));
    }

    /** 删除（单个） */
    @DeleteMapping("/{publishId}")
    public AjaxResult remove(@PathVariable("publishId") Long publishId) {
        return toAjax(publishService.deleteHomeworkPublishById(publishId));
    }
}

