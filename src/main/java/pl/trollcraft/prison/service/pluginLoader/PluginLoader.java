package pl.trollcraft.prison.service.pluginLoader;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pl.trollcraft.prison.constants.SystemLocaleConstants;

import java.util.*;
import java.util.logging.Logger;

public final class PluginLoader {

    public enum Operation {
        LOAD,
        UNLOAD
    }

    private static final Logger LOG
            = Logger.getLogger(PluginLoader.class.getSimpleName());

    private final PluginInstance pluginInstance;
    private final List<LoadingTask> loadingTasks;
    private final DependencyMapper dependencyMapper;
    private final LoadingTaskPriorityComparator comparator;

    public PluginLoader(Plugin plugin) {
        this.pluginInstance = new PluginInstance((JavaPlugin) plugin);
        this.loadingTasks = new ArrayList<>();
        this.dependencyMapper = new DependencyMapper();
        this.comparator = new LoadingTaskPriorityComparator();
    }

    public void registerLoadingTask(LoadingTask loadingTask) {
        this.loadingTasks.add(loadingTask);
    }

    private void orderLoadingTasks() {
        this.loadingTasks.sort(comparator);
    }

    public boolean perform(Operation operation) {
        this.orderLoadingTasks();

        LoadingState loadingState;
        for (LoadingTask loadingTask : this.loadingTasks) {

            LOG.info(String.format(SystemLocaleConstants.PLUGIN_LOADER_LOADING_TASK, loadingTask.getClass().getSimpleName()));

            loadingState = operation == Operation.LOAD ? loadingTask.performLoad(this.pluginInstance, dependencyMapper) : loadingTask.performUnload(this.pluginInstance, dependencyMapper);
            if (!loadingState.isOk()) {

                if (loadingState.isCritical()){
                    LOG.severe(loadingState.getMessage());
                    return false;
                }
                else {
                    LOG.warning(loadingState.getMessage());
                }
            }

        }
        return true;
    }

    public DependencyMapper getDependencyMapper() {
        return dependencyMapper;
    }
}
