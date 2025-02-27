package ExcelUtility;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataExcel {

    public static void main(String[] args)throws IOException {

        File excelfile = new File("TestData.xlsx");
        System.out.println(excelfile.exists());
        try {
            FileInputStream fis = new FileInputStream(excelfile);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("BreadData");
            System.out.println(sheet.getPhysicalNumberOfRows());



            workbook.close();
            fis.close();

        }
        catch (FileNotFoundException e){
            System.out.println("file is not shown");
        }

    }

}
