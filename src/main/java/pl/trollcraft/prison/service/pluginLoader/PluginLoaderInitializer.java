package pl.trollcraft.prison.service.pluginLoader;

import org.bukkit.plugin.Plugin;
import pl.trollcraft.prison.service.pluginLoader.loadingTasks.LocaleServiceLoadingTask;
import pl.trollcraft.prison.service.pluginLoader.loadingTasks.MoneyDroppingLoadingTask;
import pl.trollcraft.prison.service.pluginLoader.loadingTasks.UserInfoManagementLoadingTask;
import pl.trollcraft.prison.service.pluginLoader.loadingTasks.VaultLoadingTask;

public class PluginLoaderInitializer {

    public static PluginLoader performInitialization(Plugin plugin) {
        PluginLoader pluginLoader = new PluginLoader(plugin);

        pluginLoader.registerLoadingTask(new LocaleServiceLoadingTask());
        pluginLoader.registerLoadingTask(new UserInfoManagementLoadingTask());
        //TODO register loading tasks for the plugin loader.

        pluginLoader.registerLoadingTask(new VaultLoadingTask());

        pluginLoader.registerLoadingTask(new MoneyDroppingLoadingTask());

        return pluginLoader;
    }

}
