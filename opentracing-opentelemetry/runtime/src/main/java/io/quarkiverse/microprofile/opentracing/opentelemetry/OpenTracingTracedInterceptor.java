package io.quarkiverse.microprofile.opentracing.opentelemetry;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.eclipse.microprofile.opentracing.Traced;

import io.opentracing.Tracer;

@Traced
@Interceptor
@Priority(value = Interceptor.Priority.LIBRARY_BEFORE + 1)
public class OpenTracingTracedInterceptor {
    @Inject
    Tracer tracer;

    @AroundInvoke
    public Object traced(InvocationContext ctx) throws Exception {

        System.out.println("OpenTracingTracedInterceptor.traced");
        return ctx.proceed();
    }
}
