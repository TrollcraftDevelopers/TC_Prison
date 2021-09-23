package pl.trollcraft.prison.utility;


import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public final class ReflectionUtility {

    public static <T> Optional<T> instantiate(Class<? extends T> clazz) {
        T t = null;
        try {
            t = clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(t);
    }

    public static Optional<Class<?>> getClassByName (String name) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(clazz);
    }

    public static<T> boolean isOfClass(Class<?> clazz, Class<? extends T> type) {
        try {
            type.cast(clazz);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
