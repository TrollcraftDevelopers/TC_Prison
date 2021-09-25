package pl.trollcraft.prison.service.pluginLoader.loadingTasks;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import pl.trollcraft.prison.constants.Constants;
import pl.trollcraft.prison.service.pluginLoader.DependencyMapper;
import pl.trollcraft.prison.service.pluginLoader.LoadingState;
import pl.trollcraft.prison.service.pluginLoader.LoadingStates;
import pl.trollcraft.prison.service.pluginLoader.LoadingTask;

public final class VaultLoadingTask implements LoadingTask {

    @Override
    public int priority() {
        return 500;
    }

    @Override
    public String name() {
        return getClass().getSimpleName();
    }

    @Override
    public LoadingState performLoad(Plugin plugin, DependencyMapper dependencyMapper) {

        Server server = plugin.getServer();

        if (server.getPluginManager().getPlugin(Constants.PLUGIN_VAULT_NAME) == null) {
            return LoadingStates.critical("Vault nie zostal wykryty.");
        }
        RegisteredServiceProvider<Economy> rsp = server.getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return LoadingStates.critical("Vault nie zostal zaladowany prawidlowo.");
        }

        Economy economy = rsp.getProvider();
        dependencyMapper.registerDependency(economy);

        return LoadingStates.ok();
    }

    @Override
    public LoadingState performUnload(Plugin plugin, DependencyMapper dependencyMapper) {
        return LoadingStates.ok();
    }
}
