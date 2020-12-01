package com.ruoyi.common.utils.juhe;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.utils.http.HttpUtils;

public class JuheApi {


    public static void toutiao(String type){

        String appKey = "daa5a31bec638f8b60746b36c0b285af";
        String url = "http://v.juhe.cn/toutiao/index";
        Dict dict = Dict.create().set("type", StrUtil.isNotBlank(type) ? type : "").set("key", appKey);
        String param = dict.keySet().stream().map(tmp -> StrUtil.concat(true, tmp, "=", dict.getStr(tmp)))
                .reduce((t1, t2) -> StrUtil.concat(true, t1, "&", t2)).get();

        String resultVal = HttpUtils.sendGet(url, param);

        System.out.println(resultVal);
    }

    public static void main(String[] args) {
        toutiao("");
    }

}
