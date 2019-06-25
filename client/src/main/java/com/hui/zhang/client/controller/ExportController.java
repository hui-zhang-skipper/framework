package com.hui.zhang.client.controller;


import com.hui.zhang.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("export")
public class ExportController {

    @RequestMapping("/do")
    public void export(HttpServletResponse response) throws Exception {
        String[] title = {"城市", "业务类型", "车型", "费用类型", "工作日",
                "乘客端", "司机端", "费用类型", "休息日", "乘客端", "司机端"};
        String sheetName = "价格核对信息";
        List<Object> list = new ArrayList<>();
        HSSFWorkbook workbook = ExcelUtil.getWorkBook(sheetName, title, list);

        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String("新价格计划核对信息.xls".getBytes("utf-8"), "iso8859-1"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
        out.close();
    }
}
