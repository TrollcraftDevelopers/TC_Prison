package pl.trollcraft.prison.service.pluginLoader;

public interface LoadingTask {

    int priority();
    String name();
    LoadingState performLoad(PluginInstance pluginInstance, DependencyMapper dependencyMapper);
    LoadingState performUnload(PluginInstance pluginInstance, DependencyMapper dependencyMapper);

}
