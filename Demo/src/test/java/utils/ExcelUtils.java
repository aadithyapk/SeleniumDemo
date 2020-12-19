package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	
	public static void setExcelFile(String strFilePath, String strSheetName) {
		try {
			OPCPackage pkg = OPCPackage.open(new File(strFilePath));
			ExcelWBook = new XSSFWorkbook(pkg);
		} catch (FileNotFoundException e) {
			Log.logFatal("File in path - "+strFilePath+" was not found");
			e.printStackTrace();
		} catch (IOException e) {
			Log.logError("IO Exception when accessing file in path - "+strFilePath);
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			Log.logError("Invalid format exception when accessing file in path - "+strFilePath);
			e.printStackTrace();
		} 
		ExcelWSheet = ExcelWBook.getSheet(strSheetName);
	}
	public static Object[][] getTableArray(String strFilePath, String strSheetName, int intTestCaseRow) {
		String[][] arrTabData = null;
		int startCol, totalRows, totalCols, ci, cj;
		startCol = 1;
		totalRows = 1;
		totalCols = 2;
		ci = 0;
		cj = 0;
		setExcelFile(strFilePath, strSheetName);
		arrTabData=new String[totalRows][totalCols];
		for (int j=startCol;j<=totalCols;j++, cj++) {
			arrTabData[ci][cj]=getCellData(intTestCaseRow,j);
			System.out.println(arrTabData[ci][cj]);
		}
		return(arrTabData);
	}
	public static String getCellData(int intRowNum, int intColNum) {
		Cell = ExcelWSheet.getRow(intRowNum).getCell(intColNum);
		String CellData = Cell.getStringCellValue();
		return CellData;
	}
	public static int getRowContains(String strTestCaseName, int intcolNum) {
		int i;
		int rowCount = ExcelUtils.getUsedRowsCount();
		for ( i=0 ; i<rowCount; i++) {
			if  (ExcelUtils.getCellData(i,intcolNum).equalsIgnoreCase(strTestCaseName)) {
				break;
			}
		}
		return i;
	}
	public static int getUsedRowsCount() {
		int rowCount = ExcelWSheet.getLastRowNum();
		return rowCount;
	}

}
