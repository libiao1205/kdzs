package com.sundeinfo.kd.zs.utility;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * 导出复杂样式excel
 * @author libiao
 * @date 2021/7/22
 */
public class ExcelUtil {

    private HSSFWorkbook wb;

    private HSSFSheet sheet;

    /**
     * 用于被自定义颜色替换（ 大于等于8 && 小于64,不可重复）
     */
    private short replaceColor = 8;

    public ExcelUtil(){
        // 创建一个工作簿
        wb = new HSSFWorkbook();
        // 创建一个工作表
        sheet = wb.createSheet();
    }

    public static void main(String[] args) {
        ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.exportExcel("MQ配置约定");
    }

    public void exportExcel(String fileName){

        // 设置标题字体
        HSSFFont title1Font = this.setFont((short) 18, this.getBlack(), true, "仿宋_GB2312");

        // 设置标题单元格样式
        HSSFCellStyle style = this.setCellStyle(this.getWhite(), 'c', 'c', true, this.getGray(), title1Font);

        // 设置标题字体
        HSSFFont title2Font = this.setFont((short) 12, this.getWhite(), false, "仿宋_GB2312");

        // 设置标题单元格样式
        HSSFCellStyle style2 = this.setCellStyle(this.getBlue(), 'c', 'c', true, this.getWhite(), title2Font);

        // 设置内容字体
        HSSFFont contentFont = this.setFont((short) 12, this.getBlack(), false, "仿宋_GB2312");

        // 设置内容单元格样式
        HSSFCellStyle style3 = this.setCellStyle(this.getBuff(), 'c', 'c', true, this.getGray(), contentFont);

        // 设置跨单元格
        this.setCellRangeAddress(0, 0, "A", "J");
        this.setCellRangeAddress(2, 3, "A", "B");
        this.setCellRangeAddress(2, 3, "C", "D");
        this.setCellRangeAddress(2, 3, "E", "F");
        this.setCellRangeAddress(2, 3, "G", "H");
        this.setCellRangeAddress(2, 2, "I", "J");
        this.setCellRangeAddress(3, 3, "J", "J");
        this.setCellRangeAddress(3, 4, "I", "I");
        this.setCellRangeAddress(4, 4, "A", "A");
        this.setCellRangeAddress(4, 4, "B", "B");
        this.setCellRangeAddress(4, 4, "C", "C");
        this.setCellRangeAddress(4, 4, "D", "D");
        this.setCellRangeAddress(4, 4, "E", "E");
        this.setCellRangeAddress(4, 4, "F", "F");
        this.setCellRangeAddress(4, 4, "G", "G");
        this.setCellRangeAddress(4, 4, "H", "H");
        this.setCellRangeAddress(4, 4, "J", "J");

        // 为了防止合并后的单元格边框线不显示，所以即便是为空的单元格也要进行赋值
        List<Map<String,String>> list = new ArrayList();
        list.add(this.setMap("MQ配置管理", "A"));
        list.add(this.setMap("", "B"));
        list.add(this.setMap( "", "C"));
        list.add(this.setMap("", "D"));
        list.add(this.setMap("", "E"));
        list.add(this.setMap("", "F"));
        list.add(this.setMap("", "G"));
        list.add(this.setMap("", "H"));
        list.add(this.setMap("", "I"));
        list.add(this.setMap("", "J"));
        this.titlesCustom(list, 0, style);

        list.clear();
        list.add(this.setMap("业务场景", "A"));
        list.add(this.setMap("", "B"));
        list.add(this.setMap("业务双方", "C"));
        list.add(this.setMap("", "D"));
        list.add(this.setMap("使用请求", "E"));
        list.add(this.setMap("", "F"));
        list.add(this.setMap("Channel", "G"));
        list.add(this.setMap("", "H"));
        list.add(this.setMap("DEV环境", "I"));
        list.add(this.setMap("", "J"));
        this.titlesCustom(list, 2, style2);

        list.clear();
        list.add(this.setMap("", "A"));
        list.add(this.setMap("", "B"));
        list.add(this.setMap("", "C"));
        list.add(this.setMap("", "D"));
        list.add(this.setMap("", "E"));
        list.add(this.setMap("", "F"));
        list.add(this.setMap("", "G"));
        list.add(this.setMap("", "H"));
        list.add(this.setMap("Topic", "I"));
        list.add(this.setMap("Producer", "J"));
        this.titlesCustom(list, 3, style2);

        list.clear();
        list.add(this.setMap("业务编码", "A"));
        list.add(this.setMap("业务场景内容", "B"));
        list.add(this.setMap("发送方", "C"));
        list.add(this.setMap("接收方", "D"));
        list.add(this.setMap("有序", "E"));
        list.add(this.setMap("事务", "F"));
        list.add(this.setMap("Output生产方", "G"));
        list.add(this.setMap("Input消费方", "H"));
        list.add(this.setMap("Group", "J"));
        this.titlesCustom(list, 4, style2);

        /*
        String[] titles = {
                "MQ配置管理",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
        };
        this.setTitle(titles,0,style);

        String[] titles2 = {
                "业务场景",
                "",
                "业务双方",
                "",
                "使用请求",
                "",
                "Channel",
                "",
                "DEV环境",
                "",
        };
        this.setTitle(titles2,2,style2);

        String[] titles3 = {
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "Topic",
                "Producer",
        };
        this.setTitle(titles3,3,style2);

        String[] titles4 = {
                "业务编码",
                "业务场景内容",
                "发送方",
                "接收方",
                "有序",
                "事务",
                "Output生产方",
                "Input消费方",
                "",
                "Group",
        };
        this.setTitle(titles4,4,style2);*/

        // 插入表格内容
        this.setContent(this.getData(), 6, style3);

        // 导出到本地
        this.downFile(fileName);

    }

