package api.Utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader extends CommonUtils {
	public static File pdfFilePath;

	static XSSFSheet sheet;
	static XSSFWorkbook workbook;
	static String path = System.getProperty("user.dir");
	static String excelSheet = paths.getString("excelfile");

	static DataFormatter dataFormatter = new DataFormatter();
	public static int totalRow;

	public List<Map<String, String>> getData(String excelFilePath, String sheetName)
			throws InvalidFormatException, IOException {
		Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
		Sheet sheet = workbook.getSheet(sheetName);
		workbook.close();
		return readSheet(sheet);
	}

	private List<Map<String, String>> readSheet(Sheet sheet) {
		Row row;
		Cell cell;
		totalRow = sheet.getLastRowNum();
		List<Map<String, String>> excelRows = new ArrayList<Map<String, String>>();
		for (int currentRow = 1; currentRow <= totalRow; currentRow++) {
			row = sheet.getRow(currentRow);
			int totalColumn = row.getLastCellNum();
			LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<String, String>();
			
			//String data = null;
			for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
			    // Get the cell at the current column index
			    cell = row.getCell(currentColumn);

			    // Get the column header name
			    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn).getStringCellValue();

			    // Initialize the data variable to an empty string or some default value
			    String data = "";

			    // Check if the cell is not null before accessing its value
			    if (cell != null) {
			        if (cell.getCellType() == CellType.STRING) {
			            data = cell.getStringCellValue();
			        } else if (cell.getCellType() == CellType.NUMERIC) {
			            data = String.valueOf(cell.getNumericCellValue());
			        } else if (cell.getCellType() == CellType.BLANK) {
			            // For blank cells, you might want to set it to an empty string
			            data = "";
			        } else if (cell.getCellType() == CellType.BOOLEAN) {
			            data = String.valueOf(cell.getBooleanCellValue());
			        } else if (cell.getCellType() == CellType.FORMULA) {
			            data = cell.getCellFormula(); // Or evaluate the formula if needed
			        }
			    } else {
			        // Handle the case where the cell is null (e.g., set data to an empty string or log a message)
			        data = "";  // or log a warning: System.out.println("Cell is null at row " + rowIndex + " and column " + currentColumn);
			    }

			    // Store the column header name and corresponding data in the map
			    columnMapdata.put(columnHeaderName, data);
					
			/*
			 * for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
			 * cell = row.getCell(currentColumn); String columnHeaderName =
			 * sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn)
			 * .getStringCellValue();
			 * 
			 * if (cell.getCellType() == CellType.STRING) data = cell.getStringCellValue();
			 * else if (cell.getCellType() == CellType.NUMERIC) data =
			 * String.valueOf(cell.getNumericCellValue()); else if (cell.getCellType() ==
			 * CellType.BLANK) data = String.valueOf(cell.getStringCellValue());
			 * 
			 * //columnMapdata.put(columnHeaderName, cell.getStringCellValue());
			 * 
			 * columnMapdata.put(columnHeaderName, data);
			 */
			}
			excelRows.add(columnMapdata);
		}
		return excelRows;
	}

	public int countRow() {
		return totalRow;
	}
}