package pl.trollcraft.prison.service.pluginLoader;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public final class PluginLoader {

    public enum Operation {
        LOAD,
        UNLOAD
    }

    private static final Logger LOG
            = Logger.getLogger(PluginLoader.class.getSimpleName());

    private final Set<LoadingTask> loadingTasks;
    private final DependencyMapper dependencyMapper;

    public PluginLoader() {
        loadingTasks = new HashSet<>();
        this.dependencyMapper = new DependencyMapper();
    }

    public void registerLoadingTask(LoadingTask loadingTask) {
        this.loadingTasks.add(loadingTask);
    }

    public boolean perform(Operation operation) {
        LoadingState loadingState;
        for (LoadingTask loadingTask : this.loadingTasks) {

            LOG.info("Loading: " + loadingTask.name());
            loadingState = operation == Operation.LOAD ? loadingTask.performLoad(dependencyMapper) : loadingTask.performUnload(dependencyMapper);
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


}
