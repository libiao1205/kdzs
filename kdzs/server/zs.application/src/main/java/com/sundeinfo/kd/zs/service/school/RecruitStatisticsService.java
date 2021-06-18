package com.sundeinfo.kd.zs.service.school;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sundeinfo.foundation.mvc.service.AbstractService;
import com.sundeinfo.kd.zs.dao.SchoolRecruitPlanExtMapper;
import com.sundeinfo.kd.zs.dao.SchoolRecruitSeasonMapper;
import com.sundeinfo.kd.zs.dao.SchoolRecruitSumExtMapper;
import com.sundeinfo.kd.zs.dao.model.SchoolExample;
import com.sundeinfo.kd.zs.dao.model.SchoolRecruitSeason;
import com.sundeinfo.kd.zs.dao.model.SchoolRecruitSeasonExample;
import com.sundeinfo.kd.zs.dto.apis.school.CurrSeasonDTO;
import com.sundeinfo.kd.zs.dto.apis.school.RecruitSumStatisticsDTO;
import com.sundeinfo.kd.zs.dto.apis.school.RecruitSumStatisticsDetailDTO;
import com.sundeinfo.kd.zs.dto.apis.school.SchoolRecruitPlanDTO;
import com.sundeinfo.kd.zs.dto.apis.school.requestDto.ReqRecruitStatisticsDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.List;

@Service
public class RecruitStatisticsService extends AbstractService<RecruitStatisticsService> {

    @Resource
    SchoolRecruitSumExtMapper schoolRecruitSumExtMapper;

    @Resource
    SchoolRecruitPlanExtMapper schoolRecruitPlanExtMapper;

    @Resource
    SchoolRecruitSeasonMapper schoolRecruitSeasonMapper;

    @Autowired
    ExportExcelService exportExcelService;

    @Autowired
    SeasonService seasonService;

    public List<RecruitSumStatisticsDTO> recruitStatistics(ReqRecruitStatisticsDTO reqRecruitStatisticsDTO){
        String currDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        reqRecruitStatisticsDTO.setCurrComparisonDate(currDate);
        List<RecruitSumStatisticsDTO> recruitSumStatisticsDTOList = new ArrayList<>();
        RecruitSumStatisticsDTO currDto = schoolRecruitSumExtMapper.findAllStatistics(reqRecruitStatisticsDTO);
        reqRecruitStatisticsDTO.setRecruitYear(reqRecruitStatisticsDTO.getOnRecruitYear());
        reqRecruitStatisticsDTO.setCurrComparisonDate("");
        reqRecruitStatisticsDTO.setOldComparisonDate(this.getComparisonDate(reqRecruitStatisticsDTO));
        RecruitSumStatisticsDTO oldDto = schoolRecruitSumExtMapper.findAllStatistics(reqRecruitStatisticsDTO);
        this.calculateRate(recruitSumStatisticsDTOList,currDto,oldDto);
        return recruitSumStatisticsDTOList;
    }

