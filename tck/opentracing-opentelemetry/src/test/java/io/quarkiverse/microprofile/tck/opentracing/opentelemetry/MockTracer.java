package io.quarkiverse.microprofile.tck.opentracing.opentelemetry;

import java.util.ArrayList;
import java.util.List;

import io.opentracing.Scope;
import io.opentracing.ScopeManager;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;

public class MockTracer implements Tracer {
    private final List<MockSpan> finishedSpans = new ArrayList<>();

    public List<MockSpan> finishedSpans() {
        return finishedSpans;
    }

    public void appendFinishedSpan(MockSpan mockSpan) {
        this.finishedSpans.add(mockSpan);
    }

    public void reset() {
        this.finishedSpans.clear();
    }

    @Override
    public ScopeManager scopeManager() {
        return null;
    }

    @Override
    public Span activeSpan() {
        return null;
    }

    @Override
    public Scope activateSpan(final Span span) {
        return null;
    }

    @Override
    public SpanBuilder buildSpan(final String operationName) {
        return null;
    }

    @Override
    public <C> void inject(final SpanContext spanContext, final Format<C> format, final C carrier) {

    }

    @Override
    public <C> SpanContext extract(final Format<C> format, final C carrier) {
        return null;
    }

    @Override
    public void close() {

    }
}
