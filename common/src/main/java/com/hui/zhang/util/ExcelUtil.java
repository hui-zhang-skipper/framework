package com.hui.zhang.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Excel 帮助类
 *
 * @param
 */
public class ExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 获取excel 表格信息
     *
     * @param sheetName
     * @param title
     * @param list
     * @param <T>
     * @return
     */
    public static <T> HSSFWorkbook getWorkBook(String sheetName, String[] title, List<T> list) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        if (title == null || title.length == 0) {
            throw new RuntimeException("表头信息为空");
        }
        if (CollectionUtils.isEmpty(list)) {
            return workbook;
        }
        HSSFSheet sheet = workbook.createSheet(sheetName);
        HSSFRow row = sheet.createRow(0);
        makeSheetHeader(workbook, title, row);
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            makeRowData(row, list.get(i));
        }
        return workbook;
    }

    /**
     * 设置表头信息
     *
     * @param workbook
     * @param title
     * @param row
     */
    private static void makeSheetHeader(HSSFWorkbook workbook, String[] title, HSSFRow row) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.SKY_BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        HSSFCell cell;
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(title[i]);
        }
    }

    /**
     * 获取行数据
     *
     * @param row
     * @param t
     * @param <T>
     */
    private static <T> void makeRowData(HSSFRow row, T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String value = "";
            try {
                field.setAccessible(true);
                value = field.get(t).toString();
            } catch (Exception e) {
                logger.error("EXCEL 帮助类 反射 获取对象属性值失败", e);
            }
            row.createCell(i).setCellValue(value);
        }

    }

    public static void main(String[] args) {
        String[] title = {"城市", "业务类型", "车型", "费用类型", "工作日",
                "乘客端", "司机端", "费用类型", "休息日", "乘客端", "司机端"};
        String sheetName = "价格核对信息";

        PriceCheckVO priceCheckVO = new PriceCheckVO();
        priceCheckVO.setCity("城市1");
        priceCheckVO.setServiceType("业务类型1");
        priceCheckVO.setGroup("车型1");
        priceCheckVO.setWorkingCostType("费用类型1");
        priceCheckVO.setWorking("g");
        priceCheckVO.setWorkingPassenger("ckd");
        priceCheckVO.setWorkingDriver("sj");
        priceCheckVO.setHolidayCostType("fe");
        priceCheckVO.setHoliday("sdd");
        priceCheckVO.setHolidayPassenger("ddd");
        priceCheckVO.setHolidayDriver("dsfs");

        HSSFWorkbook workbook = getWorkBook(sheetName, title, Arrays.asList(priceCheckVO));
    }


    static class PriceCheckVO {
        private String city;
        private String serviceType;
        private String group;
        private String workingCostType;
        private String working;
        private String workingPassenger;
        private String workingDriver;
        private String holidayCostType;
        private String holiday;
        private String holidayPassenger;
        private String holidayDriver;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public String getWorkingCostType() {
            return workingCostType;
        }

        public void setWorkingCostType(String workingCostType) {
            this.workingCostType = workingCostType;
        }

        public String getWorking() {
            return working;
        }

        public void setWorking(String working) {
            this.working = working;
        }

        public String getWorkingPassenger() {
            return workingPassenger;
        }

        public void setWorkingPassenger(String workingPassenger) {
            this.workingPassenger = workingPassenger;
        }

        public String getWorkingDriver() {
            return workingDriver;
        }

        public void setWorkingDriver(String workingDriver) {
            this.workingDriver = workingDriver;
        }

        public String getHolidayCostType() {
            return holidayCostType;
        }

        public void setHolidayCostType(String holidayCostType) {
            this.holidayCostType = holidayCostType;
        }

        public String getHoliday() {
            return holiday;
        }

        public void setHoliday(String holiday) {
            this.holiday = holiday;
        }

        public String getHolidayPassenger() {
            return holidayPassenger;
        }

        public void setHolidayPassenger(String holidayPassenger) {
            this.holidayPassenger = holidayPassenger;
        }

        public String getHolidayDriver() {
            return holidayDriver;
        }

        public void setHolidayDriver(String holidayDriver) {
            this.holidayDriver = holidayDriver;
        }
    }

}
