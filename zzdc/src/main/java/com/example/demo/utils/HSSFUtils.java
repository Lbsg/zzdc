package com.example.demo.utils;

import com.example.demo.entity.ShopHistoryorderEntity;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by liubaoshuai_i on 2018/4/16.
 */
public class HSSFUtils {

    /**
     * 创建表格表头
     * @param workbook
     * @param worksheet
     * @param title
     */
    private static void setTitle(HSSFWorkbook workbook, HSSFSheet worksheet, String[] title) {
        HSSFRow row = worksheet.createRow(0);
        worksheet.setColumnWidth(3,17*256);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCell cell;
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
    }

    /**
     * 设置日期格式(不一定用的到)
     */
    public static void formatTime() {}

    /**
     * 生成EXCEL文件格式
     * @param workbook
     * @param fileName
     * @throws Exception
     */
    private static void makeExcelFiles(HSSFWorkbook workbook, String fileName, String month) throws Exception {
        String file_name = "G:/Test/" + month + fileName + ".xls";
        File file = new File("G:/Test");
        File[] fileArray = file.listFiles();
        List<String> nameList = new ArrayList<>();
        if (fileArray != null) {
            for (File item : fileArray) {
                nameList.add(item.getName());
            }
            if (nameList.contains(month + fileName + ".xls")) {
                throw new CommonException("该报表已存在");
            }else {
                FileOutputStream fos = new FileOutputStream(file_name);
                workbook.write(fos);
                fos.flush();
                fos.close();
            }
        }else {
            FileOutputStream fos = new FileOutputStream(file_name);
            workbook.write(fos);
            fos.flush();
            fos.close();
        }
    }

    /**
     * 浏览器下载EXCEL文件
     * @param workbook
     * @param fileName
     * @param resp
     * @throws IOException
     */
    public static void downloadFiles(HSSFWorkbook workbook, String fileName, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/vnd.ms-excel;charset=utf-8");
        resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        OutputStream ops = resp.getOutputStream();
        workbook.write(ops);
        ops.flush();
        ops.close();
    }

    /**
     * 获得最终表格
     * @param dataList
     * @param title
     * @param filename
     * @throws Exception
     * @return
     */
    public static HSSFWorkbook getExcel(List<ShopHistoryorderEntity> dataList, String[] title, String filename, String month) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        int rowNumber = 1;
        HSSFSheet sheet = workbook.createSheet("月报表");
        setTitle(workbook, sheet, title);
        for (ShopHistoryorderEntity item : dataList) {
            HSSFRow row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(item.getId());
            row.createCell(1).setCellValue(item.getShopname());
            row.createCell(2).setCellValue(item.getShopdishes());
            row.createCell(3).setCellValue(item.getMonthcount());
        }
        makeExcelFiles(workbook, filename, month);
        return workbook;
    }
}
