package com.sundeinfo.kd.zs.service.school;

import com.sundeinfo.foundation.mvc.service.AbstractService;
import com.sundeinfo.kd.zs.dao.SchoolRecruitCountingExtMapper;
import com.sundeinfo.kd.zs.dao.SchoolRecruitCountingMapper;
import com.sundeinfo.kd.zs.dao.model.SchoolRecruitCounting;
import com.sundeinfo.kd.zs.dao.model.SchoolRecruitCountingKey;
import com.sundeinfo.kd.zs.dto.apis.school.requestDto.ReqRecruitCountingSearchDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SchoolRecruitService extends AbstractService<SchoolRecruitService>{

    @Resource
    private SchoolRecruitCountingMapper schoolRecruitCountingMapper;

    @Resource
    SchoolRecruitCountingExtMapper  schoolRecruitCountingExtMapper;

    public SchoolRecruitCounting selectByPrimaryKey(SchoolRecruitCountingKey key){
        return schoolRecruitCountingMapper.selectByPrimaryKey(key);
    }

    public List<SchoolRecruitCounting> queryRecruitCountingKey(ReqRecruitCountingSearchDTO reqRecruitCountingSearchDTO){
        return schoolRecruitCountingExtMapper.queryByPrimaryKey(reqRecruitCountingSearchDTO);
    }

}
