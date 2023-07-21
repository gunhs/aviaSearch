package ru.sharanov.aviasearch.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class FileJSON {
        @Value("${userBucket.path}")
        private String path;
}
