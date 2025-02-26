package com.vmeknowledge.controller;

import com.vmeknowledge.common.Result;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Value("${ip:localhost}")
    String ip;

    @Value("${server.port}")
    String port;

    private static final String ROOT_PATH = System.getProperty("user.dir") + File.separator + "src/main/resources/files";

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return Result.error("文件不能为空");
        }
        String originalFilename = file.getOriginalFilename();
        int index = 0;
        if (originalFilename != null) {
            index = originalFilename.lastIndexOf(".");
        }
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;
        log.info("新的文件名: {}", newFileName);
        Path path = Paths.get(ROOT_PATH);
        if (!Files.exists(path)) {
            try {
                // 如果目录不存在，则尝试创建该目录
                Files.createDirectories(path);
                System.out.println("目录创建成功: " + ROOT_PATH);
            } catch (Exception e) {
                System.err.println("目录创建失败: " + ROOT_PATH);
                e.printStackTrace();
                return Result.error("目录创建失败");
            }
        }
        File saveFile = new File(ROOT_PATH + File.separator + newFileName);
        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            System.err.println("文件保存失败");
            e.printStackTrace();
            return Result.error("文件保存失败");
        }
        String url = "http://" + ip + ":" + port + "/download/" + newFileName;
        return Result.success(url);
    }

    @GetMapping("/download/{filename}")
    public void download(@PathVariable String filename, HttpServletResponse response) throws Exception{
        response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(filename, StandardCharsets.UTF_8));//附件下载
//        response.addHeader("Content-Disposition","inline;filename=" + URLEncoder.encode(filename, StandardCharsets.UTF_8));//预览文件
        Path filePath = Paths.get(ROOT_PATH + File.separator + filename);
        if (!Files.exists(filePath)){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
            return;
        }
        byte[] bytes = Files.readAllBytes(filePath);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }


}
