package pl.trollcraft.prison.service.pluginLoader.loadingTasks;

import pl.trollcraft.prison.service.configuration.Config;
import pl.trollcraft.prison.service.configuration.Configurator;
import pl.trollcraft.prison.service.configuration.configs.LocaleConfig;
import pl.trollcraft.prison.service.localeService.LocaleService;
import pl.trollcraft.prison.service.pluginLoader.*;

public class LocaleServiceLoadingTask implements LoadingTask {

    @Override
    public int priority() {
        return 1000;
    }

    @Override
    public LoadingState performLoad(PluginInstance pluginInstance, DependencyMapper dependencyMapper) {
        Configurator configurator = new Configurator(pluginInstance.getPlugin(), "locale.yml", true);
        Config<LocaleService> localeServiceConfig = new LocaleConfig();
        LocaleService localeService = localeServiceConfig.configure(configurator);

        dependencyMapper.registerDependency(localeService);

        return LoadingStates.ok();
    }

    @Override
    public LoadingState performUnload(PluginInstance pluginInstance, DependencyMapper dependencyMapper) {
        return LoadingStates.ok();
    }
}
