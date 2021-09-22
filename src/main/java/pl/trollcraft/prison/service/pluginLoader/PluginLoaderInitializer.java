package pl.trollcraft.prison.service.pluginLoader;

import org.bukkit.plugin.Plugin;

public class PluginLoaderInitializer {

    public static PluginLoader performInitialization(Plugin plugin) {
        PluginLoader pluginLoader = new PluginLoader();

        //TODO register loading tasks for the plugin loader.

        return pluginLoader;
    }

}
