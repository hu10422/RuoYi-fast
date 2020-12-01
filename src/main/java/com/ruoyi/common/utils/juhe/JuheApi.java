package com.ruoyi.common.utils.juhe;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.http.HttpUtils;

public class JuheApi {

    public static String[] newsType = {"top", "shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing", "shishang"};

    /**
     * 获取新闻接口
     *
     * @param type
     * @return
     */
    public static JSONArray news(String type) {
        if (!ArrayUtil.contains(newsType, type)){
            return null;
        }

        String appKey = "daa5a31bec638f8b60746b36c0b285af";
        String url = "http://v.juhe.cn/toutiao/index";
        Dict dict = Dict.create().set("type", StrUtil.isNotBlank(type) ? type : "").set("key", appKey);
        String param = dict.keySet().stream().map(tmp -> StrUtil.concat(true, tmp, "=", dict.getStr(tmp)))
                .reduce((t1, t2) -> StrUtil.concat(true, t1, "&", t2)).get();

        String resultVal = HttpUtils.sendGet(url, param);
        try {
            JSONObject jsonObject = JSONObject.parseObject(resultVal);
            JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("data");
            return jsonArray;
        } catch (JSONException e) {
        }
        return null;
    }

    public static JSONArray news() {
        return news(newsType[0]);
    }

}
