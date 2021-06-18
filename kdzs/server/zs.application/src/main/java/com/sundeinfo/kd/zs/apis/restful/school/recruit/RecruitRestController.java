package com.sundeinfo.kd.zs.apis.restful.school.recruit;

import com.sundeinfo.foundation.mvc.controller.AbstractController;
import com.sundeinfo.foundation.request.GetCallback;
import com.sundeinfo.foundation.request.PostCallback;
import com.sundeinfo.foundation.request.RequestState;
import com.sundeinfo.foundation.request.result.Result;
import com.sundeinfo.kd.zs.dao.model.SchoolRecruitCounting;
import com.sundeinfo.kd.zs.dao.model.SchoolRecruitCountingHist;
import com.sundeinfo.kd.zs.dao.model.SchoolRecruitSum;
import com.sundeinfo.kd.zs.define.PermissionCode;
import com.sundeinfo.kd.zs.dto.apis.school.RecruitCountingHistDTO;
import com.sundeinfo.kd.zs.dto.apis.school.requestDto.ReqRecruitCountingSearchDTO;
import com.sundeinfo.kd.zs.service.school.SchoolRecruitService;
import com.sundeinfo.kd.zs.service.school.SchoolRecruitSumService;
import com.sundeinfo.kd.zs.service.school.logService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("kdzs/api/school/recruit")
public class RecruitRestController extends AbstractController<RecruitRestController> {

    @Autowired
    private SchoolRecruitService RecruitService;

    @Autowired
    private logService logService;

    @Autowired
    SchoolRecruitSumService schoolRecruitSumService;

    @PreAuthorize(value = "hasAnyAuthority('" + PermissionCode.SCHOOL_RECRUIT_ADD+ "','" + PermissionCode.SCHOOL_RECRUIT_ADD + "')")
    @PostMapping(value = "/recruitStudent")
    public ResponseEntity<Result> saveRecruit(@RequestBody SchoolRecruitCounting record) throws AuthenticationException {
        return requestHandler.doPost(
                new PostCallback() {
                    @Override
                    public RequestState doCheck(){
                        if (record.getSchoolid() == null ||
                            record.getTechnicalpeople() == null ||
                            record.getUndergraduatepeople() == null ||
                            record.getGkpeople() == null) {
                            return RequestState.PARAM_NULL;
                        }
                          return RequestState.SUCCESS;
                    }
                    @Override
                    public RequestState invoke() {
                        schoolRecruitSumService.saveSchoolRecruitSum(record);
                        return RequestState.SUCCESS;
                    }
                }
        );
    }

    /**
     * 校正人数
     * */
    @PreAuthorize(value = "hasAnyAuthority('" + PermissionCode.SCHOOL_RECRUIT_VIEW_ALL+ "','" + PermissionCode.SCHOOL_RECRUIT_VIEW_ALL + "')")
    @PostMapping(value = "/updateRecruitSum")
    public ResponseEntity<Result<RequestState>> updateRecruitSum(@RequestBody SchoolRecruitSum schoolRecruitSumModel) throws AuthenticationException {
        return requestHandler.doGet(
                new GetCallback<RequestState>() {
                    @Override
                    public RequestState doCheck() {
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public RequestState invoke() {
                        int flag = schoolRecruitSumService.update(schoolRecruitSumModel);
                        if(flag > 0){
                            return RequestState.SUCCESS;
                        }else{
                            return RequestState.DB_UPDATE_ERROR;
                        }
                    }
                }
        );
    }

    @PreAuthorize(value = "hasAnyAuthority('" + PermissionCode.SCHOOL_RECRUIT_ADD+ "','" + PermissionCode.SCHOOL_RECRUIT_ADD + "')")
    @PostMapping(value = "/getRecruitStudentLogList")
    public ResponseEntity<Result<List<RecruitCountingHistDTO>>> getRecruitInfo(@RequestBody SchoolRecruitCountingHist history
                                                                               ) throws AuthenticationException {
        return requestHandler.doGet(
                new GetCallback< List<RecruitCountingHistDTO>>() {
                    @Override
                    public RequestState doCheck() {
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public List<RecruitCountingHistDTO> invoke() {
                        return  logService.queryHistory(history);
                    }
                }
        );
    }

    @PreAuthorize(value = "hasAnyAuthority('" + PermissionCode.SCHOOL_RECRUIT_ADD+ "','" + PermissionCode.SCHOOL_RECRUIT_ADD + "')")
    @PostMapping(value = "/getRecruitStudentList")
    public ResponseEntity<Result<List<SchoolRecruitCounting>>> getRecruitInfo(@RequestBody ReqRecruitCountingSearchDTO reqRecruitCountingSearchDTO) throws AuthenticationException {
        return requestHandler.doGet(
                new GetCallback<List<SchoolRecruitCounting>>() {
                    @Override
                    public RequestState doCheck() {
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public List<SchoolRecruitCounting> invoke() {
                        return RecruitService.queryRecruitCountingKey(reqRecruitCountingSearchDTO);
                    }
                }
        );
    }

}
