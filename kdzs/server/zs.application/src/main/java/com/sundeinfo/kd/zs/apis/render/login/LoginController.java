package com.sundeinfo.kd.zs.apis.render.login;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("kdzs/login")
public class LoginController {

    @GetMapping(value = "")
    public String getRecruitInfo(HttpServletRequest request) {
        return "/login";
    }

}
