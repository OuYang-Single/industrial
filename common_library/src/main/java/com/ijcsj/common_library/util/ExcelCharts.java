package com.ijcsj.common_library.util;

import android.util.Log;

import androidx.databinding.ObservableList;

import com.github.mikephil.charting.data.LineDataSet;
import com.ijcsj.common_library.bean.BackFlowBase;
import com.ijcsj.common_library.bean.DatasBase;
import com.ijcsj.common_library.bean.SetUpBean;
import com.ijcsj.common_library.bean.TemperatureBase;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.charts.AxisCrosses;
import org.apache.poi.ss.usermodel.charts.AxisPosition;
import org.apache.poi.ss.usermodel.charts.AxisTickMark;
import org.apache.poi.ss.usermodel.charts.ChartAxis;
import org.apache.poi.ss.usermodel.charts.ChartDataSource;
import org.apache.poi.ss.usermodel.charts.DataSources;
import org.apache.poi.ss.usermodel.charts.LineChartData;
import org.apache.poi.ss.usermodel.charts.LineChartSeries;
import org.apache.poi.ss.usermodel.charts.ValueAxis;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.charts.XSSFChartLegend;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelCharts {
//	public static void main(String[] args) throws Exception {
//		ExcelChart excelChart = new ExcelChart();
//		excelChart.CreateGraph("2012halinfo.txt", "2012halinfo", "底层内存曲线图");
//	}
 
	private static String TAG = "ExcelChart";
	
	/**
	 * dataPath:内存文件名，如：2012appinfo.txt
	 * heapName:曲线图顶部图：APP内存曲线或者底层内存曲线图
	 * excelName：保存excel表格名称
	 * @author cfr

	 */
	public boolean CreateGraph( String exportPath,  ObservableList<DatasBase> setObservableList){
		XSSFWorkbook my_workbook = new XSSFWorkbook();
		XSSFSheet my_worksheet = my_workbook.createSheet("操作日志");
		Row row = my_worksheet.createRow(0);
		row.createCell(0).setCellValue("来源");
		row.createCell(1).setCellValue("CanId");
		row.createCell(2).setCellValue("数据");
		row.createCell(3).setCellValue("发生/接收时间");


		Log.i(TAG,"mems:" + setObservableList.size());
		for (int i = 0; i < setObservableList.size(); i++) {
			row = my_worksheet.createRow(i + 1);

			row.createCell(0).setCellValue( setObservableList.get(i).isType()?"接收":"发送");
			if (setObservableList.get(i).getCanId().length()>2){
				row.createCell(1).setCellValue("0x"+setObservableList.get(i).getCanId());
			}else {
				row.createCell(1).setCellValue("0x0"+setObservableList.get(i).getCanId());
			}

			row.createCell(2).setCellValue(setObservableList.get(i).getData());
			row.createCell(3).setCellValue(setObservableList.get(i).getTime());
		}
 
		/*
		 * At the end of this step, we have a worksheet with test data, that we
		 * want to write into a chart
		 */
		/* Create a drawing canvas on the worksheet */

		/* Finally define FileOutputStream and write chart information */
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(exportPath + ".xlsx");
			my_workbook.write(fileOut);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (fileOut != null) {
					fileOut.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}