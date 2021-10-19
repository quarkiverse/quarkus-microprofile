package io.quarkiverse.microprofile.tck.opentracing.opentelemetry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.enterprise.inject.spi.CDI;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.opentracingshim.OpenTracingShim;
import io.opentelemetry.sdk.trace.ReadableSpan;
import io.opentelemetry.sdk.trace.data.SpanData;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.tag.Tags;

@Provider
public class MockTracerFilter implements ContainerResponseFilter {
    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext) {
        OpenTelemetry openTelemetry = GlobalOpenTelemetry.get();
        Tracer tracerShim = OpenTracingShim.createTracerShim(openTelemetry);
        ReadableSpan span = getReadableSpan(tracerShim.activeSpan());
        SpanData spanData = span.toSpanData();

        MockTracer mockTracer = (MockTracer) CDI.current().select(Tracer.class).get();
        MockSpan mockSpan = new MockSpan();

        if (spanData.getName().equals("rest/tracer/clearTracer")) {
            return;
        }

        mockSpan.setOperationName(spanData.getName());
        mockSpan.setTag(Tags.SPAN_KIND, spanData.getKind().name().toLowerCase());
        // This is written by Vert.x, but because the filter executes first is not yet populated
        mockSpan.setTag(Tags.HTTP_STATUS, responseContext.getStatus());
        spanData.getAttributes().forEach((key, value) -> mockSpan.setTag(key.getKey(), value.toString()));

        mockTracer.appendFinishedSpan(mockSpan);
    }

    private ReadableSpan getReadableSpan(final Span span) {
        try {
            Method method = span.getClass().getDeclaredMethod("getSpan");
            method.setAccessible(true);
            return (ReadableSpan) method.invoke(span);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
