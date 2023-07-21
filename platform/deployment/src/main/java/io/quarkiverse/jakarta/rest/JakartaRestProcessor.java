package io.quarkiverse.jakarta.rest;

import org.jboss.resteasy.plugins.providers.FormUrlEncodedProvider;
import org.jboss.resteasy.plugins.providers.JaxrsFormProvider;

import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.resteasy.common.runtime.providers.ServerFormUrlEncodedProvider;
import io.quarkus.resteasy.common.spi.ResteasyJaxrsProviderBuildItem;

class JakartaRestProcessor {
    @BuildStep
    void providers(BuildProducer<ResteasyJaxrsProviderBuildItem> providers) {
        providers.produce(new ResteasyJaxrsProviderBuildItem(ServerFormUrlEncodedProvider.class.getName()));
        providers.produce(new ResteasyJaxrsProviderBuildItem(FormUrlEncodedProvider.class.getName()));
        providers.produce(new ResteasyJaxrsProviderBuildItem(JaxrsFormProvider.class.getName()));
    }
}
