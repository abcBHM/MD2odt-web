package cz.zcu.kiv.md2odt.web.config;

import cz.zcu.kiv.md2odt.web.service.LogCollector;
import cz.zcu.kiv.md2odt.web.service.LogStorage;
import cz.zcu.kiv.md2odt.web.service.log.DatabaseLogStorage;
import cz.zcu.kiv.md2odt.web.service.log.InMemoryLogStorage;
import cz.zcu.kiv.md2odt.web.service.log.LogCollectorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 *
 * @author Patrik Harag
 * @version 2017-04-15
 */
@Configuration
public class LogConfig {

    @Autowired(required = false)
    private DataSource dataSource;

    @Autowired
    private ApplicationContext appContext;

    @Bean
    public LogStorage logSavingService() {
        if (dataSource != null)
            return new DatabaseLogStorage(dataSource);
        else
            // db is not connected
            return new InMemoryLogStorage();
    }

    @Bean
    public LogCollector logCollectingService() {
        LogStorage logStorage = appContext.getBean(LogStorage.class);
        return new LogCollectorImpl(logStorage);
    }

}
