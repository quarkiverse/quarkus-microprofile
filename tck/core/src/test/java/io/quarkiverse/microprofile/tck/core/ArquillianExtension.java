package io.quarkiverse.microprofile.tck.core;

import org.jboss.arquillian.core.spi.LoadableExtension;
import org.jboss.arquillian.test.spi.TestEnricher;

public class ArquillianExtension implements LoadableExtension {
    @Override
    public void register(final ExtensionBuilder extensionBuilder) {
        extensionBuilder.observer(ArquillianLifecycle.class);
        extensionBuilder.service(TestEnricher.class, TestReporterEnricher.class);
    }
}
