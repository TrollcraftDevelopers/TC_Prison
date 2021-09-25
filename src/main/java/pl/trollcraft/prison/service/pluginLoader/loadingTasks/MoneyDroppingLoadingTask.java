package pl.trollcraft.prison.service.pluginLoader.loadingTasks;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pl.trollcraft.prison.service.moneyDropping.MoneyDroppingService;
import pl.trollcraft.prison.service.moneyDropping.command.DropMoneyCommand;
import pl.trollcraft.prison.service.moneyDropping.listener.MoneyDepositListener;
import pl.trollcraft.prison.service.pluginLoader.DependencyMapper;
import pl.trollcraft.prison.service.pluginLoader.LoadingState;
import pl.trollcraft.prison.service.pluginLoader.LoadingStates;
import pl.trollcraft.prison.service.pluginLoader.LoadingTask;

import java.util.Optional;

public final class MoneyDroppingLoadingTask implements LoadingTask {

    @Override
    public int priority() {
        return 100;
    }

    @Override
    public String name() {
        return getClass().getSimpleName();
    }

    @Override
    public LoadingState performLoad(Plugin plugin, DependencyMapper dependencyMapper) {

        Optional<Economy> oEconomy = dependencyMapper.getDependency(Economy.class);
        if (oEconomy.isEmpty()) {
            return LoadingStates.error("Nie mozna zaladowac systemu banknotow.");
        }

        JavaPlugin javaPlugin = (JavaPlugin) plugin;

        Economy economy = oEconomy.get();
        MoneyDroppingService moneyDroppingService = new MoneyDroppingService(economy);

        PluginCommand moneyDropCommand = javaPlugin.getCommand("moneyDrop");
        if (moneyDropCommand != null) {
            moneyDropCommand.setExecutor(new DropMoneyCommand(moneyDroppingService));
        }

        plugin.getServer().getPluginManager().registerEvents(new MoneyDepositListener(moneyDroppingService), plugin);

        return LoadingStates.ok();
    }

    @Override
    public LoadingState performUnload(Plugin plugin, DependencyMapper dependencyMapper) {
        return LoadingStates.ok();
    }
}
