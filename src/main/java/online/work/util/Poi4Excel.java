package online.work.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by 84825 on 2018/1/24.
 */
public class Poi4Excel {
    /**
     * 导出
     *
     * @param fileName
     * @param fileType
     * @param list
     * @return
     * @throws IOException
     */
    public static Workbook writer(String fileName, String fileType, ArrayList<ArrayList<String>> list) throws IOException {
        //创建工作文档对象
        Workbook workbook = null;
        if (fileType.equals("xls")) {
            workbook = new HSSFWorkbook();
        } else if (fileType.equals("xlsx")) {
            workbook = new XSSFWorkbook();
        } else {
            return workbook;
        }
        // 创建sheet对象
        Sheet sheet = (Sheet) workbook.createSheet("sheet1");
        if (null != list && list.size() > 0) {
            // 遍历数据
            for (int i = 0; i < list.size(); i++) {
                ArrayList<String> list1 = list.get(i);
                // 获取行对象
                Row row = (Row) sheet.createRow(i);
                for (int j = 0; j < list1.size(); j++) {
                    // 获取单元格对象
                    Cell cell = row.createCell(j);
                    // 给单元格设值
                    cell.setCellValue(list1.get(j));
                }
            }
        }
        // 返回工作文档对象
        return workbook;
    }

    /**
     * 导入
     *
     * @param stream
     * @param fileType
     * @return
     * @throws Exception
     */
    public static ArrayList<ArrayList<Object>> read(InputStream stream, String fileType) throws Exception {
        // 存放excel导出的数据信息
        ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
        Workbook workbook = null;
        // 创建工作文档对象
        if (fileType.equals("xls")) {
            workbook = new HSSFWorkbook(stream);
        } else if (fileType.equals("xlsx")) {
            workbook = new XSSFWorkbook(stream);
        } else {
            return new ArrayList<ArrayList<Object>>();
        }
        // 获取第一个sheet对象
        Sheet sheet = workbook.getSheetAt(0);
        // 获取行信息
        for (Row row : sheet) {
            // 存放行数据
            ArrayList<Object> list1 = new ArrayList<Object>();
            for (Cell cell : row) {
                Object str = null;
                try {
                    // 获取单元格信息
                    str = cell.getStringCellValue();
                } catch (Exception e) {
                    // 处理double数据
                    DecimalFormat df = new DecimalFormat("0");
                    str = df.format(cell.getNumericCellValue());
                }
                // 将单元格信息放在行数据中
                list1.add(str);
            }
            // 将行信息放到excel数据中
            list.add(list1);
        }
        workbook.close();
        return list;
    }
}
