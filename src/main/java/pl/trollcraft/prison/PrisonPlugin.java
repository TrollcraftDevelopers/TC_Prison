package pl.trollcraft.prison;

import org.bukkit.plugin.java.JavaPlugin;
import pl.trollcraft.prison.constants.ExceptionConstants;
import pl.trollcraft.prison.constants.LocaleConstants;
import pl.trollcraft.prison.service.localeService.LocaleService;
import pl.trollcraft.prison.service.pluginLoader.PluginLoader;
import pl.trollcraft.prison.service.pluginLoader.PluginLoaderInitializer;

import java.util.logging.Logger;

public class PrisonPlugin extends JavaPlugin {

    private static final Logger LOG
            = Logger.getLogger(PrisonPlugin.class.getSimpleName());

    private PluginLoader pluginLoader;

    @Override
    public void onLoad() {
        this.pluginLoader = PluginLoaderInitializer.performInitialization(this);
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
        LocaleService localeService = this.pluginLoader.getDependencyMapper().getDependency(LocaleService.class)
                .orElseThrow( () -> new IllegalStateException(ExceptionConstants.PLUGIN_DISABLE_LOCALE_SERVICE_NOT_FOUND) );

        String pluginNotDisabledCorrectlyMessage = localeService.get(LocaleConstants.PLUGIN_NOT_DISABLED_CORRECTLY);
        boolean loadingStatus = pluginLoader.perform(PluginLoader.Operation.UNLOAD);
        if (!loadingStatus) {
            LOG.severe(pluginNotDisabledCorrectlyMessage);
        }
    }
}
