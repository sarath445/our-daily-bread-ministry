package ExcelUtility;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class DataExcel {
     @DataProvider(name = "shippingdata")
    public static Object[][] getExceldata()throws IOException {

        File excelfile = new File("TestData.xlsx");
        System.out.println(excelfile.exists());
        try {
            FileInputStream fis = new FileInputStream(excelfile);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("BreadData");
            System.out.println(sheet.getPhysicalNumberOfRows());      //printing the total number of rows present in the sheet.
            int noOfrow = sheet.getPhysicalNumberOfRows();   //total number of rows present in the sheet
            int noOfcolumn = sheet.getRow(0).getLastCellNum();
            System.out.println(sheet.getLastRowNum());      //excluding the header row
            Object[][] data = new Object[noOfrow-1][noOfcolumn];

            for(int i = 0; i<noOfrow-1; i++){
                for(int j = 0; j<noOfcolumn; j++){
                    DataFormatter dataFormatter = new DataFormatter();
                    data[i][j] = dataFormatter.formatCellValue(sheet.getRow(i+1).getCell(j));

                }
            }

            workbook.close();
            fis.close();

//            for(Object[] dataArray :data){
//                System.out.println(Arrays.toString(dataArray));
//            }

            return data;
        }
        catch (FileNotFoundException e){
            System.out.println("file is not shown");
        }
         return null;
    }

}
