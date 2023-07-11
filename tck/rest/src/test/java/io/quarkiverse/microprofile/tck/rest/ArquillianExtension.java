package io.quarkiverse.microprofile.tck.rest;

import org.jboss.arquillian.core.spi.LoadableExtension;

public class ArquillianExtension implements LoadableExtension {
    @Override
    public void register(final ExtensionBuilder extensionBuilder) {
        extensionBuilder.observer(ArquillianLifecycle.class);
    }
}
