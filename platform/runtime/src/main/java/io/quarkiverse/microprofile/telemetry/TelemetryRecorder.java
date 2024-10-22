package io.quarkiverse.microprofile.telemetry;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Handler;

import org.eclipse.microprofile.config.ConfigProvider;

import io.quarkus.runtime.RuntimeValue;
import io.quarkus.runtime.annotations.Recorder;
import io.quarkus.runtime.annotations.RuntimeInit;
import io.smallrye.config.SmallRyeConfig;

@Recorder
public class TelemetryRecorder {
    @RuntimeInit
    public RuntimeValue<Optional<Handler>> initializeHandler() {
        SmallRyeConfig config = ConfigProvider.getConfig().unwrap(SmallRyeConfig.class);
        TelemetryLogsExporterConfig telemetryLogsExporterConfig = config.getConfigMapping(TelemetryLogsExporterConfig.class);
        Map<String, String> properties = telemetryLogsExporterConfig.exporter().getOrDefault("logging", Map.of());
        return new RuntimeValue<>(Optional.of(new RedirectOutHandler(properties)));
    }
}
