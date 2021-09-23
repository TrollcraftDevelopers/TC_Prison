package pl.trollcraft.prison.service.userInfo;

import pl.trollcraft.prison.service.configuration.Configurator;

public interface UserComponent {

    void save(Configurator configurator);
    void load(Configurator configurator);

}
