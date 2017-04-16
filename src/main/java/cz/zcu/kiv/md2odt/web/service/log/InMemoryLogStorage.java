package cz.zcu.kiv.md2odt.web.service.log;

import cz.zcu.kiv.md2odt.web.dto.LogEntry;
import cz.zcu.kiv.md2odt.web.service.LogStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Patrik Harag
 * @version 2017-04-15
 */
public class InMemoryLogStorage implements LogStorage {

    private final List<LogEntry> logs = new ArrayList<>();

    @Override
    public boolean isPersistent() {
        return false;
    }

    @Override
    public synchronized void add(LogEntry log) {
        logs.add(log);
    }

    @Override
    public synchronized List<LogEntry> getAll() {
        return Collections.unmodifiableList(logs);
    }
}
