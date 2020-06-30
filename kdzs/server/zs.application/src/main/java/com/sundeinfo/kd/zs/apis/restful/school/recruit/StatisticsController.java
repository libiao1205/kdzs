package com.sundeinfo.kd.zs.apis.restful.school.recruit;
import com.sundeinfo.foundation.mvc.controller.AbstractController;
import com.sundeinfo.foundation.request.RequestState;
import com.sundeinfo.foundation.request.ResponseCallback;
import com.sundeinfo.foundation.request.result.Result;
import com.sundeinfo.kd.zs.dto.apis.school.RecruitSumStatisticsDTO;
import com.sundeinfo.kd.zs.dto.apis.school.RecruitSumStatisticsDetailDTO;
import com.sundeinfo.kd.zs.dto.apis.school.requestDto.ReqRecruitStatisticsDTO;
import com.sundeinfo.kd.zs.service.school.RecruitStatisticsService;
import com.sundeinfo.kd.zs.utility.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("kdzs/api/school/recruit")
public class StatisticsController extends AbstractController<StatisticsController> {
    @Autowired
    RecruitStatisticsService recruitStatisticsService;

    @PostMapping(value = "/statisticsHome")
    public ResponseEntity<Result<List<RecruitSumStatisticsDTO>>> getStatistics(@RequestBody ReqRecruitStatisticsDTO reqRecruitStatisticsDTO) {
        return requestHandler.doPost(
            new ResponseCallback<List<RecruitSumStatisticsDTO>>(){
                List<RecruitSumStatisticsDTO> recruitSumStatisticsDTOList = null;
                @Override
                public RequestState doCheck() {
                    return RequestState.SUCCESS;
                }
                @Override
                public RequestState invoke()  {
                    recruitSumStatisticsDTOList = recruitStatisticsService.recruitStatistics(reqRecruitStatisticsDTO);
                    return RequestState.SUCCESS;
                }
                @Override
                public List<RecruitSumStatisticsDTO> response(){
                    return recruitSumStatisticsDTOList;
                }
            }
        );
    }
    @PostMapping(value = "/statisticsHomeAnime")
    public ResponseEntity<Result<RecruitSumStatisticsDTO>> getStatisticsHomeAnime(@RequestBody ReqRecruitStatisticsDTO reqRecruitStatisticsDTO) {
        return requestHandler.doPost(
                new ResponseCallback<RecruitSumStatisticsDTO>(){
                    RecruitSumStatisticsDTO recruitSumStatisticsDTO = null;
                    @Override
                    public RequestState doCheck()  {
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public RequestState invoke()  {
                        recruitSumStatisticsDTO = recruitStatisticsService.recruitStatisticsHomeAnime(reqRecruitStatisticsDTO);
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public RecruitSumStatisticsDTO response() {
                        return recruitSumStatisticsDTO;
                    }
                }
        );
    }
    @PostMapping(value = "/statisticsDetail")
    public ResponseEntity<Result<List<RecruitSumStatisticsDetailDTO>>> getStatisticsDetail(@RequestBody ReqRecruitStatisticsDTO reqRecruitStatisticsDTO) {
        return requestHandler.doPost(
                new ResponseCallback<List<RecruitSumStatisticsDetailDTO>>() {
                    @Override
                    public RequestState doCheck() {
                        if(!StringUtils.hasText(reqRecruitStatisticsDTO.getOnRecruitYear()) || reqRecruitStatisticsDTO.getSeasonId() == null || reqRecruitStatisticsDTO.getSeasonId() == 0){
                            return RequestState.PARAM_NULL;
                        }
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public RequestState invoke()  {
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public List<RecruitSumStatisticsDetailDTO> response() throws Exception {
                        return recruitStatisticsService.recruitStatisticsDetail(reqRecruitStatisticsDTO);
                    }
                }
        );
    }
    @PostMapping(value = "/exportExcel")
    public ResponseEntity<Result<RequestState>> exportExcel(@RequestBody ReqRecruitStatisticsDTO reqRecruitStatisticsDTO, HttpServletResponse response) {
        return requestHandler.doPost(
                new ResponseCallback<RequestState>() {
                    @Override
                    public RequestState doCheck()  {
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public RequestState invoke() throws Exception {
                        int flag = recruitStatisticsService.doExcel(reqRecruitStatisticsDTO,response);
                        if(flag > 0){
                            return RequestState.PARAM_PERMISSION_EXPORT_EXCEL_SUCCESS_SEASON;
                        }else{
                            return RequestState.PARAM_PERMISSION_EXPORT_EXCEL_UNSUCCESSFUL_SEASON;
                        }
                    }
                    @Override
                    public RequestState response()  {
                        return RequestState.SUCCESS;
                    }
                }
        );
    }

    @GetMapping(value = "/getExcel")
    public void getExcel( HttpServletResponse response,
                          @RequestParam Integer seasonId,
                          @RequestParam String onRecruitYear,
                          @RequestParam String recruitYear,
                          @RequestParam Integer recruitSeason) throws IOException {
        ReqRecruitStatisticsDTO reqRecruitStatisticsDTO = new ReqRecruitStatisticsDTO();
        reqRecruitStatisticsDTO.setSchoolId(0);
        reqRecruitStatisticsDTO.setSort(0);
        if(seasonId > 0 && recruitSeason != null && StringUtils.hasText(onRecruitYear) && StringUtils.hasText(recruitYear)){
            reqRecruitStatisticsDTO.setSeasonId(seasonId);
            reqRecruitStatisticsDTO.setOnRecruitYear(onRecruitYear);
            reqRecruitStatisticsDTO.setRecruitYear(recruitYear);
            reqRecruitStatisticsDTO.setRecruitSeason(recruitSeason);
            recruitStatisticsService.doExcel(reqRecruitStatisticsDTO,response);
        }
    }
    @GetMapping(value = "/getAuthority")
    public ResponseEntity<Result<List<String>>> getStatisticsDetail() {
        return requestHandler.doPost(
                new ResponseCallback<List<String>>() {
                    List<String> authorityList = new ArrayList<>();
                    @Override
                    public RequestState doCheck() {
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public RequestState invoke()  {
                        authorityList = UserHelper.getCurrentUserPermissions();
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public List<String> response()  {
                        return authorityList;
                    }
                }
        );
    }
}
