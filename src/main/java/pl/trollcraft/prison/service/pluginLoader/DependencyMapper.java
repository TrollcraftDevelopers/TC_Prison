package pl.trollcraft.prison.service.pluginLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DependencyMapper {

    private final Map<Class<?>, Object> dependencies;

    public DependencyMapper() {
        this.dependencies = new HashMap<>();
    }

    public void registerDependency(Object dependency) {
        this.dependencies.put(dependency.getClass(), dependency);
    }

    public void unregisterDependency(Class<?> dependencyClass) {
        this.dependencies.remove(dependencyClass);
    }

    public<T> Optional<T> getDependency(Class<T> dependencyClass) {
        if (this.dependencies.containsKey(dependencyClass)) {

            Object dependencyObject = this.dependencies.get(dependencyClass);
            T dependency = dependencyClass.cast(dependencyObject);
            return Optional.of(dependency);

        }
        else {
            return Optional.empty();
        }
    }
}
