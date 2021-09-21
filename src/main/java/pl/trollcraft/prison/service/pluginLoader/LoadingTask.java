package pl.trollcraft.prison.service.pluginLoader;

public interface LoadingTask {

    LoadingState performLoad();
    LoadingState performUnload();

}
