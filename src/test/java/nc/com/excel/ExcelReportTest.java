package nc.com.excel;

import nc.com.analyzer.Analyzer;
import nc.com.analyzer.Reflection;
import nc.com.fillers.ArrayType;
import nc.com.sorters.SortType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExcelReportTest {
    private ExcelReport excelReport;

    @Before
    public void init() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        excelReport = new ExcelReport(new Analyzer().analyze(new Reflection()));
    }

    @Test
    public void createReportTest() {
        excelReport.createReport();
        File file = new File(excelReport.EXCEL_PATH);
        assertTrue(file.exists());
    }

    @Test
    public void excelSheetNameTest() {
        try {
            FileInputStream fis = new FileInputStream(new File(excelReport.EXCEL_PATH));
            Workbook workbook = new XSSFWorkbook(fis);
            ArrayType[] arrayTypes = ArrayType.values();
            SortType[] sortTypes = SortType.values();
            for (int sheetNum = 0; sheetNum < arrayTypes.length; sheetNum++) {
                Sheet sheet = workbook.getSheet(arrayTypes[sheetNum].toString());
                assertTrue(sheet != null);
                for (int rowNum = 1; rowNum <= sortTypes.length; rowNum++) {
                    Row row = sheet.getRow(rowNum);
                    Cell cell = row.getCell(0);
                    assertEquals(cell.getStringCellValue(), sortTypes[rowNum - 1].toString());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