    public RecruitSumStatisticsDTO recruitStatisticsHomeAnime(ReqRecruitStatisticsDTO reqRecruitStatisticsDTO){
        String currDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        RecruitSumStatisticsDTO chunJiRecruitSumStatisticsDTO = null;
        RecruitSumStatisticsDTO recruitSumStatisticsDTO = schoolRecruitSumExtMapper.findAllRecruitCount(reqRecruitStatisticsDTO.getSeasonId(),reqRecruitStatisticsDTO.getSchoolId(),reqRecruitStatisticsDTO.getSchoolIds(),currDate,"");
        if(reqRecruitStatisticsDTO.getRecruitSeason() == 1){
            SchoolRecruitSeasonExample example= new SchoolRecruitSeasonExample();
            SchoolRecruitSeasonExample.Criteria criteria = example.createCriteria();
            criteria.andRecruityearEqualTo(reqRecruitStatisticsDTO.getRecruitYear());
            criteria.andRecruitseasonEqualTo(false);
            List<SchoolRecruitSeason> schoolRecruitSeasonList = schoolRecruitSeasonMapper.selectByExample(example);
            if(schoolRecruitSeasonList != null && schoolRecruitSeasonList.size() > 0){
                chunJiRecruitSumStatisticsDTO = schoolRecruitSumExtMapper.findAllRecruitCount(schoolRecruitSeasonList.get(0).getId(),reqRecruitStatisticsDTO.getSchoolId(),reqRecruitStatisticsDTO.getSchoolIds(),"","");
            }

        }
        if(recruitSumStatisticsDTO == null){
            recruitSumStatisticsDTO = new RecruitSumStatisticsDTO();
            recruitSumStatisticsDTO.setSumPeople(0.0);
        }else{
            if(chunJiRecruitSumStatisticsDTO != null){
                chunJiRecruitSumStatisticsDTO.setSumPeople(chunJiRecruitSumStatisticsDTO.getGkPeople() + chunJiRecruitSumStatisticsDTO.getTechnicalPeople() + chunJiRecruitSumStatisticsDTO.getUndergraduatePeople());
                recruitSumStatisticsDTO.setSumPeople(recruitSumStatisticsDTO.getGkPeople() + recruitSumStatisticsDTO.getTechnicalPeople() + recruitSumStatisticsDTO.getUndergraduatePeople()+chunJiRecruitSumStatisticsDTO.getSumPeople());
            }else{
                recruitSumStatisticsDTO.setSumPeople(recruitSumStatisticsDTO.getGkPeople() + recruitSumStatisticsDTO.getTechnicalPeople() + recruitSumStatisticsDTO.getUndergraduatePeople());
            }
        }
        SchoolRecruitPlanDTO schoolRecruitPlanDTO = schoolRecruitPlanExtMapper.findAllPlanRecruitPlan(reqRecruitStatisticsDTO.getSeasonId(),reqRecruitStatisticsDTO.getSchoolId(),reqRecruitStatisticsDTO.getSchoolIds());

        recruitSumStatisticsDTO.setPlanSumPeople(Double.valueOf(String.valueOf(schoolRecruitPlanDTO.getRecruitcount())));
        DecimalFormat df = new DecimalFormat(".00");
        if(recruitSumStatisticsDTO.getSumPeople() != null && recruitSumStatisticsDTO.getPlanSumPeople() != null && recruitSumStatisticsDTO.getPlanSumPeople() != 0){
            double rate = Double.valueOf(df.format(recruitSumStatisticsDTO.getSumPeople()/recruitSumStatisticsDTO.getPlanSumPeople()))*100;
            recruitSumStatisticsDTO.setRecruitRate(rate >= 100 ? 100.0 : rate);
        }
        return recruitSumStatisticsDTO;
    }

