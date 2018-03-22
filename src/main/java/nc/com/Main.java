package nc.com;

import nc.com.analyzer.Analyzer;
import nc.com.analyzer.Reflection;
import nc.com.excel.ExcelReport;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer();
        try {
            analyzer.analyze(new Reflection());
            new ExcelReport(analyzer.analyze(new Reflection())).createReport();
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
