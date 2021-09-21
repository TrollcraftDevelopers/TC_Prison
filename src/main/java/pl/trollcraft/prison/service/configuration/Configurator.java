package pl.trollcraft.prison.service.configuration;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class Configurator {

    private static final Logger LOG =
            Logger.getLogger(Configurator.class.getTypeName());

    private final YamlConfiguration conf;
    private final File file;

    public Configurator(Plugin plugin, String fileName) {

        file = new File(plugin.getDataFolder() + File.separator + fileName);
        if (!file.exists())
            plugin.saveResource(fileName, false);
        conf = new YamlConfiguration();

        try {
            conf.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to load config " + fileName);
        }

    }

    public<T> T read(String key, Class<T> clazz) {
        Object o = conf.get(key);
        return clazz.cast(o);
    }

    public boolean isSet(String key) {
        return conf.contains(key);
    }

    public void write(String key, Object o) {
        conf.set(key, o);
    }

    public YamlConfiguration conf() {
        return conf;
    }

    public void save() {

        try {
            conf.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
