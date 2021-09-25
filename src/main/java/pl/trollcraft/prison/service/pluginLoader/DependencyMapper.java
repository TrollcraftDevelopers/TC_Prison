package pl.trollcraft.prison.service.pluginLoader;

import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class DependencyMapper {

    private static final Logger LOG
            = Logger.getLogger(DependencyMapper.class.getSimpleName());

    private final Map<Class<?>, Object> dependencies;

    public DependencyMapper() {
        this.dependencies = new HashMap<>();
    }

    public void registerDependency(Object dependency) {
        LOG.info(String.format("Registering dependency of class %s", dependency.getClass().getSimpleName()));
        this.dependencies.put(dependency.getClass(), dependency);
    }

    public void unregisterDependency(Class<?> dependencyClass) {
        this.dependencies.remove(dependencyClass);
    }

    public<T> Optional<T> getDependency(Class<T> dependencyAssignableClass) {
        Optional<Class<?>> c = dependencies.keySet()
                .stream()
                .filter(dependencyAssignableClass::isAssignableFrom)
                .findFirst();

        if (c.isPresent()) {

            Class<?> dependencyClass = c.get();
            if (this.dependencies.containsKey(dependencyClass)) {
                Object dependencyObject = this.dependencies.get(dependencyClass);
                T dependency = dependencyAssignableClass.cast(dependencyObject);
                return Optional.of(dependency);
            }

        }
        return Optional.empty();
    }
}
