package api.Utility;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader extends CommonUtils {


			
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
		    

}