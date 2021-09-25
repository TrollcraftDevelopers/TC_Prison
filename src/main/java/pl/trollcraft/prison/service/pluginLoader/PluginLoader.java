package pl.trollcraft.prison.service.pluginLoader;

import com.google.common.eventbus.EventBus;
import org.bukkit.plugin.Plugin;

import java.util.*;
import java.util.logging.Logger;

public final class PluginLoader {

    public enum Operation {
        LOAD,
        UNLOAD
    }

    private static final Logger LOG
            = Logger.getLogger(PluginLoader.class.getSimpleName());

    private final Plugin plugin;
    private final List<LoadingTask> loadingTasks;
    private final DependencyMapper dependencyMapper;
    private final LoadingTaskPriorityComparator comparator;

    public PluginLoader(Plugin plugin) {
        this.plugin = plugin;
        loadingTasks = new ArrayList<>();
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

            LOG.info("Loading: " + loadingTask.name());
            loadingState = operation == Operation.LOAD ? loadingTask.performLoad(this.plugin, dependencyMapper) : loadingTask.performUnload(this.plugin, dependencyMapper);
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
