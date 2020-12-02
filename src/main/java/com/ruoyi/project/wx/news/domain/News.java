package com.ruoyi.project.wx.news.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 t_news
 * 
 * @author ruoyi
 * @date 2020-12-01
 */
public class News extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String uniquekey;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String title;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date date;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String category;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String authorName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String url;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String thumbnailPicS;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String thumbnailPicS02;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String thumbnailPicS03;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUniquekey(String uniquekey)
    {
        this.uniquekey = uniquekey;
    }

    public String getUniquekey()
    {
        return uniquekey;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setDate(Date date)
    {
        this.date = date;
    }

    public Date getDate()
    {
        return date;
    }
    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCategory()
    {
        return category;
    }
    public void setAuthorName(String authorName)
    {
        this.authorName = authorName;
    }

    public String getAuthorName()
    {
        return authorName;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
    public void setThumbnailPicS(String thumbnailPicS)
    {
        this.thumbnailPicS = thumbnailPicS;
    }

    public String getThumbnailPicS()
    {
        return thumbnailPicS;
    }
    public void setThumbnailPicS02(String thumbnailPicS02)
    {
        this.thumbnailPicS02 = thumbnailPicS02;
    }

    public String getThumbnailPicS02()
    {
        return thumbnailPicS02;
    }
    public void setThumbnailPicS03(String thumbnailPicS03)
    {
        this.thumbnailPicS03 = thumbnailPicS03;
    }

    public String getThumbnailPicS03()
    {
        return thumbnailPicS03;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("uniquekey", getUniquekey())
            .append("title", getTitle())
            .append("date", getDate())
            .append("category", getCategory())
            .append("authorName", getAuthorName())
            .append("url", getUrl())
            .append("thumbnailPicS", getThumbnailPicS())
            .append("thumbnailPicS02", getThumbnailPicS02())
            .append("thumbnailPicS03", getThumbnailPicS03())
            .toString();
    }
}
