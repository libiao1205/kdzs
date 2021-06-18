package com.sundeinfo.kd.zs.apis.restful.school.recruit;

import com.sundeinfo.foundation.mvc.controller.AbstractController;
import com.sundeinfo.foundation.request.RequestState;
import com.sundeinfo.foundation.request.ResponseCallback;
import com.sundeinfo.foundation.request.result.Result;
import com.sundeinfo.kd.zs.dao.model.School;
import com.sundeinfo.kd.zs.define.PermissionCode;
import com.sundeinfo.kd.zs.dto.apis.school.SchoolDTO;
import com.sundeinfo.kd.zs.service.school.ImportExcelService;
import com.sundeinfo.kd.zs.service.school.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("kdzs/api/school/recruit")
public class SchoolController extends AbstractController<SchoolController> {

    @Autowired
    SchoolService schoolService;

    @Autowired
    ImportExcelService importExcelService;

    //@PreAuthorize(value = "hasAnyAuthority('" + PermissionCode.SCHOOL_RECRUIT_VIEW+ "','" + PermissionCode.SCHOOL_RECRUIT_VIEW + "')")
    @GetMapping(value="/schoolFindAll")
    public ResponseEntity<Result<List<SchoolDTO>>> getSchoolAll(){
        return requestHandler.doPost(
                new ResponseCallback<List<SchoolDTO>>(){
                    List<SchoolDTO> list = null;
                    @Override
                    public RequestState doCheck()  {
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public RequestState invoke() {
                        list = schoolService.findSchoolAll();
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public List<SchoolDTO> response() {
                        return list;
                    }
                }
        );
    }
    /**导入Excel招生信息*/
    @GetMapping(value="/importExcel")
    public ResponseEntity<Result> importExcel(@RequestParam String recruitDate,@RequestParam Integer shoot){
        return requestHandler.doPost(
                new ResponseCallback(){
                    @Override
                    public RequestState doCheck()  {
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public RequestState invoke() throws Exception {
                        importExcelService.importExcel(recruitDate,shoot);
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public RequestState response() {
                        return RequestState.SUCCESS;
                    }
                }
        );
    }

    @GetMapping(value="/schoolFindOne")
    public ResponseEntity<Result<SchoolDTO>> getSchoolOne(){
        return requestHandler.doPost(
                new ResponseCallback<SchoolDTO>(){
                    SchoolDTO schoolDTO = null;
                    @Override
                    public RequestState doCheck() {
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public RequestState invoke() throws Exception {
                        schoolDTO = schoolService.findCurrUserSchool();
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public SchoolDTO response() {
                        return schoolDTO;
                    }
                }
        );
    }

    @PreAuthorize(value = "hasAnyAuthority('" + PermissionCode.SCHOOL_RECRUIT_VIEW + "','" + PermissionCode.SCHOOL_RECRUIT_VIEW_ALL + "')")
    @GetMapping(value="/SchoolByNamePlan")
    public ResponseEntity<Result<List<SchoolDTO>>> getSchoolByName(@RequestParam Integer seasonId,@RequestParam String name){
        return requestHandler.doPost(
                new ResponseCallback<List<SchoolDTO>>(){
                    List<SchoolDTO> schoolDTOList = null;
                    @Override
                    public RequestState doCheck() throws Exception {
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public RequestState invoke() throws Exception {

                        schoolDTOList = schoolService.findSchoolByNamePlan(seasonId,name);
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public List<SchoolDTO> response() throws Exception {
                        return schoolDTOList;
                    }
                }
        );
    }

    /**
     * 根据学校名查询校正列表中的招生人数
     * */
    @PreAuthorize(value = "hasAnyAuthority('" + PermissionCode.SCHOOL_RECRUIT_VIEW + "','" + PermissionCode.SCHOOL_RECRUIT_VIEW_ALL + "')")
    @GetMapping(value="/SchoolByNameRevisePeople")
    public ResponseEntity<Result<List<SchoolDTO>>> getSchoolByRevisePeople(@RequestParam Integer seasonId,@RequestParam String name){
        return requestHandler.doPost(
                new ResponseCallback<List<SchoolDTO>>(){
                    List<SchoolDTO> schoolDTOList = null;
                    @Override
                    public RequestState doCheck() throws Exception {
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public RequestState invoke() throws Exception {

                        schoolDTOList = schoolService.findSchoolByNameRevisePeople(seasonId,name);
                        return RequestState.SUCCESS;
                    }
                    @Override
                    public List<SchoolDTO> response() throws Exception {
                        return schoolDTOList;
                    }
                }
        );
    }

}
