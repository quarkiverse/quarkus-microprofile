package io.quarkiverse.microprofile.tck.rest;

import java.lang.reflect.Method;

import org.jboss.arquillian.container.spi.event.container.BeforeDeploy;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.core.api.annotation.Observes;
import org.jboss.arquillian.test.spi.TestClass;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public class ArquillianLifecycle {
    public void beforeDeploy(@Observes BeforeDeploy event, TestClass testClass) {
        Archive<?> archive = event.getDeployment().getArchive();
        if (archive instanceof WebArchive) {
            int port = 0;
            // Some tests have multiple deployments, which will cause the second deployment to fail due to port already
            // being, so we match the main deployment to fix the port and let it be random for everything else
            Method[] declaredMethods = testClass.getJavaClass().getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                if (declaredMethod.isAnnotationPresent(Deployment.class)) {
                    Deployment deployment = declaredMethod.getAnnotation(Deployment.class);
                    if (event.getDeployment().getName().equals(deployment.name())) {
                        port = 8081;
                    }
                }
            }

            WebArchive war = (WebArchive) archive;
            String name = war.getName();
            if (name.endsWith(".war")) {
                name = name.substring(0, name.length() - 4);
            }
            war.addAsResource(new StringAsset(
                    "quarkus.http.test-port=" + port + "\n" +
                            "quarkus.http.root-path=/" + name + "\n"),
                    "application.properties");
        }
    }
}
