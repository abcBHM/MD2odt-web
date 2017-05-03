package cz.zcu.kiv.md2odt.web.service.log

import org.apache.log4j.Logger
import org.junit.Test

/**
 *
 * @author Patrik Harag
 * @version 2017-05-03
 */
class LogCollectorImplTest {

    private static final Logger LOGGER = Logger.getLogger(LogCollectorImplTest.class)

    def collector = new LogCollectorImpl()

    @Test
    void testLogging() {
        def logs = collector.collectLogs({
            LOGGER.info("test: 1 2 3")
            LOGGER.info("test: a b c")
        })

        assert logs.log.contains("test: 1 2 3")
        assert logs.log.contains("test: a b c")
    }

    @Test
    void testException() {
        def logs = collector.collectLogs({
            String something = null
            something.size()
        })

        assert logs.exceptionObj
    }

}