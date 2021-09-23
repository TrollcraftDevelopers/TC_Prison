package pl.trollcraft.prison.service.userInfo;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class UserInfo {

    private final UUID uuid;
    private final String lastNickname;

    private final Set<UserComponent> components;

    public UserInfo(UUID uuid, String lastNickname) {
        this.uuid = uuid;
        this.lastNickname = lastNickname;

        this.components = new HashSet<>();
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getLastNickname() {
        return lastNickname;
    }

    public Set<UserComponent> getComponents() {
        return components;
    }

    public<T> Optional<T> getComponent(Class<T> componentClass) {
        Optional<UserComponent> oComponent = this.components.stream()
                .filter( userComponent -> userComponent.getClass().equals(componentClass) )
                .findFirst();

        if (oComponent.isPresent()) {
            UserComponent component = oComponent.get();
            return Optional.of(componentClass.cast(component));
        }
        return Optional.empty();
    }

    public boolean hasComponent(Class<?> componentClass) {
        return this.components.stream()
                .anyMatch( userComponent -> userComponent.getClass().equals(componentClass) );
    }
}
