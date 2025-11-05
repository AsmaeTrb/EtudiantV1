package com.example.etudiantv1;

import com.example.etudiantv1.Configuration.RsaKeys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(RsaKeys.class)
public class EtudiantV1Application {

    public static void main(String[] args) {
        SpringApplication.run(EtudiantV1Application.class, args);
    }

}
