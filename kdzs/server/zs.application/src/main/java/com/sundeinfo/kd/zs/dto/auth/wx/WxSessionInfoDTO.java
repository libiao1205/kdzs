package com.sundeinfo.kd.zs.dto.auth.wx;

import lombok.Data;

@Data
public class WxSessionInfoDTO {
    private String openid;
    private String session_key;
    private String unionid;
    private String errcode;
    private String errmsg;
}
