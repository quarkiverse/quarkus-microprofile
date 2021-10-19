package io.quarkiverse.microprofile.opentracing.opentelemetry;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.opentracingshim.OpenTracingShim;
import io.opentracing.Tracer;

@Singleton
public class OpenTracingTracerProducer {
    @Inject
    OpenTelemetry openTelemetry;

    @Default
    @Produces
    @Singleton
    Tracer tracer() {
        return OpenTracingShim.createTracerShim(openTelemetry);
    }
}
