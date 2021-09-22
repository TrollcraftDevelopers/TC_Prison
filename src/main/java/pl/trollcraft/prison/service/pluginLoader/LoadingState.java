package pl.trollcraft.prison.service.pluginLoader;

public class LoadingState {

    //TODO move to a separate class
    public static LoadingState OK() {
        return new LoadingState(true, false, "");
    }

    //TODO move to a separate class
    public static LoadingState critical(String message) {
        return new LoadingState(false, true, message);
    }

    //TODO move to a separate class
    public static LoadingState error(String message) {
        return new LoadingState(false, false, message);
    }

    private final boolean ok;
    private final boolean critical;
    private final String message;

    public LoadingState(boolean ok,
                        boolean critical,
                        String message) {
        this.ok = ok;
        this.critical = critical;
        this.message = message;
    }

    public boolean isOk() {
        return ok;
    }

    public boolean isCritical() {
        return critical;
    }

    public String getMessage() {
        return message;
    }
}
