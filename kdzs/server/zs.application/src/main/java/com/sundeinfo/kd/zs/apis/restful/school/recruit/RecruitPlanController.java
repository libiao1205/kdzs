package com.sundeinfo.kd.zs.apis.restful.school.recruit;

import com.sundeinfo.foundation.mvc.controller.AbstractController;
import com.sundeinfo.foundation.request.PostCallback;
import com.sundeinfo.foundation.request.RequestState;
import com.sundeinfo.foundation.request.result.Result;
import com.sundeinfo.kd.zs.dao.model.SchoolRecruitPlan;
import com.sundeinfo.kd.zs.define.PermissionCode;
import com.sundeinfo.kd.zs.service.school.RecruitPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("kdzs/api/school/recruit")
public class RecruitPlanController extends AbstractController<RecruitPlanController> {
    @Autowired
    RecruitPlanService recruitPlanService;

    @PreAuthorize(value = "hasAnyAuthority('" + PermissionCode.SCHOOL_RECRUIT_VIEW + "','" + PermissionCode.SCHOOL_RECRUIT_VIEW_ALL + "')")
    @PostMapping(value = "/updateRecruitPlan")
    public ResponseEntity<Result> updateRecruitPlan(@RequestBody SchoolRecruitPlan schoolRecruitPlan) throws AuthenticationException {
        return requestHandler.doPost(
                new PostCallback() {
                    @Override
                    public RequestState doCheck(){
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public RequestState invoke(){
                        int flag = recruitPlanService.updateRecruitPlan(schoolRecruitPlan);
                       if(flag>0){
                           return RequestState.SUCCESS;
                       }else{
                           return RequestState.DB_UPDATE_ERROR;
                       }
                    }
                }
        );
    }
}
