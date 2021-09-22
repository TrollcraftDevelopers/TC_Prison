package pl.trollcraft.prison.service.pluginLoader;

public class LoadingState {

    public static LoadingState OK() {
        return new LoadingState(true, false, "");
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
