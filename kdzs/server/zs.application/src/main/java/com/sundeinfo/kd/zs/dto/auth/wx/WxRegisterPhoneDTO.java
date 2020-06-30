package com.sundeinfo.kd.zs.dto.auth.wx;

import com.sundeinfo.kd.zs.define.enums.WeChatMpType;
import lombok.Data;

@Data
public class WxRegisterPhoneDTO {
    private String wxCode;
    private String iv;
    private String encryptedData;
    private WeChatMpType mpType;
}
