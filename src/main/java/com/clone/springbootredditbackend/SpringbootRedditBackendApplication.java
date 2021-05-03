package com.clone.springbootredditbackend;

import com.clone.springbootredditbackend.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class SpringbootRedditBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedditBackendApplication.class, args);
    }

}
