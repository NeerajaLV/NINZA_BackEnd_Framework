package com.ninza.hrm.api.genericutility;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * This class contains generic methods to use excel file
 * @author neera
 */
public class ExcelFileUtility {
	String fileName="./testData.xlsx";
	/**
	 * This method will read data from VTiger excel file and return value to the caller
	 * @param sheetName
	 * @param rowNum
	 * @param colNum
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String getDataFromExcelFile(String sheetName, int rowNum, int colNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(fileName);
		Workbook book=WorkbookFactory.create(fis);
		String data = null;
		try {
		data=book.getSheet(sheetName).getRow(rowNum).getCell(colNum).getStringCellValue();
		}
		catch(Exception e){}
		
		return data;
	}
	/**
	 * This method will read data from excel file and return value to the caller
	 * @param URL
	 * @param sheetName
	 * @param rowNum
	 * @param colNum
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String getDataFromExcelFile(String URL, String sheetName, int rowNum, int colNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(URL);
		Workbook book=WorkbookFactory.create(fis);
		String data = null;
		try {
		data=book.getSheet(sheetName).getRow(rowNum).getCell(colNum).getStringCellValue();
		}
		catch(Exception e){}
		
		return data;
	}
	/**
	 * This method will get last row number of VTiger and return the value to caller
	 * @param sheetName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(fileName);
		Workbook book=WorkbookFactory.create(fis);
		int rowCount=book.getSheet(sheetName).getLastRowNum();
		
		return rowCount;
	}
	/**
	 * This method will get last row number and return the value to caller
	 * @param URL
	 * @param sheetName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public int getRowCount(String URL, String sheetName) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(URL);
		Workbook book=WorkbookFactory.create(fis);
		int rowCount=book.getSheet(sheetName).getLastRowNum();
		return rowCount;
	}
	/**
	 * This method used to write the data into VTiger excel file
	 * @param sheetName
	 * @param rowNum
	 * @param colNum
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void setDataIntoExcelFile(String sheetName, int rowNum, int colNum,String data) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(fileName);
		Workbook book=WorkbookFactory.create(fis);
		book.getSheet(sheetName).getRow(rowNum).createCell(colNum).setCellValue(data);
		FileOutputStream fos=new FileOutputStream("./testData.xlsx");
		book.write(fos);
		book.close();
		fis.close();
		fos.close();
	}
	/**
	 * This method used to write the data into excel file
	 * @param URL
	 * @param sheetName
	 * @param rowNum
	 * @param colNum
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void setDataIntoExcelFile(String URL, String sheetName, int rowNum, int colNum,String data) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(URL);
		Workbook book=WorkbookFactory.create(fis);
		book.getSheet(sheetName).createRow(rowNum).createCell(colNum).setCellValue(data);
		FileOutputStream fos=new FileOutputStream(URL);
		book.write(fos);
		book.close();
		fis.close();
		fos.close();
	}
	/**
	 * This method used to fetch all data from excel file
	 * @param URL
	 * @throws IOException
	 */
	public void getAllDataFromExcel(String URL) throws IOException
	{
		FileInputStream fis=new FileInputStream(URL);
        Workbook workbook = WorkbookFactory.create(fis);
    	DataFormatter formatter=new DataFormatter();
        // Loop through all sheets
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
        Sheet sheet = workbook.getSheetAt(i);
        System.out.println("Sheet Name: " + sheet.getSheetName());

            // Loop through all rows
            for (Row row : sheet) {
                // Loop through all cells
                for (Cell cell : row) {
                	String value=formatter.formatCellValue(cell);
                	if(value.isBlank()||value==null)
                		{System.out.print("\t");}
                	else
                    	{System.out.print(value+" \t ");}
                }
                System.out.println();
            }
            System.out.println("--------------------------------------------------");
        }
        
