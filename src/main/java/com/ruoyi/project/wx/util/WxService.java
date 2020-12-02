package com.ruoyi.project.wx.util;

import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.framework.config.WxMpConfig;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxService {

    @Autowired
    private WxMpConfig wxMpConfig;

    private static WxMpService wxService;

    public WxMpService get() {
        if (ObjectUtil.isNotNull(wxService)) {
            return wxService;
        }


        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setAppId(wxMpConfig.getAppId()); // 设置微信公众号的appid
        config.setSecret(wxMpConfig.getSecret()); // 设置微信公众号的app corpSecret
        config.setToken(wxMpConfig.getToken()); // 设置微信公众号的token
        config.setAesKey(wxMpConfig.getAesKey()); // 设置微信公众号的EncodingAESKey

        wxService = new WxMpServiceImpl();// 实际项目中请注意要保持单例，不要在每次请求时构造实例，具体可以参考demo项目
        wxService.setWxMpConfigStorage(config);
        return wxService;
    }
}
