package io.quarkiverse.microprofile.telemetry;

import java.util.List;

import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.LogHandlerBuildItem;
import io.quarkus.opentelemetry.runtime.config.build.OTelBuildConfig;

public class TelemetryProcessor {
    @BuildStep
    @Record(ExecutionTime.RUNTIME_INIT)
    void build(
            final TelemetryRecorder recorder,
            final OTelBuildConfig oTelBuildConfig,
            final BuildProducer<LogHandlerBuildItem> logHandlerBuildItem) {

        if (oTelBuildConfig.enabled()) {
            List<String> exporters = oTelBuildConfig.logs().exporter();
            if (exporters.contains("logging")) {
                logHandlerBuildItem.produce(new LogHandlerBuildItem(recorder.initializeHandler()));
            }
        }
    }
}
