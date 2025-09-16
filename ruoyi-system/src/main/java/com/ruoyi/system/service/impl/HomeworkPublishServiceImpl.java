package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.HomeworkPublish;
import com.ruoyi.system.mapper.HomeworkPublishMapper;
import com.ruoyi.system.service.IHomeworkPublishService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HomeworkPublishServiceImpl implements IHomeworkPublishService {

    @Resource
    private HomeworkPublishMapper publishMapper;

    @Override
    public HomeworkPublish selectHomeworkPublishById(Long publishId) {
        return publishMapper.selectHomeworkPublishById(publishId);
    }

    @Override
    public List<HomeworkPublish> selectHomeworkPublishList(HomeworkPublish publish) {
        return publishMapper.selectHomeworkPublishList(publish);
    }

    @Override
    public int insertHomeworkPublish(HomeworkPublish publish) {
        return publishMapper.insertHomeworkPublish(publish);
    }

    @Override
    public int updateHomeworkPublish(HomeworkPublish publish) {
        return publishMapper.updateHomeworkPublish(publish);
    }

    @Override
    public int deleteHomeworkPublishById(Long publishId) {
        return publishMapper.deleteHomeworkPublishById(publishId);
    }

    @Override
    public int deleteHomeworkPublishByIds(Long[] publishIds) {
        return publishMapper.deleteHomeworkPublishByIds(publishIds);
    }
}
