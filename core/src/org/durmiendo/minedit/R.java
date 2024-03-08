package org.durmiendo.minedit;

import arc.struct.Seq;
import arc.util.Log;
import arc.util.Reflect;
import mindustry.ctype.Content;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
public class R {

    public static Seq<Class<?>> getClasses(String packageName, Class<?> filter) throws ClassNotFoundException, IOException, URISyntaxException {
        Seq<Class<?>> classes = new Seq();
        String path = packageName.replace('.', '/');
        File directory = new File(ClassLoader.getSystemClassLoader().getResource(path).getFile());
        if (directory.exists()) {
            String[] files = directory.list();
            for (String file : files) {
                if (file.endsWith(".class")) {
                    String className = packageName + '.' + file.substring(0, file.length() - 6);
                    Class<?> clazz = null;
                    try {
                        clazz = Class.forName(className);
                    } catch (ClassNotFoundException e) {
                        Log.warn("getClasses error: " + e);
                    }
                    if (filter == null || filter.isAssignableFrom(clazz)) {
                        classes = classes.add(clazz);
                    }
                }
            }
        }
        return classes;
    }


    public static String getName(Field fi) {
        String r = null;
        try {
            fi.setAccessible(true);
            r = fi.getName();
            fi.setAccessible(false);
        } catch (Exception e) {
            Log.info("getName error: " + e);
        }
        return r;
    }
    public static Class getType(Field fi) {
        Class r;
        try {
            fi.setAccessible(true);
            r = fi.getType();
            fi.setAccessible(false);
        } catch (Exception e) {
            Log.info("getType error: " + e);
            r = fi.getDeclaringClass();
        }
        return r;
    }
    public static Object getField(Field fi, Object obj) {
        Object r = null;
        try {
            fi.setAccessible(true);
            r = fi.get(obj);
            fi.setAccessible(false);
        } catch (Exception e) {
            Log.info("getField error: " + obj.toString() + " " + getName(fi) + " " + e);
        }
        if (getType(fi).isPrimitive()) return r;
        Constructor c = null;
        if (obj == null) return r;
        Class<?> parent = obj.getClass().getSuperclass();
        while (parent != null) {
            if (parent == Object.class) break;
            if (parent == Comparable.class) break;
            try {
                c = parent.getDeclaredConstructor(null);
            } catch (NoSuchMethodException e) {}
            parent = parent.getSuperclass();
            if (parent == Content.class) break;
        }
        try {
            if (c == null) return r;
            r = c.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            Log.info("getField error: " + obj.toString() + " " + getName(fi) + " " + e);
        }
        return r;
    }
    public static void setField(Field fi, Object obj, Object value) {
        try {
            fi.setAccessible(true);
            fi.set(obj, value);
            fi.setAccessible(false);
        } catch (IllegalAccessException e) {
            Log.info("setField error: " + obj.toString() + " " + getName(fi) + " " + e);
        }
    }

    public static <T> T cloneObject(T object) {
        try {
            Class<T> clazz = (Class<T>) object.getClass();
            T copy = Reflect.invoke(clazz, "clone");
            return copy;
        } catch (Exception e) {
            Log.info("Clone error: " + object.toString() + " " + e);
        }
        return null;
    }

    public static Seq<Field> getFields(Class<?> clazz) {
        Seq<Field> fields = new Seq<>(clazz.getDeclaredFields());
        Class<?> parent = clazz.getSuperclass();
        while (parent != null) {
            fields.addAll(parent.getDeclaredFields());
            parent = parent.getSuperclass();
        }
        return fields;
    }
}
