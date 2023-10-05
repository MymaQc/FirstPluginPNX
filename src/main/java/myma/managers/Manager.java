package myma.managers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public interface Manager {

    void onEnable();

    void onDisable();

    static <T extends Manager> T getInstance(Class<T> managerClass) {
        try {
            Constructor<T> constructor = managerClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

}
