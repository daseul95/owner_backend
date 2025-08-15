package me.dev.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import static com.sun.jna.platform.win32.Kernel32Util.deleteFile;

@Service
@RequiredArgsConstructor
public class S3Service {

    private static final String[] ALLOWED_EXTS = {".jpg", ".jpeg", ".png", ".gif", ".webp"};
    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
            "image/jpeg", "image/png", "image/gif", "image/webp"
    );
    private static final long MAX_BYTES = 10 * 1024 * 1024; // 10MB
    private static final long MAX_PIXELS = 40_000_000;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final S3Client s3Client =  S3Client.builder()
            .region(Region.AP_NORTHEAST_2)
            .credentialsProvider(ProfileCredentialsProvider.create())
            .build();  

    public String uploadFile(String dirName, MultipartFile file) {
        try {
            validateImage(file);

            String ext = getExtension(file.getOriginalFilename());
            String fileName = createFileName(ext);
            String fileUrl = dirName.replaceAll("/+$", "") + "/" + fileName;

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(fileUrl)
                    .contentType(file.getContentType())
                    .acl("public-read") // 퍼블릭 읽기 권한
                    .contentLength(file.getSize())
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

            // S3 퍼블릭 URL 직접 생성 (공개 버킷 기준)
            return "https://" + bucket + ".s3." +  Region.AP_NORTHEAST_2.id() + ".amazonaws.com/" + fileUrl;

        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 중 에러가 발생했습니다.", e);
        }
    }

    public static String getExtension(String filename) {
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex == -1) throw new IllegalArgumentException("확장자가 없는 파일입니다.");
        return filename.substring(dotIndex).toLowerCase();
    }
    public static String buildS3Path(String dirName, String originalFilename) {
        String ext = getExtension(originalFilename);

        if (!isAllowedExt(ext)) {
            throw new IllegalArgumentException("허용되지 않는 파일 확장자: " + ext);
        }

        String safeFileName = UUID.randomUUID().toString() + ext;
        return dirName.replaceAll("/+$", "") + "/" + safeFileName;
        // 뒤쪽 불필요한 / 제거 후 붙이기
    }

    private void validateImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) throw new IllegalArgumentException("빈 파일");
        if (file.getSize() > MAX_BYTES) throw new IllegalArgumentException("파일이 너무 큽니다");

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("이미지 Content-Type 아님");
        }

        try (InputStream in = file.getInputStream()) {
            if (!looksLikeImageMagic(in)) throw new IllegalArgumentException("이미지 시그니처 아님");
        }

        try (InputStream in = file.getInputStream()) {
            BufferedImage img = ImageIO.read(in);
            if (img == null) throw new IllegalArgumentException("이미지 디코딩 불가");
            long pixels = (long) img.getWidth() * img.getHeight();
            if (pixels <= 0 || pixels > MAX_PIXELS) {
                throw new IllegalArgumentException("이미지 해상도 제한 초과");
            }
        }
    }

    private boolean looksLikeImageMagic(InputStream in) throws IOException {
        in.mark(32);
        byte[] head = in.readNBytes(16);
        in.reset();

        if (head.length >= 2 && (head[0] & 0xFF) == 0xFF && (head[1] & 0xFF) == 0xD8) return true; // JPEG
        if (head.length >= 8 && head[0] == (byte) 0x89 && head[1] == 'P' && head[2] == 'N' && head[3] == 'G') return true; // PNG
        if (head.length >= 4 && head[0] == 'G' && head[1] == 'I' && head[2] == 'F' && head[3] == '8') return true; // GIF
        if (head.length >= 12 &&
                head[0] == 'R' && head[1] == 'I' && head[2] == 'F' && head[3] == 'F' &&
                head[8] == 'W' && head[9] == 'E' && head[10] == 'B' && head[11] == 'P') return true; // WebP

        return false;
    }

    public String createFileName(String ext) {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
                + "-"
                + UUID.randomUUID().toString()
                + ext;
}
    // 허용 확장자 체크
    private static boolean isAllowedExt(String ext) {
        for (String allowed : S3Service.ALLOWED_EXTS) {
            if (allowed.equals(ext)) return true;
        }
        return false;
    }

    public void deleteFiles(List<String> fileUrls) {
            // 여러 파일을 한 번에 삭제할 때 사용해요
            for (String fileUrl : fileUrls) {
                deleteFile(fileUrl);
            }
        }
}