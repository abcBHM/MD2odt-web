package cz.zcu.kiv.md2odt.web.service;

/**
 *
 * @author Patrik Harag
 * @version 2017-04-15
 */
public interface LogCollector {

    void collectLogs(ThrowableRunnable runnable) throws Exception;

}

