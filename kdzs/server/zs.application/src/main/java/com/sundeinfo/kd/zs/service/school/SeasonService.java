package com.sundeinfo.kd.zs.service.school;

import com.sundeinfo.foundation.mvc.service.AbstractService;
import com.sundeinfo.kd.zs.dao.*;
import com.sundeinfo.kd.zs.dao.model.*;
import com.sundeinfo.kd.zs.dto.apis.school.CurrSeasonDTO;
import com.sundeinfo.kd.zs.dto.apis.school.RecruitSeasonDTO;
import com.sundeinfo.kd.zs.dto.apis.school.SchoolDTO;
import com.sundeinfo.kd.zs.utility.DateUtils;
import com.sundeinfo.kd.zs.utility.UserHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SeasonService extends AbstractService<SeasonService> {
    @Resource
    SchoolRecruitSeasonExtMapper schoolRecruitSeasonExtMapper;

    @Resource
    SchoolRecruitSeasonMapper schoolRecruitSeasonMapper;

    @Resource
    SchoolRecruitPlanMapper schoolRecruitPlanMapper;

    @Resource
    SchoolExtMapper schoolExtMapper;

    public CurrSeasonDTO getCurrSeason(){
        CurrSeasonDTO currSeasonDTO = schoolRecruitSeasonExtMapper.selectByCurrSeason();
        if(currSeasonDTO != null){
            String OnRecruitYear = this.subRecruitYear(currSeasonDTO);
            currSeasonDTO.setOnRecruitYear(OnRecruitYear);
        }
        return currSeasonDTO;
    }
    private String subRecruitYear(CurrSeasonDTO currSeasonDTO){
        SimpleDateFormat simpleDateFormatFormat = new SimpleDateFormat("yyyy");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(simpleDateFormatFormat.parse(currSeasonDTO.getRecruitYear()));//设置起时间
            cal.add(Calendar.YEAR, -1);//减一年
        }catch (Exception e){
            logger.info("格式化时间出错");
            e.printStackTrace();
        }
        return simpleDateFormatFormat.format(cal.getTime());
    }
    public Long seasonIsExist(RecruitSeasonDTO schoolRecruitSeason){
        return schoolRecruitSeasonExtMapper.seasonIsExist(schoolRecruitSeason);
    }
    @Transactional
    public int saveSeason(RecruitSeasonDTO recruitSeason){
        int onRecruitYear = Integer.valueOf(recruitSeason.getRecruitYear()) - 1;
        this.changePeople(recruitSeason);
        Integer seasonId = 0;
        /*添加春季时沿用上一年的招生计划，添加秋季时沿用春季的招生计划，如果春季没有招生，则再沿用上一年的计划*/
        if(recruitSeason.getRecruitSeason() == 0){
            seasonId = schoolRecruitSeasonExtMapper.selectByOnRecruitYear(String.valueOf(onRecruitYear),recruitSeason.getRecruitSeason());
        }else{
            seasonId = schoolRecruitSeasonExtMapper.selectByOnRecruitYear(recruitSeason.getRecruitYear(),0);
            if(seasonId < 0){
                seasonId = schoolRecruitSeasonExtMapper.selectByOnRecruitYear(String.valueOf(onRecruitYear),recruitSeason.getRecruitSeason());
            }
        }
        try{
            schoolRecruitSeasonExtMapper.insertSelective(recruitSeason);
            List<SchoolDTO> schoolList = schoolExtMapper.selectSchoolByNamePlan(seasonId,"");
            schoolList.forEach(SchoolDTO -> {
                SchoolRecruitPlan record = new SchoolRecruitPlan();
                record.setSeasonid(recruitSeason.getId());
                record.setSchoolid(SchoolDTO.getSchoolId());
                record.setRecruitcount(SchoolDTO.getRecruitCount() == null ? 0 : SchoolDTO.getRecruitCount());
                record.setRecruitplan(true);
                record.setIsdelete(true);
                record.setCreateby(recruitSeason.getCreateBy());
                record.setCreatetime(new Date());
                record.setUpdateby(recruitSeason.getCreateBy());
                record.setUpdatetime(new Date());
                schoolRecruitPlanMapper.insertSelective(record);
            });
            return 1;
        }catch (Exception e){
            logger.info("添加季度异常");
            e.printStackTrace();
            return 0;
        }
    }
    public int updateSeason(RecruitSeasonDTO recruitSeason){
        this.changePeople(recruitSeason);
        return schoolRecruitSeasonExtMapper.updateByPrimaryKey(recruitSeason);
    }
    public SchoolRecruitSeason selectById(Integer id){
        return schoolRecruitSeasonMapper.selectByPrimaryKey(id);
    }
    public int updateState(){
        List<Integer> list = schoolRecruitSeasonExtMapper.selectByStatus();
        StringBuffer idStr = new StringBuffer();
        list.forEach((id)->{
            idStr.append(id+",");
        });
        if(idStr.lastIndexOf(",") < 0){
            return 0;
        }else{
            return schoolRecruitSeasonExtMapper.updateByStatus(idStr.substring(0,idStr.lastIndexOf(",")));
        }
    }
    public List<SchoolRecruitSeason> findAllSeason(){
        SchoolRecruitSeasonExample example= new SchoolRecruitSeasonExample();
        return schoolRecruitSeasonMapper.selectByExample(example);
    }
    private void changePeople(RecruitSeasonDTO schoolRecruitSeason){
        Integer userId = UserHelper.getCurrentUserId();
        Date currDate = new Date();
        if(schoolRecruitSeason.getRecruitStartDate().before(currDate) &&  schoolRecruitSeason.getRecruitEndDate().after(currDate)){
            schoolRecruitSeason.setStatus(0);
        }else{
            schoolRecruitSeason.setStatus(1);
        }
        schoolRecruitSeason.setCreateBy(userId);
        schoolRecruitSeason.setCreateTime(currDate);
        schoolRecruitSeason.setUpdateBy(userId);
        schoolRecruitSeason.setUpdateTime(currDate);
    }
}
