package pl.trollcraft.prison.service.pluginLoader.loadingTasks;

import pl.trollcraft.prison.service.pluginLoader.*;
import pl.trollcraft.prison.service.userInfo.UserComponentManager;
import pl.trollcraft.prison.service.userInfo.UserInfoManager;
import pl.trollcraft.prison.service.userInfo.listener.PlayerJoinQuitListener;

public class UserInfoManagementLoadingTask implements LoadingTask {

    @Override
    public int priority() {
        return 750;
    }

    @Override
    public String name() {
        return getClass().getSimpleName();
    }

    @Override
    public LoadingState performLoad(PluginInstance pluginInstance, DependencyMapper dependencyMapper) {
        UserComponentManager userComponentManager = new UserComponentManager();

        //TODO think of EventBus or something to handle
        //TODO Currently we have to manually register components.

        UserInfoManager userInfoManager = new UserInfoManager(pluginInstance.getPlugin(), userComponentManager);

        dependencyMapper.registerDependency(userComponentManager);
        dependencyMapper.registerDependency(userInfoManager);

        pluginInstance.registerListener(new PlayerJoinQuitListener(userInfoManager));

        return LoadingStates.ok();
    }

    @Override
    public LoadingState performUnload(PluginInstance pluginInstance, DependencyMapper dependencyMapper) {
        return LoadingStates.ok();
    }
}
