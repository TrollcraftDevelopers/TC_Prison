package pl.trollcraft.prison;

import org.bukkit.plugin.java.JavaPlugin;
import pl.trollcraft.prison.constants.LocaleConstants;
import pl.trollcraft.prison.service.pluginLoader.PluginLoader;
import pl.trollcraft.prison.service.pluginLoader.PluginLoaderInitializer;

import java.util.logging.Logger;

public class PrisonPlugin extends JavaPlugin {

    private static final Logger LOG
            = Logger.getLogger(PrisonPlugin.class.getSimpleName());

    private PluginLoader pluginLoader;

    @Override
    public void onLoad() {
        this.pluginLoader = PluginLoaderInitializer.performInitialization();
    }

    @Override
    public void onEnable() {
        boolean loadingStatus = pluginLoader.perform(PluginLoader.Operation.LOAD);
        if (!loadingStatus) {
            this.setEnabled(false);
        }
    }

    @Override
    public void onDisable() {
        boolean loadingStatus = pluginLoader.perform(PluginLoader.Operation.UNLOAD);
        if (!loadingStatus) {
            LOG.severe(LocaleConstants.PLUGIN_NOT_DISABLED_CORRECTLY);
        }
    }
}
