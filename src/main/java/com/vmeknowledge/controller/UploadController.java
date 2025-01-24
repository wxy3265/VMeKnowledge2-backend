package com.vmeknowledge.controller;


import com.vmeknowledge.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @PostMapping("/upload")
    public Result upload(String username , Integer age , MultipartFile image) throws Exception {
        log.info("文件上传: {}, {}, {}",username, age, image);
        String originalFilename = image.getOriginalFilename();
        int index = 0;
        if (originalFilename != null) {
            index = originalFilename.lastIndexOf(".");
        }
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;
        log.info("新的文件名: {}", newFileName);

        image.transferTo(new File("D:\\Code\\image\\" + newFileName));
        return Result.success();
    }


}
