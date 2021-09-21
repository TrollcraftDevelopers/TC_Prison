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

    public void registerLoadingTask(LoadingTask loadingTask) {
        this.loadingTasks.add(loadingTask);
    }

    public PluginLoader() {
        loadingTasks = new HashSet<>();
    }

    public boolean perform(Operation operation) {
        LoadingState loadingState;
        for (LoadingTask loadingTask : this.loadingTasks) {

            loadingState = operation == Operation.LOAD ? loadingTask.performLoad() : loadingTask.performUnload();
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
