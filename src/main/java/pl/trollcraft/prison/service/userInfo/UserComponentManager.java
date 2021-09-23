package pl.trollcraft.prison.service.userInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserComponentManager {

    private final Map<String, Class<? extends UserComponent>> componentsMap;

    public UserComponentManager() {
        this.componentsMap = new HashMap<>();
    }

    public void registerComponent(Class<? extends UserComponent> userComponentClass) {
        String componentName = userComponentClass.getSimpleName();
        componentsMap.put(componentName, userComponentClass);
    }

    public Optional<Class<? extends UserComponent>> getComponent(String componentName) {
        return Optional.ofNullable(componentsMap.get(componentName));
    }

}
