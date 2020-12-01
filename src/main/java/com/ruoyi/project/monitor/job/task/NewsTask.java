package com.ruoyi.project.monitor.job.task;

import com.ruoyi.project.system.news.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("newsTask")
public class NewsTask {

    @Autowired
    private INewsService newsService;

    public void downloadNewsOfPush() {
        newsService.downloadNewsOfPush();
    }
}
