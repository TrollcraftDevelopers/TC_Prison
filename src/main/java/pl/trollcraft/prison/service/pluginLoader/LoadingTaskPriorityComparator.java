package pl.trollcraft.prison.service.pluginLoader;

import java.util.Comparator;

public final class LoadingTaskPriorityComparator implements Comparator<LoadingTask> {

    @Override
    public int compare(LoadingTask a, LoadingTask b) {
        int aPriority = a.priority();
        int bPriority = b.priority();
        return bPriority - aPriority;
    }
}