    /**
     * 设置表格内容
     * @param data 内容数据包
     * @param startRow 从第几行开始添加
     * @param style 样式
     * @return void
     * @author libiao
     */
    public void setContent(List<String[]> data, int startRow, HSSFCellStyle style){
        if(Objects.isNull(data)){
            return;
        }
        // 循环行
        for(int i = 0; i < data.size(); i++){
            String[] titles = data.get(i);
            if(Objects.isNull(data)){
                continue;
            }
            HSSFRow row = sheet.createRow(startRow + i);
            // 循环列
            for(int j = 0; j < titles.length; j++){
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(new HSSFRichTextString(titles[j]));
                cell.setCellStyle(style);
            }
        }
    }

    /**
     * 设置标题，按照顺序插入
     * @param titles 标题
     * @param startRow 在第几行插入数据
     * @param style 样式
     * @return void
     * @author libiao
     */
    public void setTitle(String[] titles, int startRow, HSSFCellStyle style){

        if(Objects.isNull(titles)){
            return;
        }
        HSSFRow row = sheet.createRow(startRow);
        for(int j = 0; j < titles.length; j++){
            HSSFCell cell = row.createCell(j);
            cell.setCellValue(new HSSFRichTextString(titles[j]));
            cell.setCellStyle(style);
        }
    }

    /**
     * 设置标题，按照指定下标插入
     * @param list 标题
     * @param startRow 在第几行插入数据
     * @param style 样式
     * @return void
     * @author libiao
     */
    public void titlesCustom(List<Map<String,String>> list, int startRow, HSSFCellStyle style){

        if(Objects.isNull(list)){
            return;
        }
        HSSFRow row = sheet.createRow(startRow);
        for(int j = 0; j < list.size(); j++){
            Map<String,String> map = list.get(j);
            String indexStr = map.get("indexStr");
            HSSFCell cell = null;
            if(StringUtils.isEmpty(indexStr)){
                cell = row.createCell(j);
            }else{
                int index = CellReference.convertColStringToIndex(indexStr);
                cell = row.createCell(index);
            }
            cell.setCellValue(new HSSFRichTextString(map.get("value")));
            cell.setCellStyle(style);
        }
    }

    /**
     * 设置横向纵向跨单元格 （例如：A列和B列合并，下标第0行和第1行合并）
     * @param firstRow 开始行
     * @param lastRow 结束行
     * @param firstCol 开始列
     * @param lastCol 结束列
     * @return void
     * @author libiao
     */
    public void setCellRangeAddress(int firstRow, int lastRow, String firstCol, String lastCol){

        int firstColIndex = CellReference.convertColStringToIndex(firstCol);
        int lastColIndex = CellReference.convertColStringToIndex(lastCol);
        CellRangeAddress cellRangeAddress = new CellRangeAddress(firstRow, lastRow, firstColIndex, lastColIndex);
        sheet.addMergedRegion(cellRangeAddress);
    }

    /**
     * 设置字体样式
     * @param fontSize 字体大小
     * @param color 字体颜色rgb
     * @param isBold 是否加粗
     * @param fontName 字体名称（宋体,简体等）
     * @return org.apache.poi.hssf.usermodel.HSSFFont
     * @author libiao
     */
    public HSSFFont setFont(short fontSize,byte[] color, boolean isBold, String fontName){

        // 创建字体
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints(fontSize);
        font.setFontName(fontName);

        if(!Objects.isNull(color) && color.length > 2){
            replaceColor++;
            font.setColor(replaceColor);
            HSSFPalette palette = wb.getCustomPalette();
            palette.setColorAtIndex(replaceColor, color[0], color[1], color[2]);
        }

        if(isBold){
            // 粗体显示
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        }
        return font;
    }

