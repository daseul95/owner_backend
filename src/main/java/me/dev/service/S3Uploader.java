package me.dev.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

@Service
public class S3Uploader {

    private final S3Client s3Client;
    private final String bucketName = "owner-image";


    public S3Uploader(@Value("${cloud.aws.credentials.access-key}")String accessKey,
                      @Value("${cloud.aws.credentials.secret-key}")String secretKey,@Value("${cloud.aws.region.static}") Region region) throws IOException{
        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
        s3Client = S3Client.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

    public String upload(MultipartFile file, String keyName) throws IOException {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .acl("public-read") // 공개 권한
                .build();
        MultipartFile multipartFile =file;
        File tempFile = File.createTempFile("prefix-", "-" + multipartFile.getOriginalFilename());

// MultipartFile 내용을 임시 파일에 복사
        multipartFile.transferTo(tempFile);

// File → Path
        Path path = tempFile.toPath();
        PutObjectResponse response = s3Client.putObject(request, path);

        if(response.sdkHttpResponse().isSuccessful()) {
            return "https://" + bucketName + ".s3.ap-northeast-2.amazonaws.com/" + keyName;
        } else {
            throw new RuntimeException("S3 Upload Failed");
        }
    }
}
