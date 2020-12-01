package com.ruoyi.project.system.news.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.juhe.JuheApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.news.mapper.NewsMapper;
import com.ruoyi.project.system.news.domain.News;
import com.ruoyi.project.system.news.service.INewsService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2020-12-01
 */
@Service
public class NewsServiceImpl implements INewsService {
    @Autowired
    private NewsMapper newsMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public News selectNewsById(Long id) {
        return newsMapper.selectNewsById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param news 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<News> selectNewsList(News news) {
        return newsMapper.selectNewsList(news);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param news 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertNews(News news) {
        news.setCreateTime(DateUtils.getNowDate());
        return newsMapper.insertNews(news);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param news 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateNews(News news) {
        news.setUpdateTime(DateUtils.getNowDate());
        return newsMapper.updateNews(news);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteNewsByIds(String ids) {
        return newsMapper.deleteNewsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteNewsById(Long id) {
        return newsMapper.deleteNewsById(id);
    }

    @Override
    public void downloadNewsOfPush() {
        JSONArray news = JuheApi.news();
        Assert.notEmpty(news, "获取新闻数据失败");

        List<News> newsList = news.stream().map(t -> cn.hutool.core.convert.Convert.convert(News.class, t)).collect(Collectors.toList());
        newsList.forEach(t -> insertNews(t));
    }
}
