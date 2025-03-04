package ExcelUtility;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class DataExcel {
    private static final String File_path = "D:\\ODBM\\OurDailyBreadMinistry\\TestData.xlsx";

    public static Object[][] getExceldata(String sheetname) throws Exception {

         FileInputStream fis = null;
         XSSFWorkbook workbook  = null;
         Object[][] data = null;
         try {
             fis = new FileInputStream(File_path);
             workbook = new XSSFWorkbook(fis);
             XSSFSheet sheet = workbook.getSheet(sheetname);
             int rowcount = sheet.getPhysicalNumberOfRows();
             int rowcolumn = sheet.getRow(0).getPhysicalNumberOfCells();
             data = new Object[rowcount-1][rowcolumn];
             DataFormatter formatter = new DataFormatter();
             for(int i = 1; i<rowcount; i++){
                 for(int j=0; j<rowcolumn; j++){
                     Cell cell = sheet.getRow(i).getCell(j);
                     data[i-1][j] = formatter.formatCellValue(cell);
                 }
             }

         }
         catch (IOException e ){
             System.out.println("Error reading Excel file: " + e.getMessage());
             throw new FileNotFoundException();
         }
         finally {
             try {
                 {
                     if(workbook!=null){
                         workbook.close();
                     }
                     if(fis!=null){
                         fis.close();
                     }
                 }
             }
             catch (IOException e){
                 System.out.println("Error closing Excel resources: " + e.getMessage());
             }
         }
        //File excelfile = new File("TestData.xlsx");
        //System.out.println(excelfile.exists());

return data;
    }

}























































//try {
//FileInputStream fis = new FileInputStream(File_path);
//XSSFWorkbook workbook = new XSSFWorkbook(fis);
//XSSFSheet sheet = workbook.getSheet(sheetname);
//            System.out.println(sheet.getPhysicalNumberOfRows());      //printing the total number of rows present in the sheet.
//int noOfrow = sheet.getPhysicalNumberOfRows();   //total number of rows present in the sheet
//int noOfcolumn = sheet.getRow(0).getLastCellNum();
//            System.out.println(sheet.getLastRowNum());      //excluding the header row
//Object[][] data = new Object[noOfrow - 1][noOfcolumn];
//
//            for (int i = 0; i < noOfrow - 1; i++) {
//        for (int j = 0; j < noOfcolumn; j++) {
//DataFormatter dataFormatter = new DataFormatter();
//data[i][j] = dataFormatter.formatCellValue(sheet.getRow(i + 1).getCell(j));
//
//        }
//        }
//
//        workbook.close();
//            fis.close();
//
////            for(Object[] dataArray :data){
////                System.out.println(Arrays.toString(dataArray));
////            }
//
//
//        } catch (FileNotFoundException e) {
//        System.out.println("file is not shown");
//        }
//                return  null;
//
//                }