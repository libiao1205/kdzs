package com.sundeinfo.kd.zs.dto.auth.wx;

import com.sundeinfo.kd.zs.define.enums.WeChatMpType;
import lombok.Data;

@Data
public class WxLoginDTO {
    private String wxCode;
    private String unionId;
    private WeChatMpType mpType;
}
