package com.sundeinfo.kd.zs.apis.restful.school.recruit;

import com.sundeinfo.foundation.mvc.controller.AbstractController;
import com.sundeinfo.foundation.request.GetCallback;
import com.sundeinfo.foundation.request.PostCallback;
import com.sundeinfo.foundation.request.RequestState;
import com.sundeinfo.foundation.request.ResponseCallback;
import com.sundeinfo.foundation.request.result.Result;
import com.sundeinfo.kd.zs.dao.model.SchoolRecruitSeason;
import com.sundeinfo.kd.zs.dto.apis.school.CurrSeasonDTO;
import com.sundeinfo.kd.zs.dto.apis.school.RecruitSeasonDTO;
import com.sundeinfo.kd.zs.service.school.SeasonService;
import com.sundeinfo.kd.zs.utility.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("kdzs/api/school/recruit")
public class SeasonController extends AbstractController<SeasonController> {
    @Autowired
    SeasonService seasonService;

    @GetMapping(value = "/currSeason")
    public ResponseEntity<Result<CurrSeasonDTO>> getSchoolAll() {
        return requestHandler.doPost(
                new ResponseCallback<CurrSeasonDTO>() {
                    CurrSeasonDTO currSeasonDTO = null;
                    @Override
                    public RequestState doCheck() {
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public RequestState invoke()  {
                        currSeasonDTO = seasonService.getCurrSeason();
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public CurrSeasonDTO response() {
                        return currSeasonDTO;
                    }
                });
    }
    @PostMapping(value = "/saveSeason")
    public ResponseEntity<Result> saveSeason(@RequestBody RecruitSeasonDTO schoolRecruitSeason) throws AuthenticationException {
        return requestHandler.doPost(
                new PostCallback() {
                    @Override
                    public RequestState doCheck() {
                        if(StringUtils.isBlank(schoolRecruitSeason.getRecruitYear()) ||
                                schoolRecruitSeason.getRecruitStartDate() == null ||
                                schoolRecruitSeason.getRecruitEndDate() == null){
                            return RequestState.PARAM_NULL;
                        }
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public RequestState invoke() {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        schoolRecruitSeason.setStartDate(sdf.format(schoolRecruitSeason.getRecruitStartDate()));
                        schoolRecruitSeason.setEndDate(sdf.format(schoolRecruitSeason.getRecruitEndDate()));
                        Long count = seasonService.seasonIsExist(schoolRecruitSeason);
                        if(count > 0){
                            return RequestState.PARAM_PERMISSION_EXIST_SEASON;
                        }

                        int flag = seasonService.saveSeason(schoolRecruitSeason);
                        if(flag > 0){
                            return RequestState.SUCCESS;
                        }else{
                            return RequestState.DB_INSERT_ERROR;
                        }
                    }
                }
        );
    }
    @PostMapping(value = "/updateSeason")
    public ResponseEntity<Result> updateSeason(@RequestBody RecruitSeasonDTO schoolRecruitSeason) throws AuthenticationException {
        return requestHandler.doPost(
                new PostCallback() {
                    @Override
                    public RequestState doCheck() {
                        if(StringUtils.isBlank(schoolRecruitSeason.getRecruitYear()) ||
                                schoolRecruitSeason.getRecruitStartDate() == null ||
                                schoolRecruitSeason.getRecruitEndDate() == null){
                            return RequestState.PARAM_NULL;
                        }
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public RequestState invoke() {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        schoolRecruitSeason.setStartDate(sdf.format(schoolRecruitSeason.getRecruitStartDate()));
                        schoolRecruitSeason.setEndDate(sdf.format(schoolRecruitSeason.getRecruitEndDate()));
                        Long count = seasonService.seasonIsExist(schoolRecruitSeason);
                        if(count > 0){
                            return RequestState.PARAM_PERMISSION_EXIST_SEASON;
                        }
                        int flag = seasonService.updateSeason(schoolRecruitSeason);
                        if(flag > 0){
                            return RequestState.SUCCESS;
                        }else{
                            return RequestState.DB_INSERT_ERROR;
                        }
                    }
                }
        );
    }
    @GetMapping(value = "/whetherOvertime")
    public ResponseEntity<Result> whetherOvertime(){
        return requestHandler.doGet(new GetCallback() {
            @Override
            public RequestState doCheck() {
                return RequestState.SUCCESS;
            }
            @Override
            public RequestState invoke() {
                seasonService.updateState();
                return RequestState.SUCCESS;
            }
        });
    }
    @GetMapping(value = "/querySeason")
    public ResponseEntity<Result<List<SchoolRecruitSeason>>> querySeason(){
        return requestHandler.doGet(new GetCallback<List<SchoolRecruitSeason>>() {
            @Override
            public RequestState doCheck() {
                return RequestState.SUCCESS;
            }
            @Override
            public List<SchoolRecruitSeason> invoke() {
                return seasonService.findAllSeason();
            }
        });
    }
    @GetMapping(value = "/querySeasonOne")
    public ResponseEntity<Result<SchoolRecruitSeason>> querySeasonOne(@RequestParam Integer id){
        return requestHandler.doGet(new GetCallback<SchoolRecruitSeason>() {
            @Override
            public RequestState doCheck() {
                return RequestState.SUCCESS;
            }
            @Override
            public SchoolRecruitSeason invoke() {
                return seasonService.selectById(id);
            }
        });
    }



}