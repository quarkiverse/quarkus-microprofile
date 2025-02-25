package io.quarkiverse.microprofile.tck.core;

import org.jboss.arquillian.container.spi.event.container.BeforeDeploy;
import org.jboss.arquillian.core.api.annotation.Observes;
import org.jboss.arquillian.test.spi.TestClass;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public class ArquillianLifecycle {
    public void beforeDeploy(@Observes BeforeDeploy event, TestClass testClass) {
        Archive<?> archive = event.getDeployment().getArchive();
        if (archive instanceof WebArchive) {
            ((WebArchive) archive).addClass(JsonbProducer.class);
        }
    }
}
