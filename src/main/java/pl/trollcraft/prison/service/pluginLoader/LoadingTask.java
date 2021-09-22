package pl.trollcraft.prison.service.pluginLoader;

public interface LoadingTask {

    String name();
    LoadingState performLoad();
    LoadingState performUnload();

}
