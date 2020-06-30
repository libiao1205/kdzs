package com.sundeinfo.kd.zs.service.school;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sundeinfo.foundation.mvc.service.AbstractService;
import com.sundeinfo.kd.zs.dao.SchoolRecruitPlanExtMapper;
import com.sundeinfo.kd.zs.dao.SchoolRecruitSumExtMapper;
import com.sundeinfo.kd.zs.dto.apis.school.RecruitSumStatisticsDTO;
import com.sundeinfo.kd.zs.dto.apis.school.RecruitSumStatisticsDetailDTO;
import com.sundeinfo.kd.zs.dto.apis.school.SchoolRecruitPlanDTO;
import com.sundeinfo.kd.zs.dto.apis.school.requestDto.ReqRecruitStatisticsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RecruitStatisticsService extends AbstractService<RecruitStatisticsService> {
    @Resource
    SchoolRecruitSumExtMapper schoolRecruitSumExtMapper;

    @Resource
    SchoolRecruitPlanExtMapper schoolRecruitPlanExtMapper;

    @Autowired
    ExportExcelService exportExcelService;

    public List<RecruitSumStatisticsDTO> recruitStatistics(ReqRecruitStatisticsDTO reqRecruitStatisticsDTO){
        List<RecruitSumStatisticsDTO> recruitSumStatisticsDTOList = new ArrayList<>();
        RecruitSumStatisticsDTO currDto = schoolRecruitSumExtMapper.findAllStatistics(reqRecruitStatisticsDTO);
        reqRecruitStatisticsDTO.setRecruitYear(reqRecruitStatisticsDTO.getOnRecruitYear());
        RecruitSumStatisticsDTO oldDto = schoolRecruitSumExtMapper.findAllStatistics(reqRecruitStatisticsDTO);
        this.calculateRate(recruitSumStatisticsDTOList,currDto,oldDto);
        return recruitSumStatisticsDTOList;
    }

    public RecruitSumStatisticsDTO recruitStatisticsHomeAnime(ReqRecruitStatisticsDTO reqRecruitStatisticsDTO){
        RecruitSumStatisticsDTO recruitSumStatisticsDTO = schoolRecruitSumExtMapper.findAllRecruitCount(reqRecruitStatisticsDTO.getSeasonId(),reqRecruitStatisticsDTO.getSchoolId());
        SchoolRecruitPlanDTO schoolRecruitPlanDTO = schoolRecruitPlanExtMapper.findAllPlanRecruitPlan(reqRecruitStatisticsDTO.getSeasonId(),reqRecruitStatisticsDTO.getSchoolId());
        recruitSumStatisticsDTO.setPlanGkPeople(Double.valueOf(String.valueOf(schoolRecruitPlanDTO.getGkpeople())));
        recruitSumStatisticsDTO.setPlanTechnicalPeople(Double.valueOf(String.valueOf(schoolRecruitPlanDTO.getTechnicalpeople())));
        recruitSumStatisticsDTO.setPlanUndergraduatePeople(Double.valueOf(String.valueOf(schoolRecruitPlanDTO.getUndergraduatepeople())));
        DecimalFormat df = new DecimalFormat(".00");
        if(recruitSumStatisticsDTO.getGkPeople() != null && recruitSumStatisticsDTO.getPlanGkPeople() != null){
            recruitSumStatisticsDTO.setGkRate(Double.valueOf(df.format(recruitSumStatisticsDTO.getGkPeople()/recruitSumStatisticsDTO.getPlanGkPeople()))*100);
        }
        if(recruitSumStatisticsDTO.getTechnicalPeople() != null && recruitSumStatisticsDTO.getPlanTechnicalPeople() != null){
            double rate = Double.valueOf(df.format(recruitSumStatisticsDTO.getTechnicalPeople()/recruitSumStatisticsDTO.getPlanTechnicalPeople()))*100;
            recruitSumStatisticsDTO.setTechnicalRate(rate >= 100 ? 100.0 : rate);
        }
        if(recruitSumStatisticsDTO.getUndergraduatePeople() != null && recruitSumStatisticsDTO.getPlanUndergraduatePeople() != null){
            recruitSumStatisticsDTO.setUndergraduateRate(Double.valueOf(df.format(recruitSumStatisticsDTO.getUndergraduatePeople()/recruitSumStatisticsDTO.getPlanUndergraduatePeople()))*100);
        }
        return recruitSumStatisticsDTO;
    }

    public List<RecruitSumStatisticsDetailDTO> recruitStatisticsDetail(ReqRecruitStatisticsDTO reqRecruitStatisticsDTO){
        List<RecruitSumStatisticsDetailDTO> currYearList = schoolRecruitSumExtMapper.findAllStatisticsDetail(reqRecruitStatisticsDTO);
        ReqRecruitStatisticsDTO oldStatisticsDTO = new ReqRecruitStatisticsDTO();
        oldStatisticsDTO.setRecruitSeason(reqRecruitStatisticsDTO.getRecruitSeason());
        oldStatisticsDTO.setSchoolId(reqRecruitStatisticsDTO.getSchoolId());
        oldStatisticsDTO.setSort(reqRecruitStatisticsDTO.getSort());
        oldStatisticsDTO.setRecruitYear(reqRecruitStatisticsDTO.getOnRecruitYear());
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
        this.DetailCalculateRate(currYearList);

        if(reqRecruitStatisticsDTO.getSort() == 1){
            return this.sumSchoolSort(currYearList,reqRecruitStatisticsDTO);
        }
        currYearList.sort(Comparator.comparing(RecruitSumStatisticsDetailDTO::getSumPeople).reversed());
        return currYearList;
    }

    public int doExcel(ReqRecruitStatisticsDTO reqRecruitStatisticsDTO, HttpServletResponse response) throws IOException {
        List<RecruitSumStatisticsDetailDTO> list = new ArrayList<>();
        for(int i = 1;i < 5;i ++) {
            reqRecruitStatisticsDTO.setSort(i);
            list.addAll(this.recruitStatisticsDetail(reqRecruitStatisticsDTO));
        }
        if(list.size() < 0 ){
            return 0;
        }
        return exportExcelService.excel(list,reqRecruitStatisticsDTO,response);
    }

    //春季数据包
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
            currDto.setChunJiSumPeople(0);
            currDto.setOldChunJiSumPeople(0);
            currDto.setChunQiuSumPeople(0);
            if(oldYearList.size() == 0){
                currDto.setOldUndergraduatePeople(0);
                currDto.setOldTechnicalPeople(0);
                currDto.setOldGkPeople(0);
                currDto.setOldSumPeople(0.0);
            }else{
                RecruitSumStatisticsDetailDTO oldDto = oldYearList.get(i);
                currDto.setOldUndergraduatePeople(oldDto.getUndergraduatePeople() == null ? 0 : oldDto.getUndergraduatePeople());
                currDto.setOldTechnicalPeople(oldDto.getTechnicalPeople() == null ? 0 : oldDto.getTechnicalPeople());
                currDto.setOldGkPeople(oldDto.getGkPeople() == null ? 0 : oldDto.getGkPeople());
                currDto.setOldSumPeople(oldDto.getSumPeople() == null ? 0 : oldDto.getSumPeople());
            }
            if(currDto.getSumPeople() == 0 && currDto.getOldSumPeople() == 0){
                removeList.add(currDto);
            }
        }
    }
    //秋季数据包
    private void qiuJiSource(List<RecruitSumStatisticsDetailDTO> currYearList,
                             List<RecruitSumStatisticsDetailDTO> oldYearList,
                             List<RecruitSumStatisticsDetailDTO> removeList,
                             ReqRecruitStatisticsDTO reqRecruitStatisticsDTO,
                             ReqRecruitStatisticsDTO oldStatisticsDTO){
        reqRecruitStatisticsDTO.setRecruitSeason(0);
        List<Integer> chunJiSumList = schoolRecruitSumExtMapper.findAllStatisticsQuiJiCurrSeasonSumPeopleDetail(reqRecruitStatisticsDTO);
        oldStatisticsDTO.setRecruitSeason(0);
        List<Integer> oldChunJiSumList = schoolRecruitSumExtMapper.findAllStatisticsQuiJiCurrSeasonSumPeopleDetail(oldStatisticsDTO);
        reqRecruitStatisticsDTO.setRecruitSeason(1);
        oldStatisticsDTO.setRecruitSeason(1);
        for(int i = 0; i < currYearList.size(); i ++){
            RecruitSumStatisticsDetailDTO currDto = currYearList.get(i);
            Integer chunJiSeasonSum = chunJiSumList.get(i);
            currDto.setRecruitCount(currDto.getRecruitCount() == null ? 0 : currDto.getRecruitCount());
            currDto.setSumPeople(currDto.getSumPeople() == null ? 0 : currDto.getSumPeople());
            currDto.setUndergraduatePeople(currDto.getUndergraduatePeople() == null ? 0 : currDto.getUndergraduatePeople());
            currDto.setTechnicalPeople(currDto.getTechnicalPeople() == null ? 0 : currDto.getTechnicalPeople());
            currDto.setGkPeople(currDto.getGkPeople() == null ? 0 : currDto.getGkPeople());
            currDto.setChunJiSumPeople(chunJiSeasonSum == null ? 0 : chunJiSeasonSum);
            currDto.setChunQiuSumPeople((int)(currDto.getSumPeople()  == null ? 0 : currDto.getSumPeople()) + currDto.getChunJiSumPeople());
            if(oldYearList.size() == 0){
                currDto.setOldUndergraduatePeople(0);
                currDto.setOldTechnicalPeople(0);
                currDto.setOldGkPeople(0);
                currDto.setOldSumPeople(0.0);
            }else{
                RecruitSumStatisticsDetailDTO oldDto = oldYearList.get(i);
                Integer oldChunJiSeasonSum = oldChunJiSumList.get(i);
                currDto.setOldUndergraduatePeople(oldDto.getUndergraduatePeople() == null ? 0 : oldDto.getUndergraduatePeople());
                currDto.setOldTechnicalPeople(oldDto.getTechnicalPeople() == null ? 0 : oldDto.getTechnicalPeople());
                currDto.setOldGkPeople(oldDto.getGkPeople() == null ? 0 : oldDto.getGkPeople());
                currDto.setOldSumPeople(oldDto.getSumPeople() == null ? 0 : oldDto.getSumPeople());
                currDto.setOldChunJiSumPeople(oldChunJiSeasonSum == null ? 0 : oldChunJiSeasonSum);
            }
            if(currDto.getSumPeople() == 0 && currDto.getOldSumPeople() == 0 && currDto.getChunJiSumPeople() == 0){
                removeList.add(currDto);
            }
        }
    }
    /*总校排序*/
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

    /*计算小计*/
    private void DetailCalculateRate (List<RecruitSumStatisticsDetailDTO> recruitSumStatisticsDetailDTOList){
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
            dtoInfo.setUndergraduatePeople((dtoInfo.getUndergraduatePeople() == null ? 0 : dtoInfo.getUndergraduatePeople() + (dto.getUndergraduatePeople())));
            dtoInfo.setOldUndergraduatePeople((dtoInfo.getOldUndergraduatePeople() == null ? 0 : dtoInfo.getOldUndergraduatePeople() + (dto.getOldUndergraduatePeople())));
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
        recruitSumStatisticsDetailDTOList.add(0,dtoInfo);
    }
    /*春季的总招生人数是本季总和，秋季的总招生人数是全年总和*/
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
    /*计算统计页面招生率*/
    private void DetailCalculateRateInfo(RecruitSumStatisticsDetailDTO dto){
        DecimalFormat df = new DecimalFormat(".00");
        Double currSumPeople = 0.0;
        Double oldSumPeople = 0.0;
        if(dto.getChunJiSumPeople() > 0){
            currSumPeople = Double.valueOf(String.valueOf(dto.getChunQiuSumPeople()));
            oldSumPeople = Double.valueOf(dto.getOldChunJiSumPeople() == null ? 0 : dto.getOldChunJiSumPeople() +
                    dto.getOldGkPeople() +
                    dto.getOldTechnicalPeople() +
                    dto.getOldUndergraduatePeople());
        }else{
            currSumPeople = dto.getSumPeople();
            oldSumPeople = dto.getOldSumPeople() == null ? 0 : dto.getOldSumPeople();
        }
        if(currSumPeople == oldSumPeople){
            dto.setRecruitRate(0.0);
            dto.setRateType(0);
        }else if(oldSumPeople == 0){
            dto.setRecruitRate(100.0);
            dto.setRateType(0);
        }else if(currSumPeople == 0){
            dto.setRecruitRate(-100.0);
            dto.setRateType(1);
        }else{
            dto.setRecruitRate(Double.valueOf(df.format((currSumPeople - oldSumPeople)/oldSumPeople * 100)));
            dto.setRateType(0);
        }
        if(currSumPeople == 0){
            dto.setPlanAccomplishRate(0.0);
        }else if(dto.getRecruitCount() == 0){
            dto.setPlanAccomplishRate(100.0);
        }else{
            dto.setPlanAccomplishRate(Double.valueOf(df.format(currSumPeople/dto.getRecruitCount() * 100)));
        }
    }
    /*计算首页招生率*/
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
            DecimalFormat df = new DecimalFormat(".00");
            if(currDto.getSumPeople() > oldDto.getSumPeople()){
                currDto.setRecruitRate(Double.valueOf(df.format((currDto.getSumPeople() - oldDto.getSumPeople())/currDto.getSumPeople() * 100)));
                currDto.setRateType(0);
            }else if(currDto.getSumPeople() < oldDto.getSumPeople()){
                currDto.setRecruitRate(Double.valueOf(df.format((oldDto.getSumPeople() - currDto.getSumPeople())/oldDto.getSumPeople() * 100)));
                currDto.setRateType(1);
            }else{
                currDto.setRecruitRate(0.0);
                currDto.setRateType(0);
            }
            if(currDto.getUndergraduatePeople() > oldDto.getUndergraduatePeople()){
                currDto.setUndergraduateRate(Double.valueOf(df.format((currDto.getUndergraduatePeople() - oldDto.getUndergraduatePeople())/currDto.getUndergraduatePeople() * 100)));
                currDto.setUndergraduateRateType(0);
            }else if(currDto.getUndergraduatePeople() < oldDto.getUndergraduatePeople()){
                currDto.setUndergraduateRate(Double.valueOf(df.format((oldDto.getUndergraduatePeople() - currDto.getUndergraduatePeople())/oldDto.getUndergraduatePeople() * 100)));
                currDto.setUndergraduateRateType(1);
            }else{
                currDto.setUndergraduateRate(0.0);
                currDto.setUndergraduateRateType(0);
            }

            if(currDto.getTechnicalPeople() > oldDto.getTechnicalPeople()){
                currDto.setTechnicalRate(Double.valueOf(df.format((currDto.getTechnicalPeople() - oldDto.getTechnicalPeople())/currDto.getTechnicalPeople() * 100)));
                currDto.setTechnicalRateType(0);
            }else if(currDto.getTechnicalPeople() < oldDto.getTechnicalPeople()){
                currDto.setTechnicalRate(Double.valueOf(df.format((oldDto.getTechnicalPeople() - currDto.getTechnicalPeople())/oldDto.getTechnicalPeople() * 100)));
                currDto.setTechnicalRateType(1);
            }else{
                currDto.setTechnicalRate(0.0);
                currDto.setTechnicalRateType(0);
            }
            if(currDto.getGkPeople() > oldDto.getGkPeople()){
                currDto.setGkRate(Double.valueOf(df.format((currDto.getGkPeople() - oldDto.getGkPeople())/currDto.getGkPeople() * 100)));
                currDto.setGkRateType(0);
            }else if(currDto.getGkPeople() < oldDto.getGkPeople()){
                currDto.setGkRate(Double.valueOf(df.format((oldDto.getGkPeople() - currDto.getGkPeople())/oldDto.getGkPeople() * 100)));
                currDto.setGkRateType(1);
            }else{
                currDto.setGkRate(0.0);
                currDto.setGkRateType(0);
            }
        }
         recruitSumStatisticsDTOList.add(currDto);
         recruitSumStatisticsDTOList.add(oldDto);
    }

    public int pdf(ReqRecruitStatisticsDTO reqRecruitStatisticsDTO) throws IOException {
        try {
            String seasonName = reqRecruitStatisticsDTO.getRecruitSeason() == 0 ? "春季" : "秋季";
            String year = reqRecruitStatisticsDTO.getRecruitYear();
            String oldYear = reqRecruitStatisticsDTO.getOnRecruitYear();
            List<RecruitSumStatisticsDetailDTO> list = null;
            PdfPTable table = null;
            list = this.recruitStatisticsDetail(reqRecruitStatisticsDTO);
            if(reqRecruitStatisticsDTO.getRecruitSeason() == 0){
                table = new PdfPTable(11);//括号参数表示列
                int width[] = {80, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40};//设置每列宽度比例
                table.setWidths(width);
            }else {
                table = new PdfPTable(12);
                int width[] = {80, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40};
                table.setWidths(width);
            }
            if (list.size() < 0) {
                return 0;
            }
            //创建页面，左右上下边距
            Document document = new Document(PageSize.A4, 30, 30, 30, 20);
            //建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以 将文档写入到磁盘中
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:/pdfInfo.pdf"));

            //写入之前要打开文档
            document.open();

            BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
                    "UniGB-UCS2-H", false);
            Font font = new Font(bfChinese, 10, Font.NORMAL);

            table.setTotalWidth(1200);

            table.setHorizontalAlignment(Element.ALIGN_CENTER);//居中    

            BaseColor bgGray = new BaseColor(204, 204, 204);

            PdfPCell cellTitle = new PdfPCell(new Paragraph(year+"年"+seasonName+"春季招生计划", font));
            cellTitle.setBackgroundColor(bgGray);
            cellTitle.setColspan(reqRecruitStatisticsDTO.getRecruitSeason() == 0 ? 11 : 12);
            cellTitle.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cellTitle.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中 

            table.addCell(cellTitle);

            PdfPCell cellA1 = new PdfPCell(new Paragraph("分校", font));
            cellA1.setBackgroundColor(bgGray);
            cellA1.setRowspan(2);
            cellA1.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cellA1.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中 

            PdfPCell cellA2 = new PdfPCell(new Paragraph("计划招生人数", font));
            cellA2.setBackgroundColor(bgGray);
            cellA2.setRowspan(2);
            cellA2.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cellA2.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中 

            PdfPCell cellA3 = new PdfPCell(new Paragraph("国开本科", font));
            cellA3.setBackgroundColor(bgGray);
            cellA3.setColspan(2);
            cellA3.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cellA3.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中 

            PdfPCell cellA4 = new PdfPCell(new Paragraph("上开本科", font));
            cellA4.setBackgroundColor(bgGray);
            cellA4.setColspan(2);
            cellA4.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cellA4.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中 

            PdfPCell cellA5 = new PdfPCell(new Paragraph("上开专科", font));
            cellA5.setBackgroundColor(bgGray);
            cellA5.setColspan(2);
            cellA5.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cellA5.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中 

            PdfPCell cellA6 = new PdfPCell(new Paragraph("春季总计", font));
            cellA6.setBackgroundColor(bgGray);
            cellA6.setRowspan(2);
            cellA6.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cellA6.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中 

            PdfPCell cellA7 = new PdfPCell(new Paragraph("计划完成率", font));
            cellA7.setBackgroundColor(bgGray);
            cellA7.setRowspan(2);
            cellA7.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cellA7.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中 

            PdfPCell cellA8 = new PdfPCell(new Paragraph("同比增长率", font));
            cellA8.setBackgroundColor(bgGray);
            cellA8.setRowspan(2);
            cellA8.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cellA8.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中 

            PdfPCell cellA9 = new PdfPCell(new Paragraph("全年总计", font));
            cellA9.setBackgroundColor(bgGray);
            cellA9.setRowspan(2);
            cellA9.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cellA9.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中 

            table.addCell(cellA1);
            table.addCell(cellA2);
            table.addCell(cellA3);
            table.addCell(cellA4);
            table.addCell(cellA5);
            table.addCell(cellA6);
            if(reqRecruitStatisticsDTO.getRecruitSeason() == 1){
                table.addCell(cellA9);
            }
            table.addCell(cellA7);
            table.addCell(cellA8);

            PdfPCell cellB1 = new PdfPCell(new Paragraph("目前人数", font));
            cellB1.setBackgroundColor(bgGray);
            cellB1.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cellB1.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中 

            PdfPCell cellB2 = new PdfPCell(new Paragraph(oldYear+"年"+seasonName, font));
            cellB2.setBackgroundColor(bgGray);
            cellB2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellB2.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cellB3 = new PdfPCell(new Paragraph("目前人数", font));
            cellB3.setBackgroundColor(bgGray);
            cellB3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellB3.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cellB4 = new PdfPCell(new Paragraph(oldYear+"年"+seasonName, font));
            cellB4.setBackgroundColor(bgGray);
            cellB4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellB4.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cellB5 = new PdfPCell(new Paragraph("目前人数", font));
            cellB5.setBackgroundColor(bgGray);
            cellB5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellB5.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cellB6 = new PdfPCell(new Paragraph(oldYear+"年"+seasonName, font));
            cellB6.setBackgroundColor(bgGray);
            cellB6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellB6.setVerticalAlignment(Element.ALIGN_MIDDLE);

            table.addCell(cellB1);
            table.addCell(cellB2);
            table.addCell(cellB3);
            table.addCell(cellB4);
            table.addCell(cellB3);
            table.addCell(cellB4);
            for (int i = 0; i < list.size(); i++) {
                RecruitSumStatisticsDetailDTO dto = list.get(i);
                PdfPCell cellC1 = new PdfPCell(new Paragraph(dto.getName(), font));
                cellC1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellC1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                String recruitCount = dto.getRecruitCount() == null ? "0" : String.valueOf(dto.getRecruitCount()).substring(0,String.valueOf(dto.getRecruitCount()).indexOf("."));
                PdfPCell cellC2 = new PdfPCell(new Paragraph(recruitCount, font));
                cellC2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellC2.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell cellC3 = new PdfPCell(new Paragraph(String.valueOf(dto.getUndergraduatePeople() == null ? 0 : dto.getUndergraduatePeople()), font));
                cellC3.setBackgroundColor(BaseColor.ORANGE);
                cellC3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellC3.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell cellC4 = new PdfPCell(new Paragraph(String.valueOf(dto.getOldUndergraduatePeople() == null ? 0 : dto.getOldUndergraduatePeople()), font));
                cellC4.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellC4.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell cellC5 = new PdfPCell(new Paragraph(String.valueOf(dto.getTechnicalPeople() == null ? 0 : dto.getTechnicalPeople()), font));
                cellC5.setBackgroundColor(BaseColor.ORANGE);
                cellC5.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellC5.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell cellC6 = new PdfPCell(new Paragraph(String.valueOf(dto.getOldTechnicalPeople() == null ? 0 : dto.getOldTechnicalPeople()), font));
                cellC6.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellC6.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell cellC7 = new PdfPCell(new Paragraph(String.valueOf(dto.getGkPeople() == null ? 0 : dto.getGkPeople()), font));
                cellC7.setBackgroundColor(BaseColor.ORANGE);
                cellC7.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellC7.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell cellC8 = new PdfPCell(new Paragraph(String.valueOf(dto.getOldGkPeople() == null ? 0 : dto.getOldGkPeople()), font));
                cellC8.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellC8.setVerticalAlignment(Element.ALIGN_MIDDLE);
                String sumPeople = dto.getSumPeople() == null ? "0" : String.valueOf(dto.getSumPeople()).substring(0,String.valueOf(dto.getSumPeople()).indexOf("."));
                PdfPCell cellC9 = new PdfPCell(new Paragraph(sumPeople, font));
                cellC9.setBackgroundColor(BaseColor.ORANGE);
                cellC9.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellC9.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell cellC10 = new PdfPCell(new Paragraph(String.valueOf(dto.getPlanAccomplishRate() == null ? 0 : dto.getPlanAccomplishRate())+"%", font));
                cellC10.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellC10.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell cellC11 = new PdfPCell(new Paragraph(String.valueOf(dto.getRecruitRate() == null ? 0 : dto.getRecruitRate())+"%", font));
                cellC11.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellC11.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell cellC12 = new PdfPCell(new Paragraph(String.valueOf(dto.getChunJiSumPeople() == null ? 0 : dto.getChunJiSumPeople()), font));
                cellC12.setBackgroundColor(BaseColor.ORANGE);
                cellC12.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellC12.setVerticalAlignment(Element.ALIGN_MIDDLE);
                if("小计".equals(dto.getName())){
                    cellC1.setBackgroundColor(bgGray);
                    cellC2.setBackgroundColor(bgGray);
                    cellC3.setBackgroundColor(bgGray);
                    cellC4.setBackgroundColor(bgGray);
                    cellC5.setBackgroundColor(bgGray);
                    cellC6.setBackgroundColor(bgGray);
                    cellC7.setBackgroundColor(bgGray);
                    cellC8.setBackgroundColor(bgGray);
                    cellC9.setBackgroundColor(bgGray);
                    cellC10.setBackgroundColor(bgGray);
                    cellC11.setBackgroundColor(bgGray);
                    cellC12.setBackgroundColor(bgGray);
                }
                table.addCell(cellC1);
                table.addCell(cellC2);
                table.addCell(cellC3);
                table.addCell(cellC4);
                table.addCell(cellC5);
                table.addCell(cellC6);
                table.addCell(cellC7);
                table.addCell(cellC8);
                if(reqRecruitStatisticsDTO.getRecruitSeason() == 1){
                    table.addCell(cellC12);
                }
                table.addCell(cellC9);
                table.addCell(cellC10);
                table.addCell(cellC11);

            }
            //写入内容
            document.add(table);
            //关闭文档
            document.close();
        } catch (Exception e) {
            logger.info("导出pdf异常");
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
