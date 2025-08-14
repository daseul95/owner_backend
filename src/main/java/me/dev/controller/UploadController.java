package me.dev.controller;

import me.dev.service.S3Uploader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private final S3Uploader s3Uploader;

    public UploadController() {
        this.s3Uploader = new S3Uploader(
                ${accessKey},
                ${secretKey},
                software.amazon.awssdk.regions.Region.AP_NORTHEAST_2
        );
    }

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        File file = File.createTempFile("temp", multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);

        String keyName = "store/" + multipartFile.getOriginalFilename();
        return s3Uploader.upload(file, keyName);
    }
}
