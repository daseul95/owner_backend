package me.dev.service;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;
import java.net.URL;

public class S3Uploader {

    private final S3Client s3Client;
    private final String bucketName = "owner-image";

    public S3Uploader(String accessKey, String secretKey, Region region) {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
        s3Client = S3Client.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

    public String upload(File file, String keyName) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .acl("public-read") // 공개 권한
                .build();

        PutObjectResponse response = s3Client.putObject(request, file.toPath());

        if(response.sdkHttpResponse().isSuccessful()) {
            return "https://" + bucketName + ".s3.ap-northeast-2.amazonaws.com/" + keyName;
        } else {
            throw new RuntimeException("S3 Upload Failed");
        }
    }
}
