package io.quarkiverse.microprofile.telemetry;

import static io.smallrye.common.constraint.Assert.checkNotNullParam;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;

public class RedirectOutHandler extends Handler {
    private final PrintStream oldOut;
    private final PrintStream newOut;

    public RedirectOutHandler(Map<String, String> properties) {
        try {
            this.oldOut = System.out;
            this.newOut = new PrintStream(pattern(properties));
            System.setOut(newOut);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void publish(final LogRecord record) {

    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {
        newOut.close();
        System.setOut(oldOut);
    }

    private String pattern(final Map<String, String> properties) {
        String pattern = properties.get("pattern");
        if (pattern == null) {
            pattern = fromLogManager("pattern");
        }
        return pattern;
    }

    private String fromLogManager(final String property) {
        LogManager manager = LogManager.getLogManager();
        String className = this.getClass().getName();
        return checkNotNullParam(className + "." + property, manager.getProperty(className + "." + property));
    }
}
