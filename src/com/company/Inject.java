package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Properties;

public class Inject {
    public <T> T inject(T object) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/com/company/properties.properties "));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Field[] oblasts = object.getClass().getDeclaredFields();
        for (Field oblast : oblasts) {
            Annotation[] remarks = oblast.getAnnotations();
            for (Annotation remark : remarks) {
                if (remark instanceof Autoinjection) {
                    String oblastClass = oblast.getType().toString();
                    oblastClass = oblastClass.substring(oblastClass.indexOf(" ") + 1);
                    String classToInject = properties.getProperty(oblastClass);
                    try {
                        oblast.set(object, Class.forName(classToInject).newInstance());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return object;
    }
}
