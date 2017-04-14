package cz.zcu.kiv.md2odt.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.io.IOException;

/**
 *
 * @version 2017-04-14
 * @author Patrik Harag
 */
@Configuration
public class FileUploadConfig {

    public static final long MAX_UPLOAD_SIZE = 200 * 1024;
    public static final String DEFAULT_ENCODING = "UTF-8";

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() throws IOException {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(MAX_UPLOAD_SIZE);
        resolver.setDefaultEncoding(DEFAULT_ENCODING);


        resolver.setUploadTempDir(new FileSystemResource(getTempFolder()));

        return new CommonsMultipartResolver();
    }

    private File getTempFolder() {
        File buildFolder = new File("target");
        if (buildFolder.isDirectory())
            return new File("target/web-app-temp");

        return new File("temp");
    }

}
