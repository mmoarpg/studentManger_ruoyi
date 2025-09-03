package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Post;

/**
 * 岗位信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-09-03
 */
public interface PostMapper 
{
    /**
     * 查询岗位信息
     * 
     * @param postId 岗位信息主键
     * @return 岗位信息
     */
    public Post selectPostByPostId(Long postId);

    /**
     * 查询岗位信息列表
     * 
     * @param post 岗位信息
     * @return 岗位信息集合
     */
    public List<Post> selectPostList(Post post);

    /**
     * 新增岗位信息
     * 
     * @param post 岗位信息
     * @return 结果
     */
    public int insertPost(Post post);

    /**
     * 修改岗位信息
     * 
     * @param post 岗位信息
     * @return 结果
     */
    public int updatePost(Post post);

    /**
     * 删除岗位信息
     * 
     * @param postId 岗位信息主键
     * @return 结果
     */
    public int deletePostByPostId(Long postId);

    /**
     * 批量删除岗位信息
     * 
     * @param postIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePostByPostIds(Long[] postIds);
}
