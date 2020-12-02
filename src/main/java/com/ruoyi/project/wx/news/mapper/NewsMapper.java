package com.ruoyi.project.wx.news.mapper;

import java.util.List;

import com.ruoyi.project.wx.news.domain.News;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2020-12-01
 */
public interface NewsMapper {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    News selectNewsById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param news 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    List<News> selectNewsList(News news);

    /**
     * 新增【请填写功能名称】
     *
     * @param news 【请填写功能名称】
     * @return 结果
     */
    int insertNews(News news);

    /**
     * 修改【请填写功能名称】
     *
     * @param news 【请填写功能名称】
     * @return 结果
     */
    int updateNews(News news);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    int deleteNewsById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteNewsByIds(String[] ids);
}
