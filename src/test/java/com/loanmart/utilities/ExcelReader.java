package com.loanmart.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/*import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;*/
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private String filePath;
	//private String sheetName;
	private HashMap<String, String> data;
	private FileInputStream file;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public ExcelReader() {
		//filePath = "\\\\loanmart.com\\fs\\Dept\\Tech\\QA\\Automation\\LM Test Automation\\TestData\\";
		//sheetName = null;
		filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\";
		try {
			file = new FileInputStream(new File(filePath + "testdata.xlsx"));
			workbook = new XSSFWorkbook(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to open File");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to create workbook instance holding reference to .xlsx file");
			e.printStackTrace();
		}

	}
	
	public String getSheetName(int sheet) {
		String name =  workbook.getSheetName(sheet);
		return name;
	}
	
	public int getNumofSheets() {
		int totalNumberofSheets = workbook.getNumberOfSheets();
		return totalNumberofSheets;
	}
	
	private int getNumofCellsPerRow(int sheet) {
		int totalNumberofCellsPerRow = workbook.getSheetAt(sheet).getRow(0).getPhysicalNumberOfCells();
		return totalNumberofCellsPerRow;
	}
	
	private int getNumofActiveRows(int sheet) {
		int totalNumberofCellsPerRow = workbook.getSheetAt(sheet).getPhysicalNumberOfRows();
		System.out.println(totalNumberofCellsPerRow);
		return totalNumberofCellsPerRow;
	}
	
	private XSSFSheet getSheetObject(String sheetName) {
		XSSFSheet sheetObj = workbook.getSheet(sheetName);
		return sheetObj;
	}
	
	private int getSheetIndex(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		return index;
	}
	
	public void clearData() {
		data.clear();
	}
	
	public List<HashMap<String, String>> getDataFromExcelFile(String sheetName){
		List<HashMap<String, String>> allData = new ArrayList<HashMap<String, String>>();
		sheet = getSheetObject(sheetName);
		int numberOfCellPerRow = getNumofCellsPerRow(getSheetIndex(sheetName));
		int numberofRows =  getNumofActiveRows(getSheetIndex(sheetName));
		  
		for(int row = 1; row < numberofRows; row ++) {
			 String value = "";
			 String key = "";
			 data = new HashMap<String, String>();
			 
			 for(int column = 0; column < numberOfCellPerRow; column++ ) {
				 key = sheet.getRow(0).getCell(column).getStringCellValue();
				 if(sheet.getRow(row).getCell(column) != null)
					 value = sheet.getRow(row).getCell(column).getStringCellValue();
				 data.put(key, value);
			 }
			 
			 allData.add(data);
		}
		return allData;
	}
}
