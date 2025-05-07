package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	public static XSSFWorkbook wb;
	public static HSSFWorkbook wbh;
	public static XSSFSheet sh;
	public static HSSFSheet shh;
	public static FileInputStream f;

	public static String readDataFromExcel(int row, int column, String filePath, String sheetName)
			throws IOException {
		f = new FileInputStream(System.getProperty("user.dir") + filePath);
		wb = new XSSFWorkbook(f);
		sh = wb.getSheet(sheetName);
		DataFormatter formatter = new DataFormatter();
		Row r = sh.getRow(row);
		Cell c = r.getCell(column);
		String value = formatter.formatCellValue(c);
		// return c.getStringCellValue();
		return value;
	}
}

