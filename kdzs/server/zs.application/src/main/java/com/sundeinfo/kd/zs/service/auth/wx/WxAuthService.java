package com.sundeinfo.kd.zs.service.auth.wx;

import com.alibaba.fastjson.JSON;
import com.sundeinfo.core.http.HttpService;
import com.sundeinfo.foundation.mvc.service.AbstractService;
import com.sundeinfo.kd.zs.define.enums.WeChatMpType;
import com.sundeinfo.kd.zs.dto.auth.wx.WxSessionInfoDTO;
import com.sundeinfo.kd.zs.utility.WxAESUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WxAuthService extends AbstractService<WxAuthService> {

    @Autowired
    private HttpService httpService;

    @Value("${wx.mp.jscode2session.api}")
    private String API_JSCODE2SESSION;
    // 招生数据填报小程序
    @Value("${wx.mp.appid.counting}")
    private String MP_APP_ID_COUNTING;
    @Value("${wx.mp.secret.counting}")
    private String MP_APP_SECRET_COUNTING;

    public WxSessionInfoDTO jscode2session(WeChatMpType weChatMpType, String code) {
        String apiUrl = API_JSCODE2SESSION;
        String appId = "";
        String secret = "";
        // 招生数据填报小程序
        if (WeChatMpType.COUNTING == weChatMpType) {
            appId = MP_APP_ID_COUNTING;
            secret = MP_APP_SECRET_COUNTING;
        } else {
            return null;
        }
        try {
            String param = String.format("?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code", appId, secret, code);
            String result = httpService.doGet(apiUrl + param);
            return JSON.parseObject(result, WxSessionInfoDTO.class);
        } catch (Throwable e) {
            return null;
        }
    }

    /**
     * 解密微信数据
     *
     * @param sessionKey
     * @param encryptedData
     * @param iv
     * @return
     */
    public String decryptData(String sessionKey, String encryptedData, String iv) {
        return WxAESUtils.wxDecrypt(encryptedData, sessionKey, iv);
    }

}
