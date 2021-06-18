package com.sundeinfo.kd.zs.service.school;
import com.sundeinfo.foundation.mvc.service.AbstractService;
import com.sundeinfo.kd.zs.dao.SchoolRecruitPlanExtMapper;
import com.sundeinfo.kd.zs.dao.SchoolRecruitSeasonExtMapper;
import com.sundeinfo.kd.zs.dao.model.SchoolRecruitPlan;
import com.sundeinfo.kd.zs.dao.model.SchoolRecruitSeason;
import com.sundeinfo.kd.zs.utility.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class RecruitPlanService extends AbstractService<RecruitPlanService> {

    @Resource
    SchoolRecruitSeasonExtMapper schoolRecruitSeasonExtMapper;

    @Resource
    SchoolRecruitPlanExtMapper schoolRecruitPlanExtMapper;

    @Autowired
    SeasonService seasonService;

    /*修改本年度某个季节招生计划另一个季节的也要同步（招生计划一年只能有一个）*/
    @Transactional
    public int updateRecruitPlan(SchoolRecruitPlan schoolRecruitPlan){
        Integer userId = UserHelper.getCurrentUserId();
        Date currDate = new Date();
        schoolRecruitPlan.setCreateby(userId);
        schoolRecruitPlan.setCreatetime(currDate);
        schoolRecruitPlan.setUpdateby(userId);
        schoolRecruitPlan.setUpdatetime(currDate);
        try{
            schoolRecruitPlanExtMapper.updateByPrimaryKeySelectiveExt(schoolRecruitPlan);

            SchoolRecruitSeason recruitSeason = seasonService.selectById(schoolRecruitPlan.getSeasonid());
            if(recruitSeason.getRecruitseason()){
                schoolRecruitPlan.setSeasonid(schoolRecruitSeasonExtMapper.selectByOnRecruitYear(recruitSeason.getRecruityear(),0));
            }else{
                schoolRecruitPlan.setSeasonid(schoolRecruitSeasonExtMapper.selectByOnRecruitYear(recruitSeason.getRecruityear(),1));
            }
            schoolRecruitPlan.setId(null);
            schoolRecruitPlanExtMapper.updateByPrimaryKeySelectiveExt(schoolRecruitPlan);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            logger.info("修改招生计划异常");
            return 0;
        }
    }

}
