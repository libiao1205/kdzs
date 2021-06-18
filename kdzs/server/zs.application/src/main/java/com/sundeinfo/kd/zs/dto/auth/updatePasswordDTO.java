package com.sundeinfo.kd.zs.dto.auth;

import lombok.Data;

@Data
public class updatePasswordDTO {
    private Integer userId;
    private String oldPassword;
    private String newPassword;
    private String rePassword;
}
