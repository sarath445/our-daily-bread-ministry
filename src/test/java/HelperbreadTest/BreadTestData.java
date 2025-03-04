package HelperbreadTest;

import ExcelUtility.DataExcel;
import org.testng.annotations.DataProvider;

public class BreadTestData {
    @DataProvider(name = "shippings")
    public static Object[][] BreadData()throws Exception{
        String sheetname = "BreadData";
        return DataExcel.getExceldata(sheetname);
    }
}
