package io.quarkiverse.microprofile.tck.core;

import jakarta.annotation.Priority;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.json.spi.JsonProvider;

import io.quarkus.arc.DefaultBean;

/**
 * Override {@link io.quarkus.jsonb.JsonbProducer} because TCK {@link JsonbBuilder} returns <code>null</code> for
 * {@link JsonbBuilder#withProvider(JsonProvider)} and {@link JsonbBuilder#withConfig(JsonbConfig)}.
 */
@Singleton
public class JsonbProducer {
    @Produces
    @Singleton
    @DefaultBean
    @Priority(1)
    public Jsonb jsonb(JsonbConfig jsonbConfig) {
        return JsonbBuilder.newBuilder().build();
    }
}
