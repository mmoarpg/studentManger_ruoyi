package com.ruoyi.system.service;

import com.ruoyi.system.domain.HomeworkPublish;
import java.util.List;

public interface IHomeworkPublishService {
    HomeworkPublish selectHomeworkPublishById(Long publishId);
    List<HomeworkPublish> selectHomeworkPublishList(HomeworkPublish publish);
    int insertHomeworkPublish(HomeworkPublish publish);
    int updateHomeworkPublish(HomeworkPublish publish);
    int deleteHomeworkPublishById(Long publishId);
    int deleteHomeworkPublishByIds(Long[] publishIds);
}