        workbook.close();
        fis.close();
	}
	/**
	 * This method used to clear all data from excel file
	 * @param URL
	 * @throws IOException
	 */
	public void clearAllDataFromExcel(String URL) throws IOException
	{
		FileInputStream fis=new FileInputStream(URL);
        Workbook workbook = WorkbookFactory.create(fis);

        // Loop through all sheets
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
        Sheet sheet = workbook.getSheetAt(i);
        System.out.println("Sheet Name: " + sheet.getSheetName());

            // Loop through all rows
            for (Row row : sheet) {
                // Loop through all cells
                for (Cell cell : row) {
                	cell.setCellValue("");
         FileOutputStream fos=new FileOutputStream(URL);
         workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();
		}
        }}}

    	public List<List<String>> readExcelData(String filePath, String sheetName) throws IOException {
        List<List<String>> excelData = new ArrayList<>();
        FileInputStream fis = null;
        Workbook workbook = null;

        try {
            File file = new File(filePath);
            fis = new FileInputStream(file);

            // Determine workbook type based on file extension
            if (filePath.toLowerCase().endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (filePath.toLowerCase().endsWith(".xls")) {
                workbook = new HSSFWorkbook(fis);
            } else {
                throw new IllegalArgumentException("Unsupported file format. Please provide a .xlsx or .xls file.");
            }

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in the Excel file.");
            }

            for (Row row : sheet) {
                List<String> rowData = new ArrayList<>();
                for (Cell cell : row) {
                    // Handle different cell types
                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            // Handle numeric values, including dates
                            if (DateUtil.isCellDateFormatted(cell)) {
                                rowData.add(cell.getDateCellValue().toString()); // Or format as needed
                            } else {
                                rowData.add(String.valueOf(cell.getNumericCellValue()));
                            }
                            break;
                        case BOOLEAN:
                            rowData.add(String.valueOf(cell.getBooleanCellValue()));
                            break;
                        case FORMULA:
                            rowData.add(cell.getCellFormula()); // Get the formula string
                            // To get the calculated value of a formula cell:
                            // rowData.add(String.valueOf(cell.getNumericCellValue())); // Or getStringCellValue()
                            break;
                        case BLANK:
                            rowData.add(""); // Add empty string for blank cells
                            break;
                        default:
                            rowData.add(""); // Default for other types or errors
                    }
                }
                excelData.add(rowData);
            }

        } finally {
            if (workbook != null) {
                workbook.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return excelData;
    }
    	
    	public List<List<String>> readExcelDataOpt(String filePath, String sheetName) throws IOException {
            List<List<String>> excelData = new ArrayList<>();
            FileInputStream fis = new FileInputStream(filePath);;
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            DataFormatter formatter=new DataFormatter();
            List<String> rowData =null;
                for (Row row : sheet) {
                    rowData = new ArrayList<>();
                    for (Cell cell : row) {
                                rowData.add(formatter.formatCellValue(cell));
                        }
                    excelData.add(rowData);
                    }
            excelData.add(rowData);
            workbook.close();
            fis.close();
            return excelData;
        }
    	public Object[][] readExcelDataObj(String filePath, String sheetName) throws EncryptedDocumentException, IOException
    	{
    		FileInputStream fis=new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet=workbook.getSheet(sheetName);
            DataFormatter formatter = new DataFormatter();
            int lastRow=sheet.getLastRowNum();
            int lastCell=0;
            for(int i=0;i<=lastRow;i++)
            {
            	int temp=sheet.getRow(i).getLastCellNum();
            	if(temp>lastCell)
            	{
            		lastCell=temp;
            	}
            }
//            System.out.println("Arguments to be passed: "+lastCell);
            Object[][] obj=new Object[lastRow+1][lastCell];
            for(int j=0;j<=lastRow;j++)
            {
            	for(int k=0;k<lastCell;k++)
            	{
            		try {
            			
            			obj[j][k]=formatter.formatCellValue(sheet.getRow(j).getCell(k));
            		}
            		catch(Exception e){}
            	}
            }
    		return obj;
         }

}
