package cz.zcu.kiv.md2odt.web.service;

import cz.zcu.kiv.md2odt.web.dto.LogEntry;

import java.util.List;

/**
 * Interface for object that can store {@link LogEntry}.
 *
 * @author Patrik Harag
 * @version 2017-04-15
 */
public interface LogStorage {

    /**
     * Returns true if the storage is persistent.
     *
     * @return boolean
     */
    boolean isPersistent();

    /**
     * Adds a log entry to the storage.
     *
     * @param log log entry
     */
    void add(LogEntry log);

    /**
     * Returns all log entries.
     *
     * @return log entries
     */
    List<LogEntry> getAll();

}
