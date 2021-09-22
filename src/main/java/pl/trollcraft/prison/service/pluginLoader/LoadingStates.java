package pl.trollcraft.prison.service.pluginLoader;

public final class LoadingStates {

    public static LoadingState OK() {
        return new LoadingState(true, false, "");
    }

    public static LoadingState critical(String message) {
        return new LoadingState(false, true, message);
    }

    public static LoadingState error(String message) {
        return new LoadingState(false, false, message);
    }

}
