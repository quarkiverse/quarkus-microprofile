package io.quarkiverse.microprofile.tck.opentracing.opentelemetry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.opentracing.Span;
import io.opentracing.tag.Tag;

public class MockSpan implements Span {
    private String operationName;
    private Map<String, Object> tags = new HashMap<>();

    public long startMicros() {
        return 0;
    }

    public long finishMicros() {
        return 0;
    }

    public String operationName() {
        return operationName;
    }

    public long parentId() {
        return 0;
    }

    @Override
    public io.opentracing.mock.MockSpan.MockContext context() {
        return new io.opentracing.mock.MockSpan.MockContext(0, 0, new HashMap<>());
    }

    public Map<String, Object> tags() {
        return tags;
    }

    public List<io.opentracing.mock.MockSpan.LogEntry> logEntries() {
        return new ArrayList<>();
    }

    @Override
    public Span setTag(final String key, final String value) {
        tags.put(key, value);
        return this;
    }

    @Override
    public Span setTag(final String key, final boolean value) {
        tags.put(key, value);
        return this;
    }

    @Override
    public Span setTag(final String key, final Number value) {
        tags.put(key, value);
        return this;
    }

    @Override
    public <T> Span setTag(final Tag<T> tag, final T value) {
        tag.set(this, value);
        tags.put(tag.getKey(), value);
        return this;
    }

    @Override
    public Span log(final Map<String, ?> fields) {
        return null;
    }

    @Override
    public Span log(final long timestampMicroseconds, final Map<String, ?> fields) {
        return null;
    }

    @Override
    public Span log(final String event) {
        return null;
    }

    @Override
    public Span log(final long timestampMicroseconds, final String event) {
        return null;
    }

    @Override
    public Span setBaggageItem(final String key, final String value) {
        return null;
    }

    @Override
    public String getBaggageItem(final String key) {
        return null;
    }

    @Override
    public Span setOperationName(final String operationName) {
        this.operationName = operationName;
        return this;
    }

    @Override
    public void finish() {

    }

    @Override
    public void finish(final long finishMicros) {

    }
}
