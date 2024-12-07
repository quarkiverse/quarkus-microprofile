package io.quarkiverse.microprofile.telemetry;

import java.util.Map;
import java.util.Set;

import org.jboss.logging.Logger;

import io.smallrye.config.SmallRyeConfigBuilder;
import io.smallrye.config.SmallRyeConfigBuilderCustomizer;

public class GlobalConfigCustomizer implements SmallRyeConfigBuilderCustomizer {
    private static final Logger log = Logger.getLogger(GlobalConfigCustomizer.class);

    @Override
    public void configBuilder(SmallRyeConfigBuilder builder) {
        log.info("Stack: ", new Throwable());

        Map<Class<?>, Set<String>> mappings = builder.getMappingsBuilder().getMappings();
        for (Map.Entry<Class<?>, Set<String>> entry : mappings.entrySet()) {
            for (String prefix : entry.getValue()) {
                log.info("Found mapping " + entry.getKey() + " with prefix " + prefix);
            }
        }
    }

    @Override
    public int priority() {
        return Integer.MAX_VALUE;
    }
}
