package cz.zcu.kiv.md2odt.web.service.log;

import cz.zcu.kiv.md2odt.web.dto.LogEntry;
import cz.zcu.kiv.md2odt.web.dto.LogEntryNew;
import cz.zcu.kiv.md2odt.web.service.LogCollector;
import cz.zcu.kiv.md2odt.web.service.StupidClientException;
import cz.zcu.kiv.md2odt.web.service.ThrowableRunnable;
import org.apache.log4j.*;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Implementation of {@link LogCollector}.
 *
 * @author Patrik Harag
 * @version 2017-05-03
 */
public class LogCollectorImpl implements LogCollector {

    private static final String PATTERN = "%r %c{1} - %m%n";

    private static int counter = 0;

    private static synchronized int newJobID() {
        return counter++;
    }


    @Override
    public LogEntryNew collectLogs(ThrowableRunnable runnable) {
        StringWriter consoleWriter = new StringWriter();
        MyPatternLayout layout = new MyPatternLayout(PATTERN);
        WriterAppender appender = new WriterAppender(layout, consoleWriter);

        appender.setName("JOB_" + newJobID());
        appender.setThreshold(Level.INFO);

        Logger.getRootLogger().addAppender(appender);

        final long start = System.currentTimeMillis();
        layout.setEpochStart(start);

        Exception exception = null;
        try {
            runnable.run();
        } catch (Exception e) {
            exception = e;
        }
        final long end = System.currentTimeMillis();

        Logger.getRootLogger().removeAppender(appender);
        String log = consoleWriter.toString();

        return new LogEntryNew(start, end, log, asString(exception), exception);
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

}
