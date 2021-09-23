package pl.trollcraft.prison.service.pluginLoader;

import org.bukkit.plugin.Plugin;

public interface LoadingTask {

    String name();
    LoadingState performLoad(Plugin plugin, DependencyMapper dependencyMapper);
    LoadingState performUnload(Plugin plugin, DependencyMapper dependencyMapper);

}
