package pl.trollcraft.prison.service.configuration.configs;

import pl.trollcraft.prison.service.configuration.Config;
import pl.trollcraft.prison.service.configuration.Configurator;
import pl.trollcraft.prison.service.localeService.LocaleService;

public class LocaleConfig implements Config<LocaleService> {

    @Override
    public LocaleService configure(Configurator configurator) {
        LocaleService localeService = new LocaleService();
        configurator.keys("").forEach( key -> {
            String text = configurator.read(key, String.class);
            localeService.add(key, text);
        } );

        return localeService;
    }
}