    /**
     * 设置单元格样式
     * @param bgColor 背景颜色rgb
     * @param align 水平排列方式（left,right,center）
     * @param vertical 垂直排列方式（top,bottom,center）
     * @param isBorder 是否显示边框线
     * @param borderColor 边框颜色rgb
     * @param font 字体
     * @return org.apache.poi.hssf.usermodel.HSSFCellStyle
     * @author libiao
     */
    public HSSFCellStyle setCellStyle(byte[] bgColor, char align, char vertical, boolean isBorder, byte[] borderColor, HSSFFont font){

        //生成单元格样式
        HSSFCellStyle style = wb.createCellStyle();
        if(!Objects.isNull(bgColor) && bgColor.length > 2){
            replaceColor++;
            //设置背景颜色
            style.setFillForegroundColor(replaceColor);
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
            //拿到palette颜色板
            HSSFPalette palette = wb.getCustomPalette();
            //这个是重点，具体的就是把之前的颜色 HSSFColor.LIME.index
            //替换为  RGB(51,204,204) 宝石蓝这种颜色
            //你可以改为 RGB(0,255,127)
            palette.setColorAtIndex(replaceColor, bgColor[0], bgColor[1], bgColor[2]);
        }

        // 水平排版
        switch (align){
            case 'l':
                style.setAlignment(CellStyle.ALIGN_LEFT);
                break;
            case 'r':
                style.setAlignment(CellStyle.ALIGN_RIGHT);
                break;
            default:
                style.setAlignment(CellStyle.ALIGN_CENTER);
        }
        // 垂直排版
        switch (vertical){
            case 't':
                style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
                break;
            case 'b':
                style.setVerticalAlignment(CellStyle.VERTICAL_BOTTOM);
                break;
            default:
                style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        }
        // 是否显示边框线
        if(isBorder){
            style.setBorderBottom((short)1);
            style.setBorderTop((short)1);
            style.setBorderLeft((short)1);
            style.setBorderRight((short)1);
            // 设置边框颜色
            if(!Objects.isNull(borderColor) && borderColor.length > 2){
                replaceColor++;
                style.setLeftBorderColor(replaceColor);
                style.setRightBorderColor(replaceColor);
                style.setTopBorderColor(replaceColor);
                style.setBottomBorderColor(replaceColor);
                HSSFPalette palette = wb.getCustomPalette();
                palette.setColorAtIndex(replaceColor, borderColor[0], borderColor[1], borderColor[2]);
            }
        }
        // 单元格内容全都定义成文本格式
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));

        // 设置单元格字体
        style.setFont(font);

        return style;
    }

    /**
     * 黑色
     */
    public byte[] getBlack(){
        return new byte[]{(byte)0, (byte)0, (byte)0};
    }

    /**
     * 白色
     */
    public byte[] getWhite(){
        return new byte[]{(byte)255, (byte)255, (byte)255};
    }

    /**
     * 浅黄色
     */
    public byte[] getBuff(){
        return new byte[]{(byte)255, (byte)242, (byte)204};
    }

    /**
     * 灰色
     */
    public byte[] getGray(){
        return new byte[]{(byte)220, (byte)220, (byte)220};
    }

    /**
     * 蓝色
     */
    public byte[] getBlue(){
        return new byte[]{(byte)0, (byte)112, (byte)192};
    }


    public Map<String,String> setMap(String value, String indexStr){
        Map<String,String> map = new HashMap<>();
        map.put("value", value);
        map.put("indexStr", indexStr);
        return map;
    }

    /**
     * 假数据包
     */
    private List<String[]> getData(){
        List<String[]> data = new ArrayList<>();
        String[] str = {
                "BOSS",
                "[新契约]推送IO和BP数据到[资金前置]",
                "新契约",
                "资金前置",
                "Y",
                "Y",
                "bookkeeping-nb-out",
                "123",
                "456",
                "789",
        };
        data.add(str);
        data.add(str);
        data.add(str);
        return data;
    }

    private void downFile(String fileName){
        FileOutputStream output= null;
        try {
            output = new FileOutputStream("D:\\" + fileName + ".xlsx");
            // 写入磁盘
            wb.write(output);
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
