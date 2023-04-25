package com.redeheavy.heavycore.platform.bukkit.utils;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {
    public static Class<?> getNmsClass(final String name) {
        final String className = "net.minecraft.server." + getVersion() + "." + name;
        return getClass(className);
    }

    public static Class<?> getCbClass(final String name) {
        final String className = "org.bukkit.craftbukkit." + getVersion() + "." + name;
        return getClass(className);
    }

    public static Class<?> getUtilClass(final String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException ex) {
            try {
                return Class.forName("net.minecraft.util." + name);
            } catch (ClassNotFoundException ex2) {
                return null;
            }
        }
    }

    public static String getVersion() {
        final String packageName = Bukkit.getServer().getClass().getPackage().getName();
        return packageName.substring(packageName.lastIndexOf(46) + 1);
    }

    public static Object getHandle(final Object wrapper) {
        final Method getHandle = makeMethod(wrapper.getClass(), "getHandle", (Class<?>[]) new Class[0]);
        return callMethod(getHandle, wrapper, new Object[0]);
    }

    public static Method makeMethod(final Class<?> clazz, final String methodName, final Class<?>... paramaters) {
        try {
            return clazz.getDeclaredMethod(methodName, paramaters);
        } catch (NoSuchMethodException ex2) {
            return null;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> T callMethod(final Method method, final Object instance, final Object... paramaters) {
        if (method == null) {
            throw new RuntimeException("No such method");
        }
        method.setAccessible(true);
        try {
            return (T) method.invoke(instance, paramaters);
        } catch (InvocationTargetException ex) {
            throw new RuntimeException(ex.getCause());
        } catch (Exception ex2) {
            throw new RuntimeException(ex2);
        }
    }

    public static <T> Constructor<T> makeConstructor(final Class<?> clazz, final Class<?>... paramaterTypes) {
        try {
            return (Constructor<T>) clazz.getConstructor(paramaterTypes);
        } catch (NoSuchMethodException ex2) {
            return null;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> T callConstructor(final Constructor<T> constructor, final Object... paramaters) {
        if (constructor == null) {
            throw new RuntimeException("No such constructor");
        }
        constructor.setAccessible(true);
        try {
            return constructor.newInstance(paramaters);
        } catch (InvocationTargetException ex) {
            throw new RuntimeException(ex.getCause());
        } catch (Exception ex2) {
            throw new RuntimeException(ex2);
        }
    }

    public static Field makeField(final Class<?> clazz, final String name) {
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException ex2) {
            return null;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> T getField(final Field field, final Object instance) {
        if (field == null) {
            throw new RuntimeException("No such field");
        }
        field.setAccessible(true);
        try {
            return (T) field.get(instance);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void setField(final Field field, final Object instance, final Object value) {
        if (field == null) {
            throw new RuntimeException("No such field");
        }
        field.setAccessible(true);
        try {
            field.set(instance, value);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Class<?> getClass(final String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException ex) {
            return null;
        }
    }

    public static <T> Class<? extends T> getClass(final String name, final Class<T> superClass) {
        try {
            return Class.forName(name).asSubclass(superClass);
        } catch (ClassCastException | ClassNotFoundException ex3) {
            final Exception ex2;
            final Exception ex = ex3;
            return null;
        }
    }

    public static Field getOnlyField(final Class<?> toGetFrom, final Class<?> type) {
        Field only = null;
        for (final Field field : toGetFrom.getDeclaredFields()) {
            if (type.isAssignableFrom(field.getClass())) {
                Preconditions.checkArgument(only == null, "More than one field of type %s on %s: %s and %s", (Object) type.getSimpleName(), (Object) toGetFrom.getSimpleName(), (Object) field.getName(), (Object) only.getName());
                only = field;
            }
        }
        return only;
    }

    public static Method getOnlyMethod(final Class<?> toGetFrom, final Class<?> returnType, final Class<?>... paramSpec) {
        Method only = null;
        for (final Method method : toGetFrom.getDeclaredMethods()) {
            if (returnType.isAssignableFrom(method.getReturnType())) {
                if (isParamsMatchSpec(method.getParameterTypes(), paramSpec)) {
                    Preconditions.checkArgument(only == null, "More than one method matching spec on %s" + (only.getName().equals(method.getName()) ? "" : (": " + only.getName() + " " + method.getName())), (Object) toGetFrom.getSimpleName());
                    only = method;
                }
            }
        }
        return only;
    }

    public static boolean isParamsMatchSpec(final Class<?>[] parameters, final Class<?>... paramSpec) {
        if (parameters.length > paramSpec.length) {
            return false;
        }
        for (int i = 0; i < paramSpec.length; ++i) {
            final Class<?> spec = paramSpec[i];
            if (spec != null) {
                final Class parameter = parameters[i];
                if (!spec.isAssignableFrom(parameter)) {
                    return false;
                }
            }
        }
        return true;
    }
}
