package io.quarkiverse.microprofile.tck.opentracing;

import org.jboss.arquillian.container.test.spi.client.deployment.ApplicationArchiveProcessor;
import org.jboss.arquillian.test.spi.TestClass;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 * @author Pavol Loffay
 * @author Jan Martiska
 */
public class DeploymentProcessor implements ApplicationArchiveProcessor {
    @Override
    public void process(Archive<?> archive, TestClass testClass) {
        if (archive instanceof WebArchive) {
            WebArchive war = (WebArchive) archive;
            war.addClass(MockTracerProducer.class);
        }
    }
}
