package cz.zcu.kiv.md2odt.web.service;

import cz.zcu.kiv.md2odt.web.dto.LogEntry;
import cz.zcu.kiv.md2odt.web.dto.LogEntryNew;

/**
 * Collects logs.
 *
 * @author Patrik Harag
 * @version 2017-05-03
 */
public interface LogCollector {

    /**
     * Evaluates {@link ThrowableRunnable} and creates {@link LogEntry}.
     *
     * @param runnable runnable
     * @return log entry
     */
    LogEntryNew collectLogs(ThrowableRunnable runnable);

}

