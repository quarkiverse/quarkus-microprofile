package io.quarkiverse.microprofile.opentracing.opentelemetry.deployment;

import io.opentracing.Tracer;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.deployment.UnremovableBeanBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;

public class OpenTracingProcessor {
    @BuildStep
    void registerBeans(
            BuildProducer<AdditionalBeanBuildItem> additionalBeans,
            BuildProducer<UnremovableBeanBuildItem> unremovableBeans) {

        //additionalBeans.produce(new AdditionalBeanBuildItem(OpenTracingTracerProducer.class));
        //additionalBeans.produce(new AdditionalBeanBuildItem(OpenTracingTracedInterceptor.class));

        unremovableBeans.produce(UnremovableBeanBuildItem.beanTypes(Tracer.class));
    }
}
