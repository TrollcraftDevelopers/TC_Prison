package pl.trollcraft.prison.service.pluginLoader.loadingTasks;

import org.bukkit.plugin.Plugin;
import pl.trollcraft.prison.service.pluginLoader.DependencyMapper;
import pl.trollcraft.prison.service.pluginLoader.LoadingState;
import pl.trollcraft.prison.service.pluginLoader.LoadingStates;
import pl.trollcraft.prison.service.pluginLoader.LoadingTask;
import pl.trollcraft.prison.service.testComponent.ComponentListener;
import pl.trollcraft.prison.service.testComponent.TestUserComponent;
import pl.trollcraft.prison.service.userInfo.UserComponentManager;
import pl.trollcraft.prison.service.userInfo.UserInfoManager;
import pl.trollcraft.prison.service.userInfo.listener.PlayerJoinQuitListener;

public class UserInfoManagementLoadingTask implements LoadingTask {

    @Override
    public String name() {
        return getClass().getSimpleName();
    }

    @Override
    public LoadingState performLoad(Plugin plugin, DependencyMapper dependencyMapper) {
        UserComponentManager userComponentManager = new UserComponentManager();
        userComponentManager.registerComponent(TestUserComponent.class);

        //TODO think of EventBus or something to handle
        UserInfoManager userInfoManager = new UserInfoManager(plugin, userComponentManager);

        dependencyMapper.registerDependency(userComponentManager);
        dependencyMapper.registerDependency(userInfoManager);

        plugin.getServer().getPluginManager().registerEvents(new PlayerJoinQuitListener(userInfoManager), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ComponentListener(), plugin);

        return LoadingStates.ok();
    }

    @Override
    public LoadingState performUnload(Plugin plugin, DependencyMapper dependencyMapper) {
        return LoadingStates.ok();
    }
}
