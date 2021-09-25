package pl.trollcraft.prison.service.pluginLoader.loadingTasks;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pl.trollcraft.prison.service.moneyDropping.MoneyDroppingService;
import pl.trollcraft.prison.service.moneyDropping.command.DropMoneyCommand;
import pl.trollcraft.prison.service.moneyDropping.listener.MoneyDepositListener;
import pl.trollcraft.prison.service.pluginLoader.*;

import java.util.Optional;

public final class MoneyDroppingLoadingTask implements LoadingTask {

    @Override
    public int priority() {
        return 100;
    }

    @Override
    public LoadingState performLoad(PluginInstance pluginInstance, DependencyMapper dependencyMapper) {

        Optional<Economy> oEconomy = dependencyMapper.getDependency(Economy.class);
        if (oEconomy.isEmpty()) {
            return LoadingStates.error("Nie mozna zaladowac systemu banknotow.");
        }

        Economy economy = oEconomy.get();
        MoneyDroppingService moneyDroppingService = new MoneyDroppingService(economy);

        pluginInstance.registerCommand("moneyDrop", new DropMoneyCommand(moneyDroppingService));
        pluginInstance.registerListener(new MoneyDepositListener(moneyDroppingService));

        return LoadingStates.ok();
    }

    @Override
    public LoadingState performUnload(PluginInstance pluginInstance, DependencyMapper dependencyMapper) {
        return LoadingStates.ok();
    }
}
