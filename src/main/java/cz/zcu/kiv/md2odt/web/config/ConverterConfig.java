package cz.zcu.kiv.md2odt.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

/**
 *
 * @version 2017-04-21
 * @author Patrik Harag
 */
@Configuration
public class ConverterConfig {

    public static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;

    public static final long RESOURCES_LIMIT = 2*1024*1024;

    @Bean
    public Predicate<URL> resourceFilter() {
        final Set<String> protocols = new TreeSet<>(Arrays.asList("http", "https"));

        return url -> protocols.contains(url.getProtocol());
    }

}
