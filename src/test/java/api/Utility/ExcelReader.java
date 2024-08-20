package api.Utility;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

<<<<<<< Updated upstream

			
		public static File pdfFilePath;
		
		static XSSFSheet sheet;
		static XSSFWorkbook workbook;
		static String path=System.getProperty("user.dir");
		static String excelSheet = paths.getString("excelfile");
		
 	static DataFormatter dataFormatter = new DataFormatter();

			
		

	 public static List<LinkedHashMap<String,String>> getExcelData( String sheetName) throws IOException {
		 String filePath = System.getProperty("user.dir") + "/src/test/resources/ExcelData/testData.xlsx";
	        Workbook workbook = WorkbookFactory.create(new FileInputStream(filePath));
	        Sheet sheet = workbook.getSheet(sheetName); 	
		    	List<LinkedHashMap<String,String>> dataFromExcel = new ArrayList<>();

		        int totalRows = sheet.getPhysicalNumberOfRows();
		        LinkedHashMap<String,String> mapData;
		        List<String> allKeys = new ArrayList<>();
		        
		        for(int i = 0; i< totalRows ; i++) {
		            mapData = new LinkedHashMap<>();
		            if( i == 0) {
		                int totalCols =  sheet.getRow(0).getPhysicalNumberOfCells();
		                for (int j = 0; j < totalCols; j++) {
		                    allKeys.add(sheet.getRow(0).getCell(j).getStringCellValue());
		                }
		            }
		            else {
		            	int totalCols = sheet.getRow(i).getPhysicalNumberOfCells();
		                for (int j = 0; j < totalCols; j++) {
		                    String cellValue = dataFormatter.formatCellValue(sheet.getRow(i).getCell(j));
		                    mapData.put(allKeys.get(j), cellValue);
		            }
		                dataFromExcel.add(mapData);
		                
		                
		    }
		        
		    }
		        return dataFromExcel;
                 // return allKeys;		        
		    }
		    
=======
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

			for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {

				cell = row.getCell(currentColumn);

				String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn)
						.getStringCellValue();

				columnMapdata.put(columnHeaderName, cell.getStringCellValue());
			}

			excelRows.add(columnMapdata);
		}

		return excelRows;
	}

	public int countRow() {

		return totalRow;
	}
>>>>>>> Stashed changes

}