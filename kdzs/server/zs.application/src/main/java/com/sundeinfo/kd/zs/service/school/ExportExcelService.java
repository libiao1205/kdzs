package com.sundeinfo.kd.zs.service.school;

import com.sundeinfo.foundation.mvc.service.AbstractService;
import com.sundeinfo.kd.zs.dto.apis.school.RecruitSumStatisticsDetailDTO;
import com.sundeinfo.kd.zs.dto.apis.school.requestDto.ReqRecruitStatisticsDTO;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class ExportExcelService extends AbstractService<RecruitStatisticsService> {

    public int excel(List<RecruitSumStatisticsDetailDTO> list, ReqRecruitStatisticsDTO reqRecruitStatisticsDTO, HttpServletResponse response) throws IOException {
        try{
            if(list.size() < 0 ){
                return 0;
            }
            logger.info("************************开始拼装excel头部************************");
            String seasonName = reqRecruitStatisticsDTO.getRecruitSeason() == 0 ? "春季" : "秋季";
            String year = reqRecruitStatisticsDTO.getRecruitYear();
            String oldYear = reqRecruitStatisticsDTO.getOnRecruitYear();
            Integer recruitSeason = reqRecruitStatisticsDTO.getRecruitSeason();
            String currDate = new SimpleDateFormat("MM月dd日").format(new Date());
            String title = year + "年" + seasonName + "开放教育招生数据统计表（" + currDate+"）";
            //  创建一个工作簿
            HSSFWorkbook wb = new HSSFWorkbook();
            //  创建一个工作表
            HSSFSheet sheet = wb.createSheet("招生统计");
            //  创建字体
            HSSFFont font1 = wb.createFont();
            HSSFFont font2 = wb.createFont();
            HSSFFont font2B = wb.createFont();
            font1.setFontHeightInPoints((short) 14);
            font1.setFontName("仿宋_GB2312");
            font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
            font2.setFontHeightInPoints((short) 11);
            font2.setFontName("Times New Roman");
            font2B.setFontHeightInPoints((short) 11);
            font2B.setFontName("仿宋_GB2312");
            font2B.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
            //  创建单元格样式
            HSSFCellStyle css2 = wb.createCellStyle();
            HSSFCellStyle css3 = wb.createCellStyle();
            HSSFCellStyle headCss = wb.createCellStyle();
            HSSFCellStyle xiaoJiCss = wb.createCellStyle();
            HSSFCellStyle xiaoJiCssB = wb.createCellStyle();
            HSSFCellStyle titleCss = wb.createCellStyle();
            //设置背景色
            css3.setFillForegroundColor(HSSFColor.ROSE.index);
            css3.setFillPattern(CellStyle.SOLID_FOREGROUND);
            xiaoJiCss.setFillForegroundColor(HSSFColor.ROSE.index);
            xiaoJiCss.setFillPattern(CellStyle.SOLID_FOREGROUND);
            xiaoJiCssB.setFillForegroundColor(HSSFColor.ROSE.index);
            xiaoJiCssB.setFillPattern(CellStyle.SOLID_FOREGROUND);
            headCss.setFillForegroundColor(HSSFColor.WHITE.index);
            headCss.setFillPattern(CellStyle.SOLID_FOREGROUND);
            titleCss.setFillForegroundColor(HSSFColor.WHITE.index);
            titleCss.setFillPattern(CellStyle.SOLID_FOREGROUND);

            HSSFDataFormat df = wb.createDataFormat();
            //  水平居中
            headCss.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            titleCss.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            css2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            css3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            xiaoJiCss.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            xiaoJiCssB.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //垂直居中
            headCss.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            titleCss.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//            css2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//            css3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            xiaoJiCss.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            xiaoJiCssB.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            //设置边框
            headCss.setBorderBottom((short)1);
            headCss.setBorderTop((short)1);
            headCss.setBorderLeft((short)1);
            headCss.setBorderRight((short)1);
            titleCss.setBorderBottom((short)1);
            titleCss.setBorderTop((short)1);
            titleCss.setBorderLeft((short)1);
            titleCss.setBorderRight((short)1);
            css2.setBorderBottom((short)1);
            css2.setBorderTop((short)1);
            css2.setBorderLeft((short)1);
            css2.setBorderRight((short)1);
            css3.setBorderBottom((short)1);
            css3.setBorderTop((short)1);
            css3.setBorderLeft((short)1);
            css3.setBorderRight((short)1);
            xiaoJiCss.setBorderBottom((short)1);
            xiaoJiCss.setBorderTop((short)1);
            xiaoJiCss.setBorderLeft((short)1);
            xiaoJiCss.setBorderRight((short)1);
            xiaoJiCssB.setBorderBottom((short)1);
            xiaoJiCssB.setBorderTop((short)1);
            xiaoJiCssB.setBorderLeft((short)1);
            xiaoJiCssB.setBorderRight((short)1);

            headCss.setFont(font2B);
            headCss.setDataFormat(df.getFormat("#,##0.0"));
            css2.setFont(font2);
            css2.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
            titleCss.setFont(font1);
            titleCss.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
            css3.setFont(font2);
            css3.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
            xiaoJiCss.setFont(font2);
            xiaoJiCss.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
            xiaoJiCssB.setFont(font2B);
            xiaoJiCss.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
            //表头
            CellRangeAddress head = new CellRangeAddress(0,0,0,recruitSeason == 1 ? 13 : 11);//起始行,结束行,起始列,结束列
            //标题
            CellRangeAddress title1 = new CellRangeAddress(1,1,0,0);
            CellRangeAddress title2 = new CellRangeAddress(1,1,1,1);

            CellRangeAddress title3 = new CellRangeAddress(1,1,3,4);
            CellRangeAddress title4 = new CellRangeAddress(1,1,2,2);
            CellRangeAddress title5 = new CellRangeAddress(1,1,3,3);

            CellRangeAddress title6 = new CellRangeAddress(1,1,5,6);
            CellRangeAddress title7 = new CellRangeAddress(1,1,4,4);
            CellRangeAddress title8 = new CellRangeAddress(1,1,5,5);

            CellRangeAddress title9 = new CellRangeAddress(1,1,7,8);
            CellRangeAddress title10 = new CellRangeAddress(1,1,6,6);
            CellRangeAddress title11 = new CellRangeAddress(1,1,7,7);
            CellRangeAddress title12 = new CellRangeAddress(1,1,8,8);
            sheet.addMergedRegion(head);
            sheet.addMergedRegion(title1);
            sheet.addMergedRegion(title2);
            sheet.addMergedRegion(title3);
            sheet.addMergedRegion(title4);
            sheet.addMergedRegion(title5);
            sheet.addMergedRegion(title6);
            sheet.addMergedRegion(title7);
            sheet.addMergedRegion(title8);
            sheet.addMergedRegion(title9);
            sheet.addMergedRegion(title10);
            sheet.addMergedRegion(title11);
            sheet.addMergedRegion(title12);
            //春季表结构
            if(recruitSeason == 0){

                CellRangeAddress title13 = new CellRangeAddress(1,1,9,9);
                CellRangeAddress title14 = new CellRangeAddress(1,1,10,10);
                CellRangeAddress title15 = new CellRangeAddress(1,1,11,11);

                sheet.addMergedRegion(title13);
                sheet.addMergedRegion(title14);
                sheet.addMergedRegion(title15);
            }else{
                //秋季表结构
                CellRangeAddress title13 = new CellRangeAddress(1,1,9,9);
                CellRangeAddress title14 = new CellRangeAddress(1,1,10,10);
                CellRangeAddress title15 = new CellRangeAddress(1,1,11,11);
                CellRangeAddress title16 = new CellRangeAddress(1,1,12,12);
                CellRangeAddress title17 = new CellRangeAddress(1,1,13,13);
                sheet.addMergedRegion(title12);
                sheet.addMergedRegion(title13);
                sheet.addMergedRegion(title14);
                sheet.addMergedRegion(title15);
                sheet.addMergedRegion(title16);
                sheet.addMergedRegion(title17);
            }

            HSSFRow row1 = sheet.createRow(0);
            HSSFCell headCell = row1.createCell(0);
            headCell.setCellValue(title);
            headCell.setCellStyle(titleCss);
            HSSFRow row2 = sheet.createRow(1);
            HSSFCell title1Cel0 = row2.createCell(0);
            title1Cel0.setCellValue(new HSSFRichTextString("序号"));
            title1Cel0.setCellStyle(headCss);
            HSSFCell title1Cell = row2.createCell(1);
            title1Cell.setCellValue(new HSSFRichTextString("分校"));
            title1Cell.setCellStyle(headCss);
            HSSFCell title2Cell = row2.createCell(2);
            title2Cell.setCellValue(new HSSFRichTextString("招生计划人数"));
            title2Cell.setCellStyle(headCss);
            HSSFCell title3Cell = row2.createCell(3);
            title3Cell.setCellValue(new HSSFRichTextString("国开本科"));
            title3Cell.setCellStyle(headCss);
            HSSFCell title3CellInfo = row2.createCell(4);
            title3CellInfo.setCellStyle(headCss);
            HSSFCell title6Cell = row2.createCell(5);
            title6Cell.setCellValue(new HSSFRichTextString("上开本科"));
            title6Cell.setCellStyle(headCss);
            HSSFCell title5CellInfo = row2.createCell(6);
            title5CellInfo.setCellStyle(headCss);
            HSSFCell title9Cell = row2.createCell(7);
            title9Cell.setCellValue(new HSSFRichTextString("上开专科"));
            title9Cell.setCellStyle(headCss);
            HSSFCell title7CellInfo = row2.createCell(8);
            title7CellInfo.setCellStyle(headCss);
            if(recruitSeason == 0){
                HSSFCell title12Cell = row2.createCell(9);
                title12Cell.setCellValue(new HSSFRichTextString("合计"));
                title12Cell.setCellStyle(headCss);
                HSSFCell title13Cell = row2.createCell(10);
                title13Cell.setCellValue(new HSSFRichTextString("计划完成率"));
                title13Cell.setCellStyle(headCss);
                HSSFCell title14Cell = row2.createCell(11);
                title14Cell.setCellValue(new HSSFRichTextString("同比增长率"));
                title14Cell.setCellStyle(headCss);
            }else{
                HSSFCell title12Cell = row2.createCell(9);
                title12Cell.setCellValue(new HSSFRichTextString("秋季合计"));
                title12Cell.setCellStyle(headCss);

                HSSFCell title13Cell = row2.createCell(10);
                title13Cell.setCellValue(new HSSFRichTextString("春季合计"));
                title13Cell.setCellStyle(headCss);

                HSSFCell title14Cell = row2.createCell(11);
                title14Cell.setCellValue(new HSSFRichTextString("全年合计"));
                title14Cell.setCellStyle(headCss);
                HSSFCell title15Cell = row2.createCell(12);
                title15Cell.setCellValue(new HSSFRichTextString("计划完成率"));
                title15Cell.setCellStyle(headCss);
                HSSFCell title16Cell = row2.createCell(13);
                title16Cell.setCellValue(new HSSFRichTextString("同比增长率"));
                title16Cell.setCellStyle(headCss);
            }


           /* HSSFRow row3 = sheet.createRow(2);
            HSSFCell title1CellInfo = row3.createCell(1);
            title1CellInfo.setCellStyle(titleCss);
            HSSFCell title4Cell = row3.createCell(2);
            HSSFCell title5Cell = row3.createCell(3);
            title4Cell.setCellValue(new HSSFRichTextString("目前人数"));
            title4Cell.setCellStyle(titleCss);
            title5Cell.setCellValue(new HSSFRichTextString(oldYear + "年" + seasonName));
            title5Cell.setCellStyle(titleCss);

            HSSFCell title7Cell = row3.createCell(4);
            HSSFCell title8Cell = row3.createCell(5);
            title7Cell.setCellValue(new HSSFRichTextString("目前人数"));
            title7Cell.setCellStyle(titleCss);
            title8Cell.setCellValue(new HSSFRichTextString(oldYear + "年" + seasonName));
            title8Cell.setCellStyle(titleCss);

            HSSFCell title10Cell = row3.createCell(6);
            HSSFCell title11Cell = row3.createCell(7);
            title10Cell.setCellValue(new HSSFRichTextString("目前人数"));
            title10Cell.setCellStyle(titleCss);
            title11Cell.setCellValue(new HSSFRichTextString(oldYear + "年" + seasonName));
            title11Cell.setCellStyle(titleCss);
            HSSFCell title8CellInfo = row3.createCell(8);
            title8CellInfo.setCellStyle(titleCss);
            HSSFCell title9CellInfo = row3.createCell(9);
            title9CellInfo.setCellStyle(titleCss);
            HSSFCell title10CellInfo = row3.createCell(10);
            title10CellInfo.setCellStyle(titleCss);

            if(recruitSeason == 1){
                HSSFCell title11CellInfo = row3.createCell(11);
                title11CellInfo.setCellStyle(titleCss);
                HSSFCell title12CellInfo = row3.createCell(12);
                title12CellInfo.setCellStyle(titleCss);
            }*/
            logger.info("************************拼装excel头部完成************************");
            //添加数据
            this.addExcelSource(list,sheet,css2,css3,headCss,xiaoJiCssB,xiaoJiCss,recruitSeason);
            //下载excel
            this.downLoadExcel(wb,response,title+".xls");
        } catch (Exception e){
            logger.info("导出excel异常");
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    /*向excel中添加数据*/
    private void addExcelSource(List<RecruitSumStatisticsDetailDTO> list,HSSFSheet sheet,
                                HSSFCellStyle css2,HSSFCellStyle css3,HSSFCellStyle headCss,HSSFCellStyle xiaoJiCssB,
                                HSSFCellStyle xiaoJiCss,Integer recruitSeason){
        logger.info("************************开始向excel中添加数据************************");
        //计算总计
        RecruitSumStatisticsDetailDTO recruitDTO = new RecruitSumStatisticsDetailDTO();
        recruitDTO.setName("总计");
        list.forEach(dto ->{
            if("小计".equals(dto.getName())){
                recruitDTO.setRecruitCount((recruitDTO.getRecruitCount() == null ? 0 : recruitDTO.getRecruitCount()) + (dto.getRecruitCount() == null ? 0: dto.getRecruitCount()));
                recruitDTO.setSumPeople((recruitDTO.getSumPeople() == null ? 0 : recruitDTO.getSumPeople()) + (dto.getSumPeople() == null ? 0 : dto.getSumPeople()));
                recruitDTO.setChunJiSumPeople((recruitDTO.getChunJiSumPeople() == null ? 0 : recruitDTO.getChunJiSumPeople()) + (dto.getChunJiSumPeople() == null ? 0 : dto.getChunJiSumPeople()));
                recruitDTO.setChunQiuSumPeople((recruitDTO.getChunQiuSumPeople() == null ? 0 : recruitDTO.getChunQiuSumPeople()) + (dto.getChunQiuSumPeople() == null ? 0 : dto.getChunQiuSumPeople()));
                recruitDTO.setGkPeople((recruitDTO.getGkPeople() == null ? 0 : recruitDTO.getGkPeople()) + (dto.getGkPeople() == null ? 0 : dto.getGkPeople()));
                recruitDTO.setUndergraduatePeople((recruitDTO.getUndergraduatePeople() == null ? 0 : recruitDTO.getUndergraduatePeople()) + (dto.getUndergraduatePeople() == null ? 0 : dto.getUndergraduatePeople()));
                recruitDTO.setTechnicalPeople((recruitDTO.getTechnicalPeople() == null ? 0 : recruitDTO.getTechnicalPeople()) + (dto.getTechnicalPeople() == null ? 0 : dto.getTechnicalPeople()));
                recruitDTO.setOldGkPeople((recruitDTO.getOldGkPeople() == null ? 0 : recruitDTO.getOldGkPeople()) + (dto.getOldGkPeople() == null ? 0 : dto.getOldGkPeople()));
                recruitDTO.setOldUndergraduatePeople((recruitDTO.getOldUndergraduatePeople() == null ? 0 : recruitDTO.getOldUndergraduatePeople()) + (dto.getOldUndergraduatePeople() == null ? 0 : dto.getOldUndergraduatePeople()));
                recruitDTO.setOldTechnicalPeople((recruitDTO.getOldTechnicalPeople() == null ? 0 :recruitDTO.getOldTechnicalPeople()) + (dto.getOldTechnicalPeople() == null ? 0 : dto.getOldTechnicalPeople()));

            }
        });
        DecimalFormat df = new DecimalFormat(".00");
        int oldSum = (recruitDTO.getOldGkPeople() + recruitDTO.getOldUndergraduatePeople() + recruitDTO.getOldTechnicalPeople());
        if(oldSum == 0){
            recruitDTO.setRecruitRate(100.0);
        }else{
            recruitDTO.setRecruitRate(Double.valueOf(df.format((recruitDTO.getSumPeople() - oldSum)/oldSum*100)));
        }
        if(recruitDTO.getChunQiuSumPeople() == 0){
            recruitDTO.setPlanAccomplishRate(Double.valueOf(df.format(recruitDTO.getSumPeople()/recruitDTO.getRecruitCount()*100)));
        }else{
            recruitDTO.setPlanAccomplishRate(Double.valueOf(df.format(recruitDTO.getChunQiuSumPeople()/recruitDTO.getRecruitCount()*100)));
        }
        list.add(recruitDTO);
        //  创建行
        int subNum = 0;
        for (int i = 0; i < list.size(); i++) {
            RecruitSumStatisticsDetailDTO dto = list.get(i);
            CellRangeAddress mergeCell1 = new CellRangeAddress(i+2,i+2,3,4);
            CellRangeAddress mergeCell2 = new CellRangeAddress(i+2,i+2,5,6);
            CellRangeAddress mergeCell3 = new CellRangeAddress(i+2,i+2,7,8);
            sheet.addMergedRegion(mergeCell1);
            sheet.addMergedRegion(mergeCell2);
            sheet.addMergedRegion(mergeCell3);
            HSSFRow row = sheet.createRow(i+2);
            HSSFCell cell0 = row.createCell(0);
            sheet.setColumnWidth(0,dto.getName().getBytes().length * 256);


            HSSFCell cell1 = row.createCell(1);
            sheet.setColumnWidth(1,dto.getName().getBytes().length * 256);
            cell1.setCellValue(new HSSFRichTextString(dto.getName()));
            cell1.setCellStyle(headCss);
            HSSFCell cell2 = row.createCell(2);
            sheet.setColumnWidth(2,25 * 256);
            String recruitCount = dto.getRecruitCount() == null ? "0" : String.valueOf(dto.getRecruitCount()).substring(0,String.valueOf(dto.getRecruitCount()).indexOf("."));
            cell2.setCellValue(new HSSFRichTextString(recruitCount));

            HSSFCell cell7 = row.createCell(3);
            sheet.setColumnWidth(6,15 * 256);
            cell7.setCellValue(new HSSFRichTextString(String.valueOf(dto.getGkPeople() == null ? 0 : dto.getGkPeople())));
            cell7.setCellStyle(css2);

            HSSFCell cell3 = row.createCell(5);
            sheet.setColumnWidth(2,15 * 256);
            cell3.setCellValue(new HSSFRichTextString(String.valueOf(dto.getUndergraduatePeople() == null ? 0 : dto.getUndergraduatePeople())));
            cell3.setCellStyle(css2);

            HSSFCell cell4 = row.createCell(4);
            /*sheet.setColumnWidth(3,15 * 256);
            cell4.setCellValue(new HSSFRichTextString(String.valueOf(dto.getOldUndergraduatePeople() == null ? 0 : dto.getOldUndergraduatePeople())));*/

            HSSFCell cell5 = row.createCell(7);
            sheet.setColumnWidth(4,15 * 256);
            cell5.setCellValue(new HSSFRichTextString(String.valueOf(dto.getTechnicalPeople() == null ? 0 : dto.getTechnicalPeople())));
            cell5.setCellStyle(css2);

            HSSFCell cell6 = row.createCell(6);
            /*sheet.setColumnWidth(5,15 * 256);
            cell6.setCellValue(new HSSFRichTextString(String.valueOf(dto.getOldTechnicalPeople() == null ? 0 : dto.getOldTechnicalPeople())));*/



            HSSFCell cell8 = row.createCell(8);
            /*sheet.setColumnWidth(7,15 * 256);
            cell8.setCellValue(new HSSFRichTextString(String.valueOf(dto.getOldGkPeople() == null ? 0 : dto.getOldGkPeople())));*/

            HSSFCell cell9 = row.createCell(9);
            HSSFCell cell10 = row.createCell(10);
            HSSFCell cell11 = row.createCell(11);
            if(recruitSeason == 0){
                sheet.setColumnWidth(9,10 * 256);
                cell9.setCellValue(new HSSFRichTextString(String.valueOf((int)Math.floor(dto.getSumPeople() == null ? 0 : dto.getSumPeople()))));
                cell9.setCellStyle(css3);

                sheet.setColumnWidth(10,20 * 256);
                cell10.setCellValue(new HSSFRichTextString(String.valueOf(dto.getPlanAccomplishRate() == null ? 0 : dto.getPlanAccomplishRate())+"%"));
                cell10.setCellStyle(css2);
                sheet.setColumnWidth(11,20 * 256);
                cell11.setCellValue(new HSSFRichTextString(dto.getRecruitRate() == null ? "" : (String.valueOf(dto.getRecruitRate())+"%")));
            }else{
                sheet.setColumnWidth(9,10 * 256);
                String sumPeople = dto.getSumPeople() == null ? "0" : String.valueOf(dto.getSumPeople()).substring(0,String.valueOf(dto.getSumPeople()).indexOf("."));
                cell9.setCellValue(new HSSFRichTextString(sumPeople));
                cell9.setCellStyle(css3);

                sheet.setColumnWidth(10,10 * 256);
                cell10.setCellValue(new HSSFRichTextString(String.valueOf(dto.getChunJiSumPeople() == null ? 0 : dto.getChunJiSumPeople())));
                cell10.setCellStyle(css3);

                sheet.setColumnWidth(11,10 * 256);
                cell11.setCellValue(new HSSFRichTextString(String.valueOf((int)Math.floor(dto.getChunQiuSumPeople()))));
                cell11.setCellStyle(css2);

                HSSFCell cell12 = row.createCell(12);
                sheet.setColumnWidth(12,20 * 256);
                cell12.setCellValue(new HSSFRichTextString(String.valueOf(dto.getPlanAccomplishRate() == null ? 0 : dto.getPlanAccomplishRate())+"%"));

                HSSFCell cell13 = row.createCell(13);
                sheet.setColumnWidth(13,20 * 256);
                cell13.setCellValue(new HSSFRichTextString(dto.getRecruitRate() == null ? "" : (String.valueOf(dto.getRecruitRate())+"%")));
                if("小计".equals(dto.getName()) ||"总计".equals(dto.getName())){
                    subNum +=1;
                    cell12.setCellStyle( xiaoJiCss);
                    cell13.setCellStyle( xiaoJiCss);
                }else{
                    cell0.setCellValue(new HSSFRichTextString(String.valueOf(i + 1 - subNum)));
                    cell12.setCellStyle(css2);
                    cell13.setCellStyle(css2);
                }


            }
            if("小计".equals(dto.getName()) || "总计".equals(dto.getName())){
                cell0.setCellStyle(xiaoJiCss);
                cell1.setCellStyle(xiaoJiCssB);
                cell2.setCellStyle(xiaoJiCss);
                cell3.setCellStyle(xiaoJiCss);
                cell4.setCellStyle(xiaoJiCss);
                cell5.setCellStyle(xiaoJiCss);
                cell6.setCellStyle(xiaoJiCss);
                cell7.setCellStyle(xiaoJiCss);
                cell8.setCellStyle(xiaoJiCss);
                cell9.setCellStyle(xiaoJiCss);
                cell10.setCellStyle(xiaoJiCss);
                cell11.setCellStyle(xiaoJiCss);

            }else{
                cell0.setCellStyle(headCss);
                cell1.setCellStyle(headCss);
                cell2.setCellStyle(css2);
                cell4.setCellStyle(css2);
                cell6.setCellStyle(css2);
                cell8.setCellStyle(css2);
                cell11.setCellStyle(css2);
            }
        }
        logger.info("************************向excel中添加数据完成************************");
    }

    /*下载excel*/
    private void downLoadExcel(HSSFWorkbook wb,HttpServletResponse response,String fileName) throws IOException {
        logger.info("************************开始下载excel************************");
        response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName, "utf-8"));
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        wb.write(response.getOutputStream());
        wb.close();
        logger.info("************************下载excel完成************************");
    }

}
