package com.jnd.sonarqube.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jnd.sonarqube.beans.Measures;
import com.jnd.sonarqube.beans.MeasuresBean;

public class PoiService {

	public File exportProjectMeasuresReport(MeasuresBean measuresBean) {

		Workbook workbook = new XSSFWorkbook();
		//CreationHelper createHelper = workbook.getCreationHelper();

		// Create a Sheet
		Sheet sheet = workbook.createSheet("Report for " + measuresBean.getComponent().getName());

		// Create a Font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Create a Row
		Row headerRow = sheet.createRow(0);
		Row dataRow = sheet.createRow(1);
		
		// Create cell headers
		Measures[] measures = measuresBean.getComponent().getMeasures();
		for (int i = 0; i < measures.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(measures[i].getMetric().toUpperCase());
			cell.setCellStyle(headerCellStyle);
			
			dataRow.createCell(i).setCellValue(measures[i].getValue());
		}
		
		// Resize all columns to fit the content size
		for (int i = 0; i < measures.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = null;
        File report_file = new File("SonarQube_Report_For_" + measuresBean.getComponent().getName() + ".xlsx");
		try {
			
			fileOut = new FileOutputStream(report_file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
        try {
			workbook.write(fileOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        try {
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

        // Closing the workbook
        try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return report_file;
    }

}