    //小程序中小计放在分校前面，excel中小计房子分校后面 flag ==true 在前，flag ==false 在后
    public List<RecruitSumStatisticsDetailDTO> recruitStatisticsDetail(ReqRecruitStatisticsDTO reqRecruitStatisticsDTO,Boolean flag){
        String currDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        reqRecruitStatisticsDTO.setCurrComparisonDate(currDate);
        if((reqRecruitStatisticsDTO.getSchoolId() != null && reqRecruitStatisticsDTO.getSchoolId() > 0) ||
        StringUtils.isNotBlank(reqRecruitStatisticsDTO.getSchoolIds())){
            reqRecruitStatisticsDTO.setSort(0);
        }
        //招生季结束后，查询本季招生统计时要用招生人数总表，防止人数校正后不响应
        CurrSeasonDTO currSeasonDTO = seasonService.getCurrSeason();
        if(currSeasonDTO == null){
            List<RecruitSumStatisticsDetailDTO> currYearList = schoolRecruitSumExtMapper.findAllStatisticsDetailSearchEnd(reqRecruitStatisticsDTO);
            ReqRecruitStatisticsDTO oldStatisticsDTO = new ReqRecruitStatisticsDTO();
            oldStatisticsDTO.setRecruitSeason(reqRecruitStatisticsDTO.getRecruitSeason());
            oldStatisticsDTO.setSchoolId(reqRecruitStatisticsDTO.getSchoolId());
            oldStatisticsDTO.setSort(reqRecruitStatisticsDTO.getSort());
            oldStatisticsDTO.setRecruitYear(reqRecruitStatisticsDTO.getOnRecruitYear());
            oldStatisticsDTO.setOldComparisonDate(this.getComparisonDate(oldStatisticsDTO));
            List<RecruitSumStatisticsDetailDTO> oldYearList = schoolRecruitSumExtMapper.findAllStatisticsDetail(oldStatisticsDTO);
            List<RecruitSumStatisticsDetailDTO> removeList = new ArrayList<>();
            if(reqRecruitStatisticsDTO.getRecruitSeason() == 0){
                this.chunJiSource(currYearList,oldYearList,removeList);
            }else if(reqRecruitStatisticsDTO.getRecruitSeason() == 1){
                this.qiuJiSource(currYearList,oldYearList,removeList,reqRecruitStatisticsDTO,oldStatisticsDTO);
            }
            removeList.forEach((dto) ->{
                currYearList.remove(dto);
            });
            if(reqRecruitStatisticsDTO.getSort() == 1){
                List<RecruitSumStatisticsDetailDTO> list = this.sumSchoolSort(currYearList,reqRecruitStatisticsDTO);
                this.DetailCalculateRate(list,flag);
                return list;
            }
            currYearList.sort(Comparator.comparing(RecruitSumStatisticsDetailDTO::getSumPeople).reversed());
            this.DetailCalculateRate(currYearList,flag);
            return currYearList;
        }else {
            List<RecruitSumStatisticsDetailDTO> currYearList = schoolRecruitSumExtMapper.findAllStatisticsDetail(reqRecruitStatisticsDTO);
            ReqRecruitStatisticsDTO oldStatisticsDTO = new ReqRecruitStatisticsDTO();
            oldStatisticsDTO.setRecruitSeason(reqRecruitStatisticsDTO.getRecruitSeason());
            oldStatisticsDTO.setSchoolId(reqRecruitStatisticsDTO.getSchoolId());
            oldStatisticsDTO.setSort(reqRecruitStatisticsDTO.getSort());
            oldStatisticsDTO.setRecruitYear(reqRecruitStatisticsDTO.getOnRecruitYear());
            oldStatisticsDTO.setOldComparisonDate(this.getComparisonDate(oldStatisticsDTO));
            List<RecruitSumStatisticsDetailDTO> oldYearList = schoolRecruitSumExtMapper.findAllStatisticsDetail(oldStatisticsDTO);
            List<RecruitSumStatisticsDetailDTO> removeList = new ArrayList<>();
            if(reqRecruitStatisticsDTO.getRecruitSeason() == 0){
                this.chunJiSource(currYearList,oldYearList,removeList);
            }else if(reqRecruitStatisticsDTO.getRecruitSeason() == 1){
                this.qiuJiSource(currYearList,oldYearList,removeList,reqRecruitStatisticsDTO,oldStatisticsDTO);
            }
            removeList.forEach((dto) ->{
                currYearList.remove(dto);
            });
            if(reqRecruitStatisticsDTO.getSort() == 1){
                List<RecruitSumStatisticsDetailDTO> list = this.sumSchoolSort(currYearList,reqRecruitStatisticsDTO);
                this.DetailCalculateRate(list,flag);
                return list;
            }
            currYearList.sort(Comparator.comparing(RecruitSumStatisticsDetailDTO::getSumPeople).reversed());
            this.DetailCalculateRate(currYearList,flag);
            return currYearList;
        }
    }

