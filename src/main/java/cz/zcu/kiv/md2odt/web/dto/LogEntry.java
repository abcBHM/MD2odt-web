package cz.zcu.kiv.md2odt.web.dto;

/**
 *
 * @author Patrik Harag
 * @version 2017-04-16
 */
public class LogEntry {

    private final int id;
    private final long startTime;
    private final long endTime;
    private String log;
    private String exception;

    public LogEntry(long start, long end, String log, String exception) {
        // for new log
        this(-1, start, end, log, exception);
    }

    public LogEntry(int id, long start, long end, String log, String exception) {
        // for log from db
        this.id = id;
        this.startTime = start;
        this.endTime = end;
        this.log = log;
        this.exception = exception;
    }

    public int getId() {
        return id;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public String getLog() {
        return log;
    }

    public String getException() {
        return exception;
    }

}
