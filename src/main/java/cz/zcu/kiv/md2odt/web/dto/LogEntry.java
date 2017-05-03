package cz.zcu.kiv.md2odt.web.dto;

/**
 * Data object that contains information about processed job.
 *
 * @author Patrik Harag
 * @version 2017-05-03
 */
public class LogEntry {

    private final int id;
    private final long startTime;
    private final long endTime;
    private String log;
    private String exception;

    public LogEntry(int id, long start, long end, String log, String exception) {
        this.id = id;
        this.startTime = start;
        this.endTime = end;
        this.log = log;
        this.exception = exception;
    }

    /**
     * Unique id of this log entry.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns time when the logging has started (UNIX time).
     *
     * @return time
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * Returns time when the logging has ended (UNIX time).
     *
     * @return time
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Returns log.
     *
     * @return log
     */
    public String getLog() {
        return log;
    }

    /**
     * Returns exception as a string or null if no (fatal) exception occurred.
     *
     * @return exception
     */
    public String getException() {
        return exception;
    }

}