    public int doExcel(ReqRecruitStatisticsDTO reqRecruitStatisticsDTO, HttpServletResponse response) throws IOException {
        List<RecruitSumStatisticsDetailDTO> list = new ArrayList<>();
        for(int i = 1;i < 5;i ++) {
            reqRecruitStatisticsDTO.setSort(i);
            list.addAll(this.recruitStatisticsDetail(reqRecruitStatisticsDTO,false));
        }
        if(list.size() < 0 ){
            return 0;
        }
        return exportExcelService.excel(list,reqRecruitStatisticsDTO,response);
    }
    /**计算去年比对日期*/
    private String getComparisonDate(ReqRecruitStatisticsDTO reqRecruitStatisticsDTO){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //当春季招生提前开始例如：20年底开始21年的春季招生，进行同期比时需要用到此参数计算时间
        if(reqRecruitStatisticsDTO.getRecruitYear().equals(new SimpleDateFormat("yyyy").format(new Date()))){
            reqRecruitStatisticsDTO.setCurrDate("1987-01-01");//年份无所谓
        }
        Date maxDate = schoolRecruitSumExtMapper.findMaxDate(reqRecruitStatisticsDTO);
        Date minDate = schoolRecruitSumExtMapper.findMinDate(reqRecruitStatisticsDTO);
        Date currDate = new Date();
        if(maxDate == null && minDate == null){
            return sdf.format(currDate);
        }else if(maxDate != null && minDate == null){
            return sdf.format(maxDate);
        }else if(maxDate == null && minDate != null){
            return sdf.format(minDate);
        }

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,cal.get(Calendar.YEAR)-1);
        Date date = cal.getTime();
        //相差几天
        long beforeDay = Duration.between(maxDate.toInstant(),date.toInstant()).toDays();
        long laterDay = Duration.between(minDate.toInstant(),date.toInstant()).toDays();
        if(laterDay < 0){
            laterDay = Math.abs(laterDay);
        }
        if(beforeDay < 0){
            beforeDay = Math.abs(beforeDay);
        }
        //前max 当前curr 后min
        if(beforeDay > laterDay){
            return sdf.format(minDate);
        }else {
            return sdf.format(maxDate);
        }
    }

    /**春季数据包*/
    private void chunJiSource(List<RecruitSumStatisticsDetailDTO> currYearList,
                              List<RecruitSumStatisticsDetailDTO> oldYearList,
                              List<RecruitSumStatisticsDetailDTO> removeList){
        for(int i = 0; i < currYearList.size(); i ++){
            RecruitSumStatisticsDetailDTO currDto = currYearList.get(i);
            currDto.setRecruitCount(currDto.getRecruitCount() == null ? 0 : currDto.getRecruitCount());
            currDto.setSumPeople(currDto.getSumPeople() == null ? 0 : currDto.getSumPeople());
            currDto.setUndergraduatePeople(currDto.getUndergraduatePeople() == null ? 0 : currDto.getUndergraduatePeople());
            currDto.setTechnicalPeople(currDto.getTechnicalPeople() == null ? 0 : currDto.getTechnicalPeople());
            currDto.setGkPeople(currDto.getGkPeople() == null ? 0 : currDto.getGkPeople());
            currDto.setChunJiSumPeople(currDto.getSumPeople().intValue());
            currDto.setChunQiuSumPeople(currDto.getSumPeople().intValue());
            if(oldYearList.size() == 0){
                currDto.setOldUndergraduatePeople(0);
                currDto.setOldTechnicalPeople(0);
                currDto.setOldGkPeople(0);
                currDto.setOldSumPeople(0.0);
                currDto.setOldChunJiSumPeople(0);
            }else{
                RecruitSumStatisticsDetailDTO oldDto = oldYearList.get(i);
                currDto.setOldUndergraduatePeople(oldDto.getUndergraduatePeople() == null ? 0 : oldDto.getUndergraduatePeople());
                currDto.setOldTechnicalPeople(oldDto.getTechnicalPeople() == null ? 0 : oldDto.getTechnicalPeople());
                currDto.setOldGkPeople(oldDto.getGkPeople() == null ? 0 : oldDto.getGkPeople());
                currDto.setOldSumPeople(oldDto.getSumPeople() == null ? 0 : oldDto.getSumPeople());
                currDto.setOldChunJiSumPeople(currDto.getOldSumPeople().intValue());
            }
            if(currDto.getRecruitPlan() == 0){
                removeList.add(currDto);
            }
        }
    }

    /**秋季数据包*/
    private void qiuJiSource(List<RecruitSumStatisticsDetailDTO> currYearList,
                             List<RecruitSumStatisticsDetailDTO> oldYearList,
                             List<RecruitSumStatisticsDetailDTO> removeList,
                             ReqRecruitStatisticsDTO reqRecruitStatisticsDTO,
                             ReqRecruitStatisticsDTO oldStatisticsDTO){
        reqRecruitStatisticsDTO.setRecruitSeason(0);
        String currDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        reqRecruitStatisticsDTO.setCurrComparisonDate(currDate);
        List<Integer> chunJiSumList = schoolRecruitSumExtMapper.findAllStatisticsQuiJiCurrSeasonSumPeopleDetail(reqRecruitStatisticsDTO);
        oldStatisticsDTO.setRecruitSeason(0);
        List<Integer> oldChunJiSumList = schoolRecruitSumExtMapper.findAllStatisticsQuiJiCurrSeasonSumPeopleDetail(oldStatisticsDTO);
        reqRecruitStatisticsDTO.setRecruitSeason(1);
        oldStatisticsDTO.setRecruitSeason(1);
        for(int i = 0; i < currYearList.size(); i ++){
            RecruitSumStatisticsDetailDTO currDto = currYearList.get(i);
            Integer chunJiSeasonSum = 0;
            if(chunJiSumList != null && chunJiSumList.size() > 0){
                chunJiSeasonSum = chunJiSumList.get(i);
            }
            currDto.setRecruitCount(currDto.getRecruitCount() == null ? 0 : currDto.getRecruitCount());
            currDto.setSumPeople(currDto.getSumPeople() == null ? 0 : currDto.getSumPeople());
            currDto.setUndergraduatePeople(currDto.getUndergraduatePeople() == null ? 0 : currDto.getUndergraduatePeople());
            currDto.setTechnicalPeople(currDto.getTechnicalPeople() == null ? 0 : currDto.getTechnicalPeople());
            currDto.setGkPeople(currDto.getGkPeople() == null ? 0 : currDto.getGkPeople());
            currDto.setChunJiSumPeople(chunJiSeasonSum);
            currDto.setChunJiSumPeople(currDto.getChunJiSumPeople() == null ? 0 : currDto.getChunJiSumPeople());
            currDto.setChunQiuSumPeople((int)(currDto.getSumPeople()  == null ? 0 : currDto.getSumPeople()) + currDto.getChunJiSumPeople());
            if(oldYearList.size() == 0){
                currDto.setOldUndergraduatePeople(0);
                currDto.setOldTechnicalPeople(0);
                currDto.setOldGkPeople(0);
                currDto.setOldSumPeople(0.0);
            }else{
                Integer oldChunJiSeasonSum = 0;
                RecruitSumStatisticsDetailDTO oldDto = oldYearList.get(i);
                if(oldChunJiSumList != null && oldChunJiSumList.size() > 0){
                    oldChunJiSeasonSum = oldChunJiSumList.get(i);
                }
                currDto.setOldUndergraduatePeople(oldDto.getUndergraduatePeople() == null ? 0 : oldDto.getUndergraduatePeople());
                currDto.setOldTechnicalPeople(oldDto.getTechnicalPeople() == null ? 0 : oldDto.getTechnicalPeople());
                currDto.setOldGkPeople(oldDto.getGkPeople() == null ? 0 : oldDto.getGkPeople());
                currDto.setOldSumPeople(oldDto.getSumPeople() == null ? 0 : oldDto.getSumPeople());
                currDto.setOldChunJiSumPeople(oldChunJiSeasonSum);
            }
            if(currDto.getRecruitPlan() == 0 ){
                removeList.add(currDto);
            }
        }
    }

    /**总校排序*/
    private List<RecruitSumStatisticsDetailDTO> sumSchoolSort(List<RecruitSumStatisticsDetailDTO> currYearList,ReqRecruitStatisticsDTO dto){
        List<RecruitSumStatisticsDetailDTO> list = new ArrayList<>();
        for(int i = 0; i < currYearList.size(); i ++){
            list.add(currYearList.get(i));
            if(i == 4){
                if(dto.getRecruitSeason() == 0) {
                    list.sort(Comparator.comparing(RecruitSumStatisticsDetailDTO::getSumPeople).reversed());
                }else{
                    list.sort(Comparator.comparing(RecruitSumStatisticsDetailDTO::getChunQiuSumPeople).reversed());
                }
            }
        }
        return list;
    }

    /**计算小计*/
    private void DetailCalculateRate (List<RecruitSumStatisticsDetailDTO> recruitSumStatisticsDetailDTOList,Boolean flag){
        if(recruitSumStatisticsDetailDTOList.size() == 1){
            RecruitSumStatisticsDetailDTO dto = recruitSumStatisticsDetailDTOList.get(0);
            this.replaceRecruitSum(dto);
            return ;
        }
        RecruitSumStatisticsDetailDTO dtoInfo = new RecruitSumStatisticsDetailDTO();
        dtoInfo.setSumPeople((dtoInfo.getSumPeople() == null ? 0 : dtoInfo.getSumPeople()));
        dtoInfo.setChunJiSumPeople(dtoInfo.getChunJiSumPeople() == null ? 0 : dtoInfo.getChunJiSumPeople());
        for(int i = 0; i < recruitSumStatisticsDetailDTOList.size(); i ++){
            RecruitSumStatisticsDetailDTO dto = recruitSumStatisticsDetailDTOList.get(i);
            dto.setRecruitCount(dto.getRecruitCount());
            dtoInfo.setRecruitCount((dtoInfo.getRecruitCount() == null ? 0 : dtoInfo.getRecruitCount()) + dto.getRecruitCount());
            dtoInfo.setUndergraduatePeople((dtoInfo.getUndergraduatePeople() == null ? 0 : dtoInfo.getUndergraduatePeople()) + (dto.getUndergraduatePeople()));
            dtoInfo.setOldUndergraduatePeople((dtoInfo.getOldUndergraduatePeople() == null ? 0 : dtoInfo.getOldUndergraduatePeople()) + (dto.getOldUndergraduatePeople()));
            dtoInfo.setTechnicalPeople((dtoInfo.getTechnicalPeople() == null ? 0 : dtoInfo.getTechnicalPeople()) + (dto.getTechnicalPeople()));
            dtoInfo.setOldTechnicalPeople((dtoInfo.getOldTechnicalPeople() == null ? 0 : dtoInfo.getOldTechnicalPeople()) + (dto.getOldTechnicalPeople()));
            dtoInfo.setGkPeople((dtoInfo.getGkPeople() == null ? 0 : dtoInfo.getGkPeople()) + (dto.getGkPeople()));
            dtoInfo.setOldGkPeople((dtoInfo.getOldGkPeople() == null ? 0 : dtoInfo.getOldGkPeople()) + (dto.getOldGkPeople()));
            dtoInfo.setSumPeople(dtoInfo.getSumPeople() + dto.getSumPeople());
            dtoInfo.setOldSumPeople((dtoInfo.getOldSumPeople() == null ? 0 : dtoInfo.getOldSumPeople()) + (dto.getOldSumPeople()));
            dtoInfo.setChunJiSumPeople((dtoInfo.getChunJiSumPeople() == null ? 0 : dtoInfo.getChunJiSumPeople()) + (dto.getChunJiSumPeople()));
            dtoInfo.setChunQiuSumPeople ((dtoInfo.getChunQiuSumPeople() == null ? 0 : dtoInfo.getChunQiuSumPeople()) + (dto.getChunQiuSumPeople()));
            this.replaceRecruitSum(dto);
        }
        dtoInfo.setName("小计");
        dtoInfo.setCellBgColor("background-color:#338FCC;");
        this.replaceRecruitSum(dtoInfo);
        if(flag){
            recruitSumStatisticsDetailDTOList.add(0,dtoInfo);
        }else{
            recruitSumStatisticsDetailDTOList.add(dtoInfo);
        }

    }

    /**春季的总招生人数是本季总和，秋季的总招生人数是全年总和*/
    private void replaceRecruitSum(RecruitSumStatisticsDetailDTO dto){
        Double sumPeople ;
        if(dto.getChunJiSumPeople() > 0){
            sumPeople = Double.valueOf(String.valueOf(dto.getChunQiuSumPeople()));
        }else{
            sumPeople = dto.getSumPeople();
        }
        if(sumPeople > 0){
            this.DetailCalculateRateInfo(dto);
        }
    }

    /**计算统计页面招生率*/
    private void DetailCalculateRateInfo(RecruitSumStatisticsDetailDTO dto){
        DecimalFormat df = new DecimalFormat(".00");
        Double currSumPeople;
        Double oldSumPeople;
        if(dto.getChunJiSumPeople() > 0){
            currSumPeople = Double.valueOf(String.valueOf(dto.getSumPeople()));
            oldSumPeople = Double.valueOf((dto.getOldSumPeople() == null ? 0 : dto.getOldSumPeople()));
        }else{
            currSumPeople = dto.getSumPeople();
            oldSumPeople = dto.getOldSumPeople() == null ? 0 : dto.getOldSumPeople();
        }
        //计算同比增长率
        if(oldSumPeople == 0){
            dto.setRecruitRate(null);
        }else{
            dto.setRecruitRate(Double.valueOf(df.format((currSumPeople - oldSumPeople)/oldSumPeople * 100)));
        }
        if(dto.getRecruitRate() != null){
            if(dto.getRecruitRate() >= 0){
                dto.setRateType(0);
            }else{
                dto.setRateType(1);
            }
        }else{
            dto.setRateType(0);
        }

        //计算计划完成率
        if(dto.getRecruitCount() == 0){
            dto.setPlanAccomplishRate(Double.valueOf(df.format(dto.getChunQiuSumPeople() * 100)));
        }else{
            dto.setPlanAccomplishRate(Double.valueOf(df.format(dto.getChunQiuSumPeople()/dto.getRecruitCount() * 100)));
        }
    }

    /**计算首页招生率*/
     private void calculateRate(List<RecruitSumStatisticsDTO> recruitSumStatisticsDTOList,RecruitSumStatisticsDTO currDto,RecruitSumStatisticsDTO oldDto){
        if(oldDto == null && currDto != null){
            oldDto = new RecruitSumStatisticsDTO(0.0,0.0,0,0.0,0.0,0,0.0,0.0,0,0.0,0.0,0);
            currDto.setRecruitRate(100.0);
            currDto.setUndergraduateRate(100.0);
            currDto.setTechnicalRate(100.0);
            currDto.setGkRate(100.0);
            currDto.setRateType(0);
        }else if(currDto == null && oldDto == null){
            oldDto = new RecruitSumStatisticsDTO(0.0,0.0,0,0.0,0.0,0,0.0,0.0,0,0.0,0.0,0);
            currDto = oldDto;
        }else if(currDto == null && oldDto != null){
            currDto = new RecruitSumStatisticsDTO(0.0,0.0,0,0.0,0.0,0,0.0,0.0,0,0.0,0.0,0);
        }else{
            //将国开数据暂且交个上开——————————————————————————后续要改
            currDto.setUndergraduatePeople(currDto.getUndergraduatePeople()+currDto.getGkPeople());
            oldDto.setUndergraduatePeople(oldDto.getUndergraduatePeople()+oldDto.getGkPeople());
            DecimalFormat df = new DecimalFormat(".00");
            //总计增长率
            if(oldDto.getSumPeople() == 0){
                currDto.setRecruitRate(Double.valueOf(df.format((currDto.getSumPeople() * 100))));
            }else{
                currDto.setRecruitRate(Double.valueOf(df.format((currDto.getSumPeople() - oldDto.getSumPeople())/oldDto.getSumPeople() * 100)));
            }
            if(currDto.getRecruitRate() >= 0){
                currDto.setRateType(0);
            }else{
                currDto.setRateType(1);
            }
            //本科增长率
            if(oldDto.getUndergraduatePeople() == 0){
                currDto.setUndergraduateRate(Double.valueOf(df.format((currDto.getUndergraduatePeople() * 100))));
            }else{
                currDto.setUndergraduateRate(Double.valueOf(df.format((currDto.getUndergraduatePeople() - oldDto.getUndergraduatePeople())/oldDto.getUndergraduatePeople() * 100)));
            }
            if(currDto.getUndergraduateRate() >= 0){
                currDto.setUndergraduateRateType(0);
            }else{
                currDto.setUndergraduateRateType(1);
            }
            //专科增长率
            if(oldDto.getTechnicalPeople() == 0){
                currDto.setTechnicalRate(Double.valueOf(df.format((currDto.getTechnicalPeople() * 100))));
            }else{
                currDto.setTechnicalRate(Double.valueOf(df.format((currDto.getTechnicalPeople() - oldDto.getTechnicalPeople())/oldDto.getTechnicalPeople() * 100)));
            }
            if(currDto.getTechnicalRate() >= 0){
                currDto.setTechnicalRateType(0);
            }else{
                currDto.setTechnicalRateType(1);
            }
            //国开暂不计算，将数据累加到上开中——————————————————————————后续要改
            /*if(currDto.getGkPeople() > oldDto.getGkPeople()){
                currDto.setGkRate(Double.valueOf(df.format((currDto.getGkPeople() - oldDto.getGkPeople())/currDto.getGkPeople() * 100)));
                currDto.setGkRateType(0);
            }else if(currDto.getGkPeople() < oldDto.getGkPeople()){
                currDto.setGkRate(Double.valueOf(df.format((oldDto.getGkPeople() - currDto.getGkPeople())/oldDto.getGkPeople() * 100)));
                currDto.setGkRateType(1);
            }else{
                currDto.setGkRate(0.0);
                currDto.setGkRateType(0);
            }*/
        }
         recruitSumStatisticsDTOList.add(currDto);
         recruitSumStatisticsDTOList.add(oldDto);
    }

}
