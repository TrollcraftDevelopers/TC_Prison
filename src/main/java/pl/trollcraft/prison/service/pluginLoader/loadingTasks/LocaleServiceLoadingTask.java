package pl.trollcraft.prison.service.pluginLoader.loadingTasks;

import org.bukkit.plugin.Plugin;
import pl.trollcraft.prison.service.configuration.Config;
import pl.trollcraft.prison.service.configuration.Configurator;
import pl.trollcraft.prison.service.configuration.configs.LocaleConfig;
import pl.trollcraft.prison.service.localeService.LocaleService;
import pl.trollcraft.prison.service.pluginLoader.DependencyMapper;
import pl.trollcraft.prison.service.pluginLoader.LoadingState;
import pl.trollcraft.prison.service.pluginLoader.LoadingStates;
import pl.trollcraft.prison.service.pluginLoader.LoadingTask;

public class LocaleServiceLoadingTask implements LoadingTask {

    @Override
    public String name() {
        return getClass().getSimpleName();
    }

    @Override
    public LoadingState performLoad(Plugin plugin, DependencyMapper dependencyMapper) {
        Configurator configurator = new Configurator(plugin, "locale.yml", true);
        Config<LocaleService> localeServiceConfig = new LocaleConfig();
        LocaleService localeService = localeServiceConfig.configure(configurator);

        dependencyMapper.registerDependency(localeService);

        return LoadingStates.ok();
    }

    @Override
    public LoadingState performUnload(Plugin plugin, DependencyMapper dependencyMapper) {
        return LoadingStates.ok();
    }
}
