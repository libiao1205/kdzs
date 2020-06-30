package com.sundeinfo.kd.zs.apis.render.school.recruit;


import com.sundeinfo.kd.zs.define.PermissionCode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("kdzs/school/recruit")
public class RecruitController {

    @GetMapping(value = "index")
    public String getRecruitInfo() {
        return "/school/recruit/index";
    }

    @PreAuthorize(value = "hasAnyAuthority('" + PermissionCode.SCHOOL_RECRUIT_ADD + "')")
    @GetMapping(value = "input")
    public String recruitInput() {
        return "/school/recruit/input";
    }
}
