package com.yks.cmt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
public class CommonController {
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        // 1.获取上传文件的文件名
        String filename = file.getOriginalFilename();
        // 2.获取上传的文件的后缀名
        String suffixName = filename.substring(filename.lastIndexOf("."));
        // 3.将UUID作文文件
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        // 4.将uuid和后缀名拼接后的结果作为最终的文件名
        filename = uuid + suffixName;
        // 5.serlvetContext获取服务器中photo目录的路程
        ServletContext servletContext = request.getServletContext();
        String realPath = servletContext.getRealPath("/image");
        System.out.println(realPath);
        File folder = new File(realPath);
        if(!folder.exists()){
            folder.mkdir();
        }
        String finalPath = realPath + File.separator + filename;
        System.out.println(finalPath);
        file.transferTo(new File(finalPath));
        return ResponseEntity.ok("http://127.0.0.1:9090/image/"+filename);
    }

}
