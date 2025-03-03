package HelperbreadTest;

import ExcelUtility.DataExcel;
import org.testng.annotations.DataProvider;

public class BreadTestData {
    @DataProvider(name = "shipping")
    public Object[][] BreadData()throws Exception{
        String sheetname = "BreadData";
        String filepath = "D:\\ODBM\\our-daily-bread-ministry\\TestData.xlsx";
        return DataExcel.getExceldata(sheetname, filepath);
    }
}
