package io.quarkiverse.microprofile.tck.core;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

import org.jboss.arquillian.test.spi.TestEnricher;
import org.junit.jupiter.api.TestReporter;

public class TestReporterEnricher implements TestEnricher {
    @Override
    public void enrich(Object testCase) {

    }

    @Override
    public Object[] resolve(Method method) {
        if (method.getParameterCount() > 0) {
            Object[] parameters = new Object[method.getParameterCount()];
            Parameter[] methodParameters = method.getParameters();
            for (int i = 0, methodParametersLength = methodParameters.length; i < methodParametersLength; i++) {
                Parameter parameter = methodParameters[i];
                if (parameter.getType().equals(TestReporter.class)) {
                    parameters[i] = new TestReporter() {
                        @Override
                        public void publishEntry(Map<String, String> map) {

                        }
                    };
                }
            }
            return parameters;
        }
        return new Object[0];
    }
}
