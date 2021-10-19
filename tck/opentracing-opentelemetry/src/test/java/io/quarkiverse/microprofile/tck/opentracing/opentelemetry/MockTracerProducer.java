package io.quarkiverse.microprofile.tck.opentracing.opentelemetry;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.interceptor.Interceptor;

import io.opentracing.Tracer;

@ApplicationScoped
@Alternative
@Priority(Interceptor.Priority.APPLICATION + 10)
public class MockTracerProducer {
    @Default
    @Produces
    @Singleton
    public Tracer tracer() {
        return new MockTracer();
    }
}
