/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.cucsi.bsd.ucc.common.untils;

import cn.cucsi.bsd.ucc.common.beans.PbxCdrsExcelCriteria;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;


import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExportUtil {

	private WritableWorkbook wwb;

	private WritableSheet sheet;

	private OutputStream os;

	private Label label;

	private jxl.write.Number numberLabel;

	private Integer rowNumber;

	private LinkedList<MyField> myFields;

	private LinkedList<String> myTitles;

	private SimpleDateFormat dateDf;

	private DecimalFormat doubleDf;

	public ExportUtil(HttpServletResponse rsp, String TableName,String sheetName, Class<?> clazz) throws Exception {

		rowNumber = 0;
		myFields = new LinkedList<MyField>();
		myTitles = new LinkedList<String>();
		dateDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		doubleDf = new DecimalFormat("######0.00");
		
		Field[] fields = clazz.getDeclaredFields();// 类属性
		for (Field field : fields) {
			AttributeName ann = field.getAnnotation(AttributeName.class);
			if (ann != null) {
				myTitles.add(ann.value());
				field.setAccessible(true);
				myFields.add(new MyField(field));
			}
		}
		/**
		 * 设置Respone 以及Excel表名
		 */
		//rsp.setContentType("application/vnd.ms-excel;charset=utf-8");
                rsp.setHeader("Content-type", "application-download");
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		rsp.setHeader("Content-Disposition", "attachment;filename=" + TableName + df.format(new Date()) + ".xls");

		/**
		 * 初始化
		 */
		os = rsp.getOutputStream();
		wwb = Workbook.createWorkbook(os);
		sheet = wwb.createSheet(sheetName, 0);

		/**
		 * 设置自动换行 以及靠左对齐
		 */
		WritableCellFormat formatTitle = new WritableCellFormat();
		formatTitle.setWrap(true);
		formatTitle.setAlignment(jxl.format.Alignment.LEFT);

		/**
		 * 设置Title
		 */
		for (int i = 0; i < myTitles.size(); i++) {
			sheet.setColumnView(i, (myTitles.get(i)).getBytes().length + 10);// 设置列宽
			label = new Label(i, 0, myTitles.get(i));
			sheet.addCell(label);
		}
		rowNumber++;
	}

 

	public void writeRow(Object obj) throws Exception {

		MyField myField;

		for (int i = 0; i != myFields.size(); i++) {
			myField = myFields.get(i);
			Object value = myFields.get(i).field.get(obj);
			
			if (value != null) {
				if (myField.dataFmt == null) {
					myField.dataFmt = false;
					myField.isNumber = false;
					myField.doubleFmt = false;
					if (value instanceof Date) {
						myField.dataFmt = true;
					}
					if (value instanceof Double) {
						myField.isNumber = true;
						myField.doubleFmt = true;
					}
					if (value instanceof Integer) {
						myField.isNumber = true;
					}
				}
				
				if (!myField.isNumber) {
					if (!myField.dataFmt) {
						label = new Label(i, rowNumber, value.toString());
						sheet.addCell(label);
					} else {
						label = new Label(i, rowNumber, dateDf.format(value));
						sheet.addCell(label);
					}
				} else {
					if (myField.doubleFmt) {
						numberLabel = new jxl.write.Number(i, rowNumber, Double.valueOf(doubleDf.format(value)).doubleValue());
						sheet.addCell(numberLabel);
					} else {
						numberLabel = new jxl.write.Number(i, rowNumber, (Integer) value);
						sheet.addCell(numberLabel);
					}

				}
			}else {
				label = new Label(i, rowNumber, "");
				sheet.addCell(label);
			}
		}

		rowNumber++;
	}

	public void close() throws Exception {
		wwb.write();
		wwb.close();
		os.close();
	}

	class MyField {
		Field field;
		Boolean dataFmt;
		Boolean isNumber;
		Boolean doubleFmt;

		public MyField(Field field) {
			super();
			this.field = field;
		}
	}
}