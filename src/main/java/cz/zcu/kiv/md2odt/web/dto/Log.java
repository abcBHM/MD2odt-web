package cz.zcu.kiv.md2odt.web.dto;

import java.util.Date;

/**
 * @author Patrik Harag
 * @version 2017-04-14
 */
public class Log {

    private final int id;
    private final Date time;
    private String log;
    private String exception;

    public Log(String log, String exception) {
        // for new log
        this(-1, null, log, exception);
    }

    public Log(int id, Date time, String log, String exception) {
        // for log from db
        this.id = id;
        this.time = time;
        this.log = log;
        this.exception = exception;
    }

    public int getId() {
        return id;
    }

    public Date getTime() {
        return time;
    }

    public String getLog() {
        return log;
    }

    public String getException() {
        return exception;
    }

    @Override
    public String toString() {
        return "Log{'\n" + log + "'}";
    }

}
