package io.quarkiverse.microprofile.telemetry;

import java.util.HashMap;
import java.util.Map;

import org.jboss.resteasy.spi.concurrent.ThreadContext;

import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;

/**
 * Remove when Quarkus supports this.
 */
@Deprecated
public class TelemetryResteasyThreadContext implements ThreadContext<Map<String, Object>> {
    @Override
    public Map<String, Object> capture() {
        Map<String, Object> context = new HashMap<>();
        context.put("context", Context.current());
        return context;
    }

    @Override
    public void push(final Map<String, Object> context) {
        Context current = (Context) context.get("context");
        Scope scope = current.makeCurrent();
        context.put("scope", scope);
    }

    @Override
    public void reset(final Map<String, Object> context) {
        Scope scope = (Scope) context.get("scope");
        scope.close();
        context.clear();
    }
}
