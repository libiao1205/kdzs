package com.sundeinfo.kd.zs.service.school;

import com.sundeinfo.foundation.mvc.service.AbstractService;
import com.sundeinfo.kd.zs.dao.SchoolRecruitCountingHistExtMapper;
import com.sundeinfo.kd.zs.dao.SchoolRecruitCountingHistMapper;
import com.sundeinfo.kd.zs.dao.model.SchoolRecruitCountingHist;
import com.sundeinfo.kd.zs.dto.apis.school.RecruitCountingHistDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class logService extends AbstractService<logService> {
    @Resource
    SchoolRecruitCountingHistMapper SchoolRecruitCountingHistMapper;
    @Resource
    SchoolRecruitCountingHistExtMapper schoolRecruitCountingHistExtMapper;
    public int saveRecruitStudentLog(SchoolRecruitCountingHist history){
        return SchoolRecruitCountingHistMapper.insertSelective(history);
    }

    public List<RecruitCountingHistDTO> queryHistory(SchoolRecruitCountingHist history){
        return schoolRecruitCountingHistExtMapper.selectHistory(history);
    }
}
