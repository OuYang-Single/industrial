package com.ijcsj.common_library.util;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

/*
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
*/

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
public class exportChartDataToExcel {


    public void exportChartDataToExcel(LineChart lineChart, String fileName) {
      /*  Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("ChartData");

        // 标题行
        Row titleRow = sheet.createRow(0);
        titleRow.createCell(0).setCellValue("X-Axis");
        titleRow.createCell(1).setCellValue("Y-Axis");

        // 数据行
        int rowNum = 1;
        List<Entry> entries = lineChart.getData().getDataSets().get(0).getEntriesForXValue(0);
        for (Entry entry : entries) {
            Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(entry.getX());
            row.createCell(1).setCellValue(entry.getY());
            rowNum++;
        }

        // 写入文件
        try {
            File file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
