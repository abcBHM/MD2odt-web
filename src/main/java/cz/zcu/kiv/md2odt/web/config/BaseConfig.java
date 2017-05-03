package cz.zcu.kiv.md2odt.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Basic configuration.
 *
 * @author Patrik Harag
 * @version 2017-04-14
 */
@Configuration
@ComponentScan(basePackages = {
        "cz.zcu.kiv.md2odt.web.config",
        "cz.zcu.kiv.md2odt.web.controller",
        "cz.zcu.kiv.md2odt.web.service",
})
public class BaseConfig {

}
