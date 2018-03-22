package nc.com.excel;

import nc.com.analyzer.Statistic;
import nc.com.fillers.ArrayType;
import nc.com.fillers.ArraysFiller;
import nc.com.sorters.SortType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;

/**
 * A {@code ExcelReport} is working on creating a *.xlsx file
 * that contains statistics for sorting. It provides a single
 * public method {@link ExcelReport#createReport()}.
 *
 * @author Oleksandr Smirnov
 * @see Workbook
 * @see XSSFWorkbook
 * @see FileOutputStream
 * @see Sheet
 * @see Row
 * @see Cell
 * @see Drawing
 * @see ClientAnchor
 * @see Chart
 * @see LineChartData
 * @see ChartAxis
 * @see ValueAxis
 * @see DataSources
 *
 * @see Statistic
 * @see ArrayType
 * @see nc.com.sorters.SortType
 */
public class ExcelReport {
    public final String EXCEL_PATH = "src/main/resources/ExcelReport.xlsx";
    private final int MATRIX_ROWS = SortType.values().length;
    private int MATRIX_COLS;

    private List<Statistic> statistics;

    /**
     * Public constructor for ExcelReport class.
     *
     * @param statistics the list of statistics.
     */
    public ExcelReport(List<Statistic> statistics) {
        this.statistics = statistics;
    }

    /**
     * The public method that create "src/main/resources/ExcelReport.xlsx"
     * file placed on statistics that contains field statistics.
     */
    public void createReport(){
        setMinCols();
        Workbook wb;
        Sheet sheet;
        ArrayType arrayType;
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(new File(EXCEL_PATH));
            wb = new XSSFWorkbook();

            arrayType = null;
            sheet = null;
            int rowsIterator = 1;

            for (Statistic statistic : statistics) {
                if (arrayType == null || arrayType != statistic.getArrayType()) {
                    arrayType = statistic.getArrayType();
                    sheet = wb.createSheet(arrayType.toString());
                    rowsIterator = 1;
                }

                sheet = inputStatistic(sheet, statistic, rowsIterator++);

                if(rowsIterator == MATRIX_ROWS + 1)
                    sheet = createChart(sheet);
            }


            wb.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The private method that the method createReport use.
     * Return an sheet with new row that contains
     * the type of sort and values of times sorting
     * array with 10, 100, 1000 and 10000 elements.
     *
     * @param      sheet      the sheet of excel file.
     */
    private Sheet inputStatistic(Sheet sheet, Statistic statistic, int rowIndex){
        Row row = sheet.createRow(rowIndex);
        Cell sortNameCell = row.createCell(0);
        sortNameCell.setCellValue(statistic.getSortType().toString());
        List<Long> times = statistic.getTimes();
        for (int cellIterator = 0; cellIterator < times.size(); cellIterator++) {
            Cell sortTimeCell = row.createCell(cellIterator + 1);
            sortTimeCell.setCellValue(times.get(cellIterator));
        }
        return sheet;
    }

    /**
     * The private method that the method createReport use.
     * Return an sheet with line chart that generated on the values,
     * which contains from cell(1,1) to cell(4, 4).
     *
     * @param      sheet      the sheet of excel file.
     */
    private Sheet createChart(Sheet sheet){
        Row row = sheet.createRow(0);
        int cellIterator = 0;
        for (int arraySize = 10; arraySize <= ArraysFiller.minArraySize; arraySize *= 10)
            row.createCell(++cellIterator).setCellValue(arraySize);

        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = drawing.createAnchor(0,0,0, 0, 0, 6, 10, 16);

        Chart chart = drawing.createChart(anchor);
        ChartLegend chartLegend = chart.getOrCreateLegend();
        chartLegend.setPosition(LegendPosition.TOP);

        LineChartData chartData = chart.getChartDataFactory().createLineChartData();

        ChartAxis bottomAxis = chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(0, 0, 1, MATRIX_COLS));

        for (int i = 1; i <= MATRIX_ROWS; i++)
            chartData.addSeries(xs, DataSources.fromNumericCellRange(sheet, new CellRangeAddress(i, i, 1, MATRIX_COLS)));

        chart.plot(chartData, bottomAxis, leftAxis);

        return sheet;
    }

    private void setMinCols(){
        double counter = ArraysFiller.minArraySize;

        int cols = 0;

        for(cols = 0; counter > 1; cols ++)
            counter /= 10;

        MATRIX_COLS = cols;
    }
}
