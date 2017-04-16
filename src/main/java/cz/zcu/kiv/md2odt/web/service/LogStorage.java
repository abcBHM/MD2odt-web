package cz.zcu.kiv.md2odt.web.service;

import cz.zcu.kiv.md2odt.web.dto.LogEntry;

import java.util.List;

/**
 *
 * @author Patrik Harag
 * @version 2017-04-15
 */
public interface LogStorage {

    boolean isPersistent();

    void add(LogEntry log);

    List<LogEntry> getAll();

}
