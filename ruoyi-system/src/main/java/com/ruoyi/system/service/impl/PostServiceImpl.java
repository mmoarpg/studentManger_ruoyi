package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.PostMapper;
import com.ruoyi.system.domain.Post;
import com.ruoyi.system.service.IPostService;

/**
 * 岗位信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-03
 */
@Service
public class PostServiceImpl implements IPostService 
{
    @Autowired
    private PostMapper postMapper;

    /**
     * 查询岗位信息
     * 
     * @param postId 岗位信息主键
     * @return 岗位信息
     */
    @Override
    public Post selectPostByPostId(Long postId)
    {
        return postMapper.selectPostByPostId(postId);
    }

    /**
     * 查询岗位信息列表
     * 
     * @param post 岗位信息
     * @return 岗位信息
     */
    @Override
    public List<Post> selectPostList(Post post)
    {
        return postMapper.selectPostList(post);
    }

    /**
     * 新增岗位信息
     * 
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public int insertPost(Post post)
    {
        post.setCreateTime(DateUtils.getNowDate());
        return postMapper.insertPost(post);
    }

    /**
     * 修改岗位信息
     * 
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public int updatePost(Post post)
    {
        post.setUpdateTime(DateUtils.getNowDate());
        return postMapper.updatePost(post);
    }

    /**
     * 批量删除岗位信息
     * 
     * @param postIds 需要删除的岗位信息主键
     * @return 结果
     */
    @Override
    public int deletePostByPostIds(Long[] postIds)
    {
        return postMapper.deletePostByPostIds(postIds);
    }

    /**
     * 删除岗位信息信息
     * 
     * @param postId 岗位信息主键
     * @return 结果
     */
    @Override
    public int deletePostByPostId(Long postId)
    {
        return postMapper.deletePostByPostId(postId);
    }
}
