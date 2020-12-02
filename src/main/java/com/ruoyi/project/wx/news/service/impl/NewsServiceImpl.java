package com.ruoyi.project.wx.news.service.impl;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.juhe.JuheApi;
import com.ruoyi.project.wx.util.WxService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpMassTagMessage;
import me.chanjar.weixin.mp.bean.material.WxMpMaterial;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;
import me.chanjar.weixin.mp.bean.material.WxMpNewsArticle;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.wx.news.mapper.NewsMapper;
import com.ruoyi.project.wx.news.domain.News;
import com.ruoyi.project.wx.news.service.INewsService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2020-12-01
 */
@Slf4j
@Service
public class NewsServiceImpl implements INewsService {
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private WxService wxService;

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

    /**
     * 获取头条数据
     * 推送微信
     */
    @Override
    public void downloadNewsOfPush() {
        JSONArray news = JuheApi.news();
        Assert.notEmpty(news, "获取新闻数据失败");

        List<News> newsList = news.stream().map(t -> BeanUtil.copyProperties(t, News.class)).collect(Collectors.toList());
        newsList.forEach(t -> insertNews(t));


        WxMpMaterialNews wxMpMaterialNews = new WxMpMaterialNews();
        newsList.stream().limit(5).forEach(newsVo -> {
            try {
                //TODO 缺少素材管理
                String fileName = Arrays.stream(StrUtil.split(newsVo.getThumbnailPicS(), "/")).sorted(Comparator.reverseOrder()).findFirst().orElse(null);
                File file = FileUtil.file(FileUtil.getTmpDirPath() + fileName);
                HttpUtil.downloadFile(newsVo.getThumbnailPicS(), file);
                WxMpMaterialUploadResult wxMpMaterialUploadResult = wxService.get().getMaterialService().materialFileUpload(WxConsts.MassMsgType.IMAGE, new WxMpMaterial(fileName, file, null, null));
                FileUtil.del(file);

                WxMpNewsArticle article = new WxMpNewsArticle();
                article.setThumbMediaId(wxMpMaterialUploadResult.getMediaId());
                article.setThumbUrl(wxMpMaterialUploadResult.getUrl());
                article.setAuthor(newsVo.getAuthorName());
                article.setTitle(newsVo.getTitle());
                article.setContentSourceUrl(newsVo.getUrl());
                article.setContent(newsVo.getTitle());
                article.setUrl("URL");
                article.setDigest(newsVo.getTitle());
                article.setShowCoverPic(true);
                article.setUrl(newsVo.getUrl());
                article.setNeedOpenComment(true);
                article.setOnlyFansCanComment(true);

                wxMpMaterialNews.addArticle(article);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        try {
            WxMpMaterialUploadResult wxMpMaterialUploadResult = wxService.get().getMaterialService().materialNewsUpload(wxMpMaterialNews);

            WxMpMassTagMessage wxMpMassTagMessage = new WxMpMassTagMessage();
            wxMpMassTagMessage.setMsgType(WxConsts.MassMsgType.MPNEWS);
            wxMpMassTagMessage.setContent("测试");
            wxMpMassTagMessage.setMediaId(wxMpMaterialUploadResult.getMediaId());
            
            WxMpMassSendResult wxMpMassSendResult = wxService.get().getMassMessageService().massGroupMessageSend(wxMpMassTagMessage);
            log.info("群发图文消息：" + JSONUtils.toJSONString(wxMpMassSendResult));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        System.out.println(FileUtil.getTmpDirPath());
    }
}
