package com.banurr.pet_project.configuration;

import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class MinioConfig {

//    @Value("${minio.url}")
//    private String minioUrl;

//    @Value("${minio.access-key")
//    private String accessKey;

//    @Value("${minio.secret-key")
//    private String secretKey;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("CbHf6ebzFATccgVWwGXA", "FS8l763NEx8nappO0FcKC7rKhRBlyzVsfaUemVPx")
                .build();
    }
}
