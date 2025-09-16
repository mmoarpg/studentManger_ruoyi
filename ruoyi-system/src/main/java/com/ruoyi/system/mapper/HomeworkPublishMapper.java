package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.HomeworkPublish;
import java.util.List;

public interface HomeworkPublishMapper {
    HomeworkPublish selectHomeworkPublishById(Long publishId);
    List<HomeworkPublish> selectHomeworkPublishList(HomeworkPublish publish);
    int insertHomeworkPublish(HomeworkPublish publish);
    int updateHomeworkPublish(HomeworkPublish publish);
    int deleteHomeworkPublishById(Long publishId);
    int deleteHomeworkPublishByIds(Long[] publishIds);
}
