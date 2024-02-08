package io.quarkiverse.microprofile.tck.telemetry;

import org.jboss.arquillian.core.spi.LoadableExtension;

public class ArquillianExtension implements LoadableExtension {
    @Override
    public void register(final ExtensionBuilder builder) {
        builder.observer(ArquillianLifecycle.class);
    }
}
