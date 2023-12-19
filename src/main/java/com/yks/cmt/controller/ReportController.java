package com.yks.cmt.controller;
import com.yks.cmt.entity.Appointment;
import com.yks.cmt.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @GetMapping("/appointment")
    public void exportAppointment(HttpServletResponse response,@RequestParam(value = "FromDate") String FromDate,@RequestParam(value = "ToDate") String ToDate){
        List<Object[]> content = reportService.getAppointmentRecords(FromDate, ToDate);
        SXSSFWorkbook workbook = reportService.exportAppointment(content);
        String fileName = "surveyRecords-"+ UUID.randomUUID().toString();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName+".xlsx");

        try (OutputStream os = response.getOutputStream()){//从response中获取输出流
            workbook.write(os);
        } catch (IOException e) {
            log.info("IO Exception:{}",e.getStackTrace());
            e.printStackTrace();
        }catch (Exception ex) {
            log.info("Exception:{}",ex.getStackTrace());
            ex.printStackTrace();
        }
    }
}
