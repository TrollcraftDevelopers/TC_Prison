package pl.trollcraft.prison.service.configuration;

public interface Config<T> {

    T configure(Configurator configProvider);

}
