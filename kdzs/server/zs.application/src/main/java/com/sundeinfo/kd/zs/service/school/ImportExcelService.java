package com.sundeinfo.kd.zs.service.school;

import com.sundeinfo.foundation.mvc.service.AbstractService;
import com.sundeinfo.kd.zs.dao.SchoolMapper;
import com.sundeinfo.kd.zs.dao.SchoolRecruitPlanMapper;
import com.sundeinfo.kd.zs.dao.UserMapper;
import com.sundeinfo.kd.zs.dao.model.*;
import com.sundeinfo.kd.zs.dto.apis.school.SchoolDTO;
import com.sundeinfo.kd.zs.utility.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImportExcelService extends AbstractService<ImportExcelService> {

    @Autowired
    SchoolRecruitSumService schoolRecruitSumService;

    @Resource
    SchoolMapper schoolMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    SchoolRecruitPlanMapper schoolRecruitPlanMapper;

    @Transactional
    public void importExcel(String recruitDate,Integer shoot) throws IOException, ParseException {
        //用流的方式先读取到你想要的excel的文件
        File file = new File("D:\\1.xls");
        //解析excel
        FileInputStream is = new FileInputStream(file);
        //获取整个excel
        HSSFWorkbook hb=new HSSFWorkbook(is);
        System.out.println(hb.getNumCellStyles());
        //获取第一个表单sheet
        HSSFSheet sheet=hb.getSheetAt(shoot);
        //获取第一行
        int firstrow=    sheet.getFirstRowNum();
        //获取最后一行
        int lastrow=    sheet.getLastRowNum();
        //远驰（经济管理学院）和远驰（人文学院）可以合起来统计，等于“远驰专修学院”
        Integer unCount = null;
        Integer teCount = null;
        //循环行数依次获取列数
        for (int i = 1; i < lastrow+1; i++) {
            //获取哪一行i
            Row row=sheet.getRow(i);
            if (row!=null) {
                //获取这一行的第一列
                int firstcell=    row.getFirstCellNum();
                //获取这一行的最后一列
                int lastcell=    row.getLastCellNum();
                //创建一个集合，用处将每一行的每一列数据都存入集合中
                List<String> list=new ArrayList<>();
                Cell cellSchool = row.getCell(1);
                String schoolName = cellSchool.toString();
                if("远驰（经济管理学院）".equals(cellSchool.toString())){
                    schoolName = "远驰专修学院";
                }else if("远驰（人文学院）".equals(cellSchool.toString())){
                    schoolName = "远驰专修学院";
                }else if("西南分校".equals(cellSchool.toString())){
                    schoolName = "西南进修学院分校";
                }else if("旅游局教学点".equals(cellSchool.toString())){
                    schoolName = "文化旅游学院";
                }else if("慧承文化进修学院教学点".equals(cellSchool.toString())){
                    schoolName = "慧承文化进修学院";
                }else if("贸易学校教学点".equals(cellSchool.toString())){
                    schoolName = "上海贸易学校教学点";
                }else if("上海沪东中华进修学院教学点".equals(cellSchool.toString())){
                    schoolName = "沪东中华进修学院教学点";
                }else if("石化工业学校培训中心教学点(筹)".equals(cellSchool.toString())){
                    schoolName = "石化工业学校培训中心教学点（筹）";
                }else if("企业家联合会".equals(cellSchool.toString())){
                    schoolName = "上海市企业管理进修学院教学点";
                }else if("老年教育学院".equals(cellSchool.toString())){
                    schoolName = "老年教育学院(暂不招生)";
                }else if("浦东西校".equals(cellSchool.toString())){
                    schoolName = "浦东西校(暂不招生)";
                }else if("泽达进修学院教学点(筹)".equals(cellSchool.toString())){
                    schoolName = "泽达进修学院教学点（筹）(暂不招生)";
                }
               /* Cell name = row.getCell(0);
                Cell phone = row.getCell(3);
                User user = new User();
                user.setName(name.toString());
                String phoneStr = phone.toString().substring(0,phone.toString().lastIndexOf("E"));
                String phoneS = phoneStr.substring(0,phoneStr.lastIndexOf("."))+ phoneStr.substring(phoneStr.lastIndexOf(".")+1,phoneStr.length());
                user.setUsername(phoneS);
                user.setPhone(phoneS);
                user.setPassword("96e79218965eb72c92a549dd5a330112");
                user.setCreatetime(new Date());
                user.setUpdatetime(new Date());
                logger.info(name.toString()+","+phoneS);
                int flag =userMapper.insertSelective(user);
                if(flag == 0){
                    continue;
                }*/
                Cell cellPlan = row.getCell(2);
                Cell cellGk =row.getCell(3);
                Cell cellUndergraduatep = row.getCell(3);
                Cell cellTechnical =row.getCell(5);
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(recruitDate);

                SchoolRecruitCounting record = new SchoolRecruitCounting();
                if(StringUtils.isBlank(cellTechnical.toString()) || cellTechnical.toString().lastIndexOf(".") < 0 ){
                    record.setTechnicalpeople(0);
                }else{
                    record.setTechnicalpeople(Integer.valueOf(cellTechnical.toString().substring(0,cellTechnical.toString().lastIndexOf("."))));
                }
                if(StringUtils.isBlank(cellUndergraduatep.toString()) || cellUndergraduatep.toString().lastIndexOf(".") < 0){
                    record.setUndergraduatepeople(0);
                }else{
                    record.setUndergraduatepeople(Integer.valueOf(cellUndergraduatep.toString().substring(0,cellUndergraduatep.toString().lastIndexOf("."))));
                }
//                if(StringUtils.isBlank(cellGk.toString()) || cellGk.toString().lastIndexOf(".") < 0){
//                    record.setGkpeople(0);
//                }else{
//                    record.setGkpeople(Integer.valueOf(cellGk.toString().substring(0,cellGk.toString().lastIndexOf("."))));
//                }
                record.setGkpeople(0);
                if("远驰（经济管理学院）".equals(cellSchool.toString()) && unCount == null && teCount == null){
                    unCount = record.getUndergraduatepeople();
                    teCount = record.getTechnicalpeople();
                    continue;
                }else if("远驰（经济管理学院）".equals(cellSchool.toString()) && unCount != null && teCount != null){
                    record.setUndergraduatepeople(record.getUndergraduatepeople()+unCount);
                    record.setTechnicalpeople(record.getTechnicalpeople()+teCount);
                }
                if("远驰（人文学院）".equals(cellSchool.toString()) && teCount == null && unCount == null){
                    teCount = record.getTechnicalpeople();
                    unCount = record.getUndergraduatepeople();
                    continue;
                }else if("远驰（人文学院）".equals(cellSchool.toString()) && teCount != null && unCount != null){
                    record.setTechnicalpeople(record.getTechnicalpeople()+teCount);
                    record.setUndergraduatepeople(record.getUndergraduatepeople()+unCount);
                }
                record.setCreatetime(date);
                SchoolExample example= new SchoolExample();
                SchoolExample.Criteria criteria = example.createCriteria();
                criteria.andNameEqualTo(schoolName);
                List<School> school = schoolMapper.selectByExample(example);
                record.setRecruitdate(date);
                record.setSeasonid(31);
                if(school == null || school.size() == 0){
                    logger.info("---------------------《"+schoolName+"》  不存在----------------------");
                    continue;
                }
                SchoolRecruitPlan recordPlan = new SchoolRecruitPlan();
                record.setSchoolid(school.get(0).getId());
                if(StringUtils.isBlank(cellPlan.toString())){
                    recordPlan.setRecruitcount(0);
                }else{
                    recordPlan.setRecruitcount(Integer.valueOf(cellPlan.toString().substring(0,cellPlan.toString().lastIndexOf("."))));
                }
                recordPlan.setRecruitplan(true);

                SchoolRecruitPlanExample examplePlan = new SchoolRecruitPlanExample();
                SchoolRecruitPlanExample.Criteria criteriaPlan = examplePlan.createCriteria();
                criteriaPlan.andSchoolidEqualTo(school.get(0).getId());
                criteriaPlan.andSeasonidEqualTo(34);
                //schoolRecruitPlanMapper.updateByExampleSelective(recordPlan,examplePlan);

                schoolRecruitSumService.saveSchoolRecruitSum(record);
                logger.info("学校："+schoolName+"; schoolId："+school.get(0).getId() +";招生计划："+cellPlan.toString() +";本科："+cellUndergraduatep.toString() +";专科："+cellTechnical.toString());
            }
        }

        is.close();
    }
}
