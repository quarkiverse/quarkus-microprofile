package io.quarkiverse.microprofile.telemetry;

import java.util.Map;

import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithParentName;

@ConfigRoot(phase = ConfigPhase.RUN_TIME)
@ConfigMapping(prefix = "quarkus.otel.logs.exporter")
public interface TelemetryLogsExporterConfig {
    /**
     * Logs Exporter configuration.
     */
    @WithParentName
    Map<String, Map<String, String>> exporter();
}
