package cz.zcu.kiv.md2odt.web.service.log;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.spi.LoggingEvent;

/**
 *
 * @author Patrik Harag
 * @version 2017-04-16
 */
public class MyPatternLayout extends PatternLayout {

    private long epochStart;

    public MyPatternLayout(String pattern) {
        super(pattern);
    }

    public void setEpochStart(long epochStart) {
        this.epochStart = epochStart;
    }

    @Override
    protected PatternParser createPatternParser(String pattern) {
        return new MyPatternParser(pattern);
    }

    public class MyPatternParser extends PatternParser {

        MyPatternParser(String pattern) {
            super(pattern);
        }

        @Override
        protected void finalizeConverter(char c) {
            switch (c) {
                case 'r':
                    currentLiteral.setLength(0);
                    addConverter(new MyPatternConverter());
                    break;
                default:
                    super.finalizeConverter(c);
            }
        }
    }

    public class MyPatternConverter extends PatternConverter {

        @Override
        protected String convert(LoggingEvent evt) {
            return "" + (System.currentTimeMillis() - epochStart);
        }
    }

}