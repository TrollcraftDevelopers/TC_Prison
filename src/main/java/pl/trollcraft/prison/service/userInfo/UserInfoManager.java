package pl.trollcraft.prison.service.userInfo;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import pl.trollcraft.prison.constants.ExceptionConstants;
import pl.trollcraft.prison.service.configuration.Configurator;
import pl.trollcraft.prison.utility.ReflectionUtility;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

public class UserInfoManager {

    private final static Logger LOG = Logger.getLogger(UserInfoManager.class.getSimpleName());

    private final Plugin plugin;
    private final UserComponentManager userComponentManager;
    private final Set<UserInfo> userInfoSet;

    public UserInfoManager(Plugin plugin, UserComponentManager userComponentManager) {
        this.plugin = plugin;
        this.userComponentManager = userComponentManager;
        this.userInfoSet = new HashSet<>();
    }

    public void saveUser(UserInfo userInfo) {
        String fileName = String.format("users/%s.yml", userInfo.getUUID().toString());
        Configurator configurator = new Configurator(plugin, fileName, false);
        configurator.write("lastNickname", userInfo.getLastNickname());
        userInfo.getComponents().forEach( userComponent -> userComponent.save(configurator) );
        configurator.save();
    }

    public UserInfo loadUserInfo(Player player) {
        UUID uuid = player.getUniqueId();
        String lastNickname = player.getName();

        String fileName = String.format("users/%s.yml", uuid);
        Configurator configurator = new Configurator(plugin, fileName, false);

        UserInfo userInfo = new UserInfo(uuid, lastNickname);
        if (configurator.exists("components")) {

            configurator.keys("components").forEach( componentClassName -> {

                Optional<Class<? extends UserComponent>> oComponentClass = this.userComponentManager.getComponent(componentClassName);
                if (oComponentClass.isPresent()) {

                    Class<? extends UserComponent> componentClass = oComponentClass.get();
                    Optional<UserComponent> oUserComponent = ReflectionUtility.instantiate(componentClass);
                    if (oUserComponent.isPresent()) {
                        UserComponent userComponent = oUserComponent.get();
                        userComponent.load(configurator);
                        userInfo.getComponents().add(userComponent);
                    }
                    else {
                        LOG.warning(String.format(ExceptionConstants.USER_INFO_FAILED_TO_INSTANTIATE_COMPONENT, componentClassName));
                    }

                }
                else {
                    LOG.warning(String.format(ExceptionConstants.USER_INFO_COMPONENT_NOT_FOUND, componentClassName));
                }

            } );

        }

        this.userInfoSet.add(userInfo);
        return userInfo;
    }

    public Optional<UserInfo> getUserInfo(UUID uuid) {
        return this.userInfoSet
                .stream()
                .filter( userInfo -> userInfo.getUUID().equals(uuid) )
                .findFirst();
    }

    public boolean unregisterComponent(UserInfo userInfo, Class<? extends UserComponent> componentClass) {
        String fileName = String.format("users/%s.yml", userInfo.getUUID().toString());
        Configurator configurator = new Configurator(plugin, fileName, false);

        return userInfo.getComponents().removeIf( userComponent -> {
            if (userComponent.getClass().equals(componentClass)) {

                String key = String.format("components.%s", userComponent.getClass().getSimpleName());
                if (configurator.exists(key)) {
                    configurator.remove(key);
                    configurator.save();
                }

                return true;
            }

            return false;
        } );
    }

}
