package com.yks.cmt.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ReportService {
    @PersistenceContext
    private EntityManager em;
    public SXSSFWorkbook exportAppointment(List<Object[]> content){
        SXSSFWorkbook workbook = new SXSSFWorkbook(-1);
        SXSSFSheet sheet = workbook.createSheet("sheet1");
        String tableHeader[] = new String[]{"#","Name","Phone","Age","Gender","Doctor","Position","Department","Appointment Date","Status"};

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        SXSSFRow titleRow = sheet.createRow(0);
        for (int i=0;i<tableHeader.length;i++){
            SXSSFCell cell = titleRow.createCell(i);
            cell.setCellValue(tableHeader[i]);
            cell.setCellStyle(cellStyle);
        }

        List<Object[]> resultList = new ArrayList<>();
        int sn = 1;
        for (Object[] o:content
             ) {
            Object[] reportContent = new Object[]{
                    sn++,
                    getDisplay(o[0]),
                    getDisplay(o[1]),
                    getDisplay(o[2]),
                    getDisplay(o[3]),
                    getDisplay(o[4]),
                    getDisplay(o[5]),
                    getDisplay(o[6]),
                    getDisplay(o[7]),
                    getDisplay(o[8]),
            };
            resultList.add(reportContent);
        }

        for (int j = 0; j < resultList.size(); j++) {
            SXSSFRow row = sheet.createRow(j+1);
            Object[] objects = resultList.get(j);
            for (int k = 0; k < objects.length; k++) {
                SXSSFCell cell = row.createCell(k);
                cell.setCellStyle(cellStyle);
                Object object = objects[k];
                cell.setCellValue(object.toString());
            }
        }

        sheet.trackAllColumnsForAutoSizing();
        autoSizeColumns(workbook);
        return workbook;
    }

    public static String getDisplay(Object object){
        if (object == null){
            return "";
        }else{
            return object.toString();
        }
    }

    public List<Object[]> getAppointmentRecords(String fromDate, String toDate){
        String sql = "SELECT u.user_name ,u.phone ,u.age ,u.gender ,d.doctor_name ,p.position_name ,de.department_name , a.appointment_date ,a.appointment_status  FROM appointment a \n" +
                "LEFT JOIN web_user u ON a.user_id = u.user_id LEFT JOIN doctor d ON a.doctor_id = d.doctor_id \n" +
                "LEFT JOIN department de on de.department_id = d.department_id LEFT JOIN `position` p ON p.position_id = d.position_id  \n" +
                "WHERE (a.appointment_date BETWEEN '"+fromDate+"' AND '"+toDate+"' OR '"+fromDate+"' = '' OR '"+toDate+"' = '')";
        Query nativeQuery = em.createNativeQuery(sql);
        List<Object[]> resultList = new ArrayList<>();
        resultList = nativeQuery.getResultList();
        return resultList;
    }

    private static void autoSizeColumns(SXSSFWorkbook workbook) {
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            SXSSFSheet sheet = workbook.getSheetAt(i);
            if (sheet.getPhysicalNumberOfRows() > 0) {
                SXSSFRow row = sheet.getRow(0);
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    sheet.autoSizeColumn(columnIndex);
                    sheet.setColumnWidth(columnIndex,sheet.getColumnWidth(columnIndex) *10/7);
                }
            }
        }
    }
}
