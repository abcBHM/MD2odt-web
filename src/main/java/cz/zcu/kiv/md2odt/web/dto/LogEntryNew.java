package cz.zcu.kiv.md2odt.web.dto;

/**
 * Data object that contains information about processed job.
 *
 * @author Patrik Harag
 * @version 2017-05-03
 */
public class LogEntryNew extends LogEntry {

    private final Exception e;

    public LogEntryNew(long start, long end, String log, String eStr, Exception eObj) {
        super(-1, start, end, log, eStr);
        this.e = eObj;
    }

    /**
     * Returns exception as a string or null if no (fatal) exception occurred.
     *
     * @return exception
     */
    public Exception getExceptionObj() {
        return e;
    }

}
