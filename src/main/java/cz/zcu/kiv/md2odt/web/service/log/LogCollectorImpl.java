package cz.zcu.kiv.md2odt.web.service.log;

import cz.zcu.kiv.md2odt.web.dto.Log;
import cz.zcu.kiv.md2odt.web.service.LogCollector;
import cz.zcu.kiv.md2odt.web.service.LogStorage;
import cz.zcu.kiv.md2odt.web.service.StupidClientException;
import cz.zcu.kiv.md2odt.web.service.ThrowableRunnable;
import org.apache.log4j.*;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author Patrik Harag
 * @version 2017-04-15
 */
public class LogCollectorImpl implements LogCollector {

    private static final String PATTERN = "%d{HH:mm:ss,SSS} %c{1} - %m%n";

    private static int counter = 0;

    public static synchronized int getJobID() {
        return counter++;
    }


    private LogStorage savingService;

    public LogCollectorImpl(LogStorage savingService) {
        this.savingService = savingService;
    }

    @Override
    public void collectLogs(ThrowableRunnable runnable) throws Exception {
        StringWriter consoleWriter = new StringWriter();
        PatternLayout layout = new PatternLayout(PATTERN);
        WriterAppender appender = new WriterAppender(layout, consoleWriter);

        appender.setName("JOB_" + getJobID());
        appender.setThreshold(Level.INFO);

        Logger.getRootLogger().addAppender(appender);

        Exception exception = null;
        try {
            runnable.run();
        } catch (Exception e) {
            exception = e;
        }

        Logger.getRootLogger().removeAppender(appender);

        asyncSave(new Log(consoleWriter.toString(), asString(exception)));

        if (exception != null)
            throw exception;
    }

    private String asString(Exception e) {
        if (e == null)
            return null;
        else if (e instanceof StupidClientException)
            return e.toString();  // no more information needed
        else
            return stackTrace(e);
    }

    private String stackTrace(Exception e) {
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);

        e.printStackTrace(printWriter);

        printWriter.flush();
        return writer.toString();
    }

    private void asyncSave(Log log) {
        Thread thread = new Thread(() -> {
            savingService.add(log);
        }, "Log saving thread");

        thread.start();
    }

}
