package io.quarkiverse.microprofile.telemetry;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Handler;

import org.eclipse.microprofile.config.ConfigProvider;
import org.jboss.logging.Logger;

import io.quarkus.runtime.RuntimeValue;
import io.quarkus.runtime.annotations.Recorder;
import io.quarkus.runtime.annotations.RuntimeInit;
import io.quarkus.runtime.configuration.AbstractConfigBuilder;
import io.quarkus.runtime.configuration.QuarkusConfigFactory;
import io.smallrye.config.ConfigMappingObject;
import io.smallrye.config.SmallRyeConfig;
import io.smallrye.config.SmallRyeConfigBuilder;
import io.smallrye.config.SmallRyeConfigBuilderCustomizer;

@Recorder
public class TelemetryRecorder {
    private static final Logger log = Logger.getLogger(TelemetryRecorder.class);

    @RuntimeInit
    public RuntimeValue<Optional<Handler>> initializeHandler() {
        log.info("TelemetryRecorder.initializeHandler");
        SmallRyeConfig config = ConfigProvider.getConfig().unwrap(SmallRyeConfig.class);

        // Maybe it is a different instance?
        SmallRyeConfig quarkusConfig = new QuarkusConfigFactory().getConfigFor(null, null);
        if (!config.equals(quarkusConfig)) {
            throw new IllegalStateException("SmallRyeConfig Classloaders mismatch!");
        }

        SmallRyeConfigBuilder staticBuilder = new SmallRyeConfigBuilder();
        AbstractConfigBuilder.withCustomizer(staticBuilder, "io.quarkus.runtime.generated.RunTimeConfig");
        staticBuilder.withCustomizers(new MissingMappingConfigBuilderCustomizer());
        //log.info("Static Mappings");
        //staticBuilder.build();

        // Is it missing from the customizer?
        SmallRyeConfigBuilder runtimeBuilder = new SmallRyeConfigBuilder();
        AbstractConfigBuilder.withCustomizer(runtimeBuilder, "io.quarkus.runtime.generated.RunTimeConfig");
        runtimeBuilder.withCustomizers(new MissingMappingConfigBuilderCustomizer());
        //log.info("Runtime Mappings");
        //runtimeBuilder.build();

        try {
            Field mappings = SmallRyeConfig.class.getDeclaredField("mappings");
            mappings.setAccessible(true);
            Map<Class<?>, Map<String, ConfigMappingObject>> map = (Map<Class<?>, Map<String, ConfigMappingObject>>) mappings
                    .get(config);
            for (Map.Entry<Class<?>, Map<String, ConfigMappingObject>> entry : map.entrySet()) {
                for (String prefix : entry.getValue().keySet()) {
                    log.info("Found in current config " + entry.getKey() + " with prefix " + prefix);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Does this fail even if present in the builder?
        config.getConfigMapping(TelemetryLogsExporterConfig.class);

        TelemetryLogsExporterConfig telemetryLogsExporterConfig = config.getConfigMapping(TelemetryLogsExporterConfig.class);
        Map<String, String> properties = telemetryLogsExporterConfig.exporter().getOrDefault("logging", Map.of());
        return new RuntimeValue<>(Optional.of(new RedirectOutHandler(properties)));
    }

    static class MissingMappingConfigBuilderCustomizer implements SmallRyeConfigBuilderCustomizer {
        @Override
        public void configBuilder(final SmallRyeConfigBuilder builder) {
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
}
