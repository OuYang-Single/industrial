package com.ijcsj.common_library.util;

import android.util.Log;

import androidx.databinding.ObservableList;

import com.github.mikephil.charting.data.LineDataSet;
import com.ijcsj.common_library.bean.BackFlowBase;
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
import java.util.ArrayList;

public class ExcelChart {
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
	public boolean CreateGraph( String exportPath,  ObservableList<LineDataSet> setObservableList){
		XSSFWorkbook my_workbook = new XSSFWorkbook();
		XSSFSheet my_worksheet = my_workbook.createSheet("温度数据");
		Row row = my_worksheet.createRow(0);
		row.createCell(0).setCellValue("时间");
		row.createCell(1).setCellValue("出水温度");
		row.createCell(2).setCellValue("回水温度");
		row.createCell(3).setCellValue("设定温度");


		Log.i(TAG,"mems:" + setObservableList.size());
		for (int i = 0; i < setObservableList.get(0).getValues().size(); i++) {
			row = my_worksheet.createRow(i + 1);
			row.createCell(0).setCellValue( DateUtil.formatTimes(((TemperatureBase)setObservableList.get(0).getValues().get(i).getData()).getDateTime()));
			row.createCell(1).setCellValue(((TemperatureBase)setObservableList.get(0).getValues().get(i).getData()).getTemperature());
			row.createCell(2).setCellValue(((BackFlowBase)setObservableList.get(1).getValues().get(i).getData()).getTemperature());
			float a=((SetUpBean)setObservableList.get(2).getValues().get(i).getData()).getTemperature();
			row.createCell(3).setCellValue(a/10f);
		}
 
		/*
		 * At the end of this step, we have a worksheet with test data, that we
		 * want to write into a chart
		 */
		/* Create a drawing canvas on the worksheet */
		XSSFDrawing xlsx_drawing = my_worksheet.createDrawingPatriarch();
		// 八个参数，前四个表示图片离起始单元格和结束单元格边缘的位置，
		// 后四个表示起始和结束单元格的位置，如下表示从第2列到第12列，从第1行到第15行,需要注意excel起始位置是0
		XSSFClientAnchor anchor = xlsx_drawing.createAnchor(0, 0, 0, 0, 5, 3, 14, 18);
		/* Create the chart object based on the anchor point */
		XSSFChart my_line_chart = xlsx_drawing.createChart(anchor);
		/*
		 * Define legends for the line chart and set the position of the legend
		 */
		XSSFChartLegend legend = my_line_chart.getOrCreateLegend();
		legend.setPosition(org.apache.poi.ss.usermodel.charts.LegendPosition.BOTTOM);
		/* Create data for the chart */
		LineChartData data = my_line_chart.getChartDataFactory().createLineChartData();
		/* Define chart AXIS */
		ChartAxis bottomAxis = my_line_chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
		bottomAxis.setCrosses(AxisCrosses.AUTO_ZERO);
		bottomAxis.setMajorTickMark(AxisTickMark.NONE);//取消X轴的标刻度
		ValueAxis leftAxis = my_line_chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
		leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
		float d1=0.0f;
		float d2=0.0f;
		for ( int i=0;i<setObservableList.get(0).getValues().size();i++){
			if (setObservableList.get(0).getValues().get(i).getY()>d1){
				d1=setObservableList.get(0).getValues().get(i).getY();
			}
		}
		for ( int i=0;i<setObservableList.get(1).getValues().size();i++){
			if (setObservableList.get(1).getValues().get(i).getY()<d1){
				d2=setObservableList.get(1).getValues().get(i).getY();
			}
		}

		leftAxis.setMaximum(d1+10f);

		leftAxis.setMinimum(d2-5);
		//添加数据
		ChartDataSource<Number> xs = DataSources.fromNumericCellRange(my_worksheet, new CellRangeAddress(1, setObservableList.get(0).getValues().size(), 0, 0));
		ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(my_worksheet, new CellRangeAddress(1, setObservableList.get(0).getValues().size(), 1, 1));
		ChartDataSource<Number> ys2 = DataSources.fromNumericCellRange(my_worksheet, new CellRangeAddress(1, setObservableList.get(1).getValues().size(), 2, 2));
		ChartDataSource<Number> ys3= DataSources.fromNumericCellRange(my_worksheet, new CellRangeAddress(1, setObservableList.get(2).getValues().size(), 3, 3));

		LineChartSeries series = data.addSeries(xs, ys1);
		LineChartSeries seriess1 = data.addSeries(xs, ys2);
		LineChartSeries seriess2 = data.addSeries(xs, ys3);
		series.setTitle("出水温度");//设置序列名称
		seriess1.setTitle("回水温度");//设置序列名称
		seriess2.setTitle("设定温度");//设置序列名称
//		my_line_chart.setTitle("内存曲线");//设置图表标题
		my_line_chart.plot(data, new ChartAxis[] { bottomAxis, leftAxis });
		
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