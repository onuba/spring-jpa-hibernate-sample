package org.onuba.example.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

@Aspect
public class InoutLoggerExecutionAspect implements Ordered {

    /**
     * Logger de la aplicación, usando la libreria logback.
     */
    private static transient Logger LOGGER = null;

    private long threshold;
    private int order;

    /**
     * Metodo que traceará la entrada y la salida, en escenarios sin exceciones, de la ejecucion de cualquier método
     * publico del sistema alojado en el contenedro de Spring, además de medir el tiempo en milisegundos de ejecución.
     * 
     * @param pjp
     *            JoinPoint del aspecto.
     * @return
     * @throws Throwable
     */
    @Around("execution(* org.onuba.example.facade..*.*(..))")
    public Object inout(ProceedingJoinPoint pjp) throws Throwable {

        final MethodSignature signature = (MethodSignature) pjp.getSignature();
        final Method method = signature.getMethod();
        final Class<?> c = pjp.getTarget().getClass();
        final String cname = c.getName();
        final String mname = method.getName();
        LOGGER = LoggerFactory.getLogger(c);

        LOGGER.debug("Entrando en " + cname + "." + mname + "...");
        final long t0 = System.currentTimeMillis();
        final Object obj = pjp.proceed();
        final long t1 = System.currentTimeMillis();
        final long t = t1 - t0;
        LOGGER.debug("Saliendo de " + cname + "." + mname + "[" + t + "ms]...");

        if (t >= threshold) {
            LOGGER.warn(cname + "." + mname + " ha tardado " + t + "ms, mas que el threshold configurado de " + threshold + "ms.");
        }

        return obj;

    }

    public long getThreshold() {
        return this.threshold;
    }

    public void setThreshold(long threshold) {
        this.threshold = threshold;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
