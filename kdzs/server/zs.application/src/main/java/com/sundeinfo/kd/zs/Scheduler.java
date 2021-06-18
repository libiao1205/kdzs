package com.sundeinfo.kd.zs;

import com.sundeinfo.kd.zs.dao.SchoolRecruitSeasonMapper;
import com.sundeinfo.kd.zs.dao.model.SchoolRecruitSeason;
import com.sundeinfo.kd.zs.dto.apis.school.RecruitSeasonDTO;
import com.sundeinfo.kd.zs.service.school.SeasonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class Scheduler {

    Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    SeasonService seasonService;

    @Resource
    SchoolRecruitSeasonMapper schoolRecruitSeasonMapper;

    /**每天凌晨2点更新招生季是否结束*/
    @Scheduled(cron = "0 0 2 * * ?")
    public void updateSeasonStatus(){
        logger.info("---------------开始更新招生季招生状态--------------");
        List<SchoolRecruitSeason> schoolRecruitSeasonList = seasonService.findAllSeason();
        schoolRecruitSeasonList.forEach(dto ->{
            Date currDate = new Date();
            if(dto.getRecruitstartdate().before(currDate) &&  dto.getRecruitenddate().after(currDate)){
                dto.setStatus(false);
            }else{
                dto.setStatus(true);
            }
            schoolRecruitSeasonMapper.updateByPrimaryKeySelective(dto);
        });
        logger.info("---------------完成更新招生季招生状态--------------");
    }
}
