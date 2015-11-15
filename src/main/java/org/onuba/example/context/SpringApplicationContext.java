package org.onuba.example.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service("springAppContext")
public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext appContext;

    // Private constructor prevents instantiation from other classes
    private SpringApplicationContext() {

    }

    public void setApplicationContext(ApplicationContext paramApplicationContext) {
        appContext = paramApplicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        return (T) appContext.getBean(beanName);
    }
}
