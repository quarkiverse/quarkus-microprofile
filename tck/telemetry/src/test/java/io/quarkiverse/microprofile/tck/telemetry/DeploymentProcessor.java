package io.quarkiverse.microprofile.tck.telemetry;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import org.eclipse.microprofile.config.spi.ConfigSource;
import org.jboss.arquillian.container.test.spi.client.deployment.ApplicationArchiveProcessor;
import org.jboss.arquillian.test.spi.TestClass;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import io.quarkus.arc.Arc;
import io.smallrye.config.common.MapBackedConfigSource;

public class DeploymentProcessor implements ApplicationArchiveProcessor {
    @Override
    public void process(Archive<?> archive, TestClass testClass) {
        if (archive instanceof WebArchive war) {
            war.addAsServiceProvider(ConfigSource.class, TestConfigSource.class);
            war.addClass(ExecutorProvider.class);
        }
    }

    /**
     * Some tests do not set the exporter to use, so it uses the default exporter as described by the specification
     * (usually <code>otlp</code>), which requires an external server to be available. This source resets the
     * exporters.
     */
    public static class TestConfigSource extends MapBackedConfigSource {
        public TestConfigSource() {
            super("TestConfigSource",
                    Map.of("otel.traces.exporter", "none",
                            "otel.metrics.exporter", "none",
                            "otel.logs.exporter", "none"),
                    50);
        }
    }

    public static class ExecutorProvider implements Executor {
        private final ExecutorService executorService;

        public ExecutorProvider() {
            this.executorService = Arc.container().getExecutorService();
        }

        @Override
        public void execute(Runnable command) {
            executorService.execute(command);
        }
    }
}
