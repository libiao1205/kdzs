package com.sundeinfo.kd.zs.service.school;

import com.sundeinfo.foundation.mvc.service.AbstractService;
import com.sundeinfo.kd.zs.dao.SchoolRecruitCountingMapper;
import com.sundeinfo.kd.zs.dao.SchoolRecruitSumMapper;
import com.sundeinfo.kd.zs.dao.model.SchoolRecruitCounting;
import com.sundeinfo.kd.zs.dao.model.SchoolRecruitCountingHist;
import com.sundeinfo.kd.zs.dao.model.SchoolRecruitSum;
import com.sundeinfo.kd.zs.dao.model.SchoolRecruitSumKey;
import com.sundeinfo.kd.zs.utility.UserHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SchoolRecruitSumService extends AbstractService<SchoolRecruitService> {
    @Resource
    SchoolRecruitSumMapper schoolRecruitSumMapper;
    @Resource
    private SchoolRecruitCountingMapper schoolRecruitCountingMapper;

    @Resource
    com.sundeinfo.kd.zs.dao.SchoolRecruitCountingHistMapper SchoolRecruitCountingHistMapper;

    public SchoolRecruitSum isExist(SchoolRecruitSumKey schoolRecruitSumKey){
        return schoolRecruitSumMapper.selectByPrimaryKey(schoolRecruitSumKey);
    }
    @Transactional
    public void saveSchoolRecruitSum(SchoolRecruitCounting record){
        Integer userId = UserHelper.getCurrentUserId();
        SchoolRecruitCounting recruitCounting = new SchoolRecruitCounting();
        recruitCounting.setSchoolid(record.getSchoolid());
        recruitCounting.setSeasonid(record.getSeasonid());
        recruitCounting.setRecruitdate(record.getRecruitdate());
        recruitCounting.setCreateby(userId);
        recruitCounting.setCreatetime(new Date());
        recruitCounting.setUpdateby(userId);
        recruitCounting.setUpdatetime(new Date());
        SchoolRecruitCounting schoolRecruitCounting = schoolRecruitCountingMapper.selectByPrimaryKey(record);
        if(schoolRecruitCounting == null){
            recruitCounting.setUndergraduatepeople(record.getUndergraduatepeople());
            recruitCounting.setTechnicalpeople(record.getTechnicalpeople());
            recruitCounting.setGkpeople(record.getGkpeople());
            schoolRecruitCountingMapper.insertSelective(recruitCounting);
        }else {
            recruitCounting.setUndergraduatepeople(record.getUndergraduatepeople() + schoolRecruitCounting.getUndergraduatepeople());
            recruitCounting.setTechnicalpeople(record.getTechnicalpeople() + schoolRecruitCounting.getTechnicalpeople());
            recruitCounting.setGkpeople(record.getGkpeople() + schoolRecruitCounting.getGkpeople());
            schoolRecruitCountingMapper.updateByPrimaryKeySelective(recruitCounting);
        }
        SchoolRecruitSumKey schoolRecruitSumKey = new SchoolRecruitSumKey();
        schoolRecruitSumKey.setSeasonid(record.getSeasonid());
        schoolRecruitSumKey.setSchoolid(record.getSchoolid());
        SchoolRecruitSum schoolRecruitSum = this.isExist(schoolRecruitSumKey);
        SchoolRecruitSum schoolRecruitSumModel = new SchoolRecruitSum();
        schoolRecruitSumModel.setSchoolid(record.getSchoolid());
        schoolRecruitSumModel.setSeasonid(record.getSeasonid());
        this.changPeople(schoolRecruitSumModel);
        if(schoolRecruitSum == null){
            schoolRecruitSumModel.setUndergraduatepeople(record.getUndergraduatepeople());
            schoolRecruitSumModel.setTechnicalpeople(record.getTechnicalpeople());
            schoolRecruitSumModel.setGkpeople(record.getGkpeople());
            schoolRecruitSumMapper.insert(schoolRecruitSumModel);
        }else {
            schoolRecruitSumModel.setUndergraduatepeople(record.getUndergraduatepeople() + schoolRecruitSum.getUndergraduatepeople());
            schoolRecruitSumModel.setTechnicalpeople(record.getTechnicalpeople() + schoolRecruitSum.getTechnicalpeople());
            schoolRecruitSumModel.setGkpeople(record.getGkpeople() + schoolRecruitSum.getGkpeople());
            schoolRecruitSumMapper.updateByPrimaryKeySelective(schoolRecruitSumModel);
        }
        SchoolRecruitCountingHist history = new SchoolRecruitCountingHist();
        history.setSeasonid(record.getSeasonid());
        history.setSchoolid(record.getSchoolid());
        history.setRecruitdate(record.getRecruitdate());
        history.setUndergraduatepeople(record.getUndergraduatepeople());
        history.setTechnicalpeople(record.getTechnicalpeople());
        history.setGkpeople(record.getGkpeople());
        history.setCreateby(userId);
        history.setCreatetime(new Date());
        history.setUpdateby(userId);
        history.setUpdatetime(new Date());
        SchoolRecruitCountingHistMapper.insertSelective(history);
    }

    public int update(SchoolRecruitSum schoolRecruitSumModel){
        this.changPeople(schoolRecruitSumModel);
        schoolRecruitSumModel.setRevise(true);//0:系统计算，1：人工校正
        SchoolRecruitSum schoolRecruitSum = this.isExist(schoolRecruitSumModel);
        if(schoolRecruitSum == null){
            return schoolRecruitSumMapper.insert(schoolRecruitSumModel);
        }else{
            return schoolRecruitSumMapper.updateByPrimaryKeySelective(schoolRecruitSumModel);
        }
    }
    private void changPeople(SchoolRecruitSum schoolRecruitSumModel){
        Integer userId = UserHelper.getCurrentUserId();
        schoolRecruitSumModel.setCreateby(userId);
        schoolRecruitSumModel.setCreatetime(new Date());
        schoolRecruitSumModel.setUpdateby(userId);
        schoolRecruitSumModel.setUpdatetime(new Date());
    }
}
