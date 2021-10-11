package utils;

import java.io.File;
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
		} catch (Exception e) {
			Log.logException("accessing excel file in path - "+strFilePath, e);
		} 
		ExcelWSheet = ExcelWBook.getSheet(strSheetName);
		Log.logInfo("Excel was to set to file at path - "+strFilePath+" with sheet - "+strSheetName);
	}
	
	public static Object[][] getTableArray(String strFilePath, String strSheetName, int intTestCaseRow) {
		String[][] arrTabData = null;
		try {
		int startCol, totalRows, totalCols, ci, cj;
		startCol = 1;
		totalRows = ExcelUtils.getUsedRowsCount();
		totalCols = ExcelUtils.getUsedColumnsCount();
		ci = 0;
		cj = 0;
		setExcelFile(strFilePath, strSheetName);
		arrTabData=new String[totalRows][totalCols];
		for (int j=startCol;j<=totalCols;j++, cj++) {
			arrTabData[ci][cj]=getCellData(intTestCaseRow,j);
		} } catch (Exception e) {
			Log.logException("Exception in getting table array from excel sheet - "+strSheetName+" at path - "+strFilePath, e);
		}
		Log.logInfo("Table array was successfully fetched from excel sheet - "+strSheetName+" at path - "+strFilePath);
		return(arrTabData);
	}
	
	private static String getCellData(int intRowNum, int intColNum) {
		Cell = ExcelWSheet.getRow(intRowNum).getCell(intColNum);
		String CellData = Cell.getStringCellValue();
		//Log.logInfo("getCellData on cell ("+intRowNum+","+intColNum+")"+" sucessfully returned "+CellData);
		return CellData;
	}
	
	public static int getRowContains(String strSearchString, int intcolNum) {
		int i;
		Boolean wasFound = false;
		int rowCount = ExcelUtils.getUsedRowsCount();
		for ( i=0 ; i<rowCount; i++) {
			if  (ExcelUtils.getCellData(i,intcolNum).equalsIgnoreCase(strSearchString)) {
				wasFound = true;
				Log.logInfo("Searched for "+strSearchString+" in column "+intcolNum+". Found in row "+rowCount);
				break;
			}
		}
		if (wasFound = false) {
			Log.logFatal("Searched for "+strSearchString+" in column "+intcolNum+" was not found ");
		}
		return i;
	}
	
	private static int getUsedRowsCount() {
		int rowCount = ExcelWSheet.getLastRowNum();
		return rowCount;
	}
	
	private static int getUsedColumnsCount() {
		int colCount;
		colCount = (ExcelWSheet.getRow(0).getLastCellNum())-1;
		return colCount;
	}

}
