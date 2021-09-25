package pl.trollcraft.prison.service.pluginLoader;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.trollcraft.prison.constants.ExceptionConstants;

import java.util.logging.Logger;

public final class PluginInstance {

    private static final Logger LOG = Logger.getLogger(PluginInstance.class.getSimpleName());

    private final JavaPlugin plugin;

    public PluginInstance(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerCommand(String command, CommandExecutor executor) {
        PluginCommand pluginCommand = this.plugin.getCommand(command);
        if (pluginCommand == null) {
            LOG.warning(String.format(ExceptionConstants.CANNOT_REGISTER_PLUGIN_COMMAND, command, executor.getClass().getSimpleName()));

        }
        else {
            pluginCommand.setExecutor(executor);
        }
    }

    public void registerListener(Listener listener) {
        this.plugin.getServer().getPluginManager().registerEvents(listener, this.plugin);
    }

    public void registerListeners(Listener... listeners) {
        PluginManager pluginManager = this.plugin.getServer().getPluginManager();
        for (Listener listener : listeners) {
            pluginManager.registerEvents(listener, this.plugin);
        }
    }


    public JavaPlugin getPlugin() {
        return plugin;
    }
}
