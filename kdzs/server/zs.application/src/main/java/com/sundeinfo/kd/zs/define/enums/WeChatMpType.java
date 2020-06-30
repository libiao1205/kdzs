package com.sundeinfo.kd.zs.define.enums;

public enum WeChatMpType {
    COUNTING("COUNTING_MP","招生数据填报小程序");

    private final String code;

    private final String value;

    WeChatMpType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String code() {
        return this.code;
    }

    public String value() {
        return this.value;
    }

    public static WeChatMpType codeOf(String code) {
        switch (code) {
            case "COUNTING_MP":
                return COUNTING;
            default:
                return null;
        }
    }
}
