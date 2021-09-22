package pl.trollcraft.prison.service.configuration;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import pl.trollcraft.prison.constants.ExceptionConstants;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class Configurator {

    private final Plugin plugin;
    private final YamlConfiguration conf;
    private final File file;

    public Configurator(Plugin plugin, String fileName) {
        this.plugin = plugin;

        file = new File(this.plugin.getDataFolder() + File.separator + fileName);
        if (!file.exists()) {
            this.tryToCreateDirectories(fileName);
            plugin.saveResource(fileName, false);
        }
        conf = new YamlConfiguration();

        try {
            conf.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            throw new IllegalStateException(String.format(ExceptionConstants.CONFIGURATOR_FAILED_TO_LOAD_CONFIG, fileName));
        }
    }

    private void tryToCreateDirectories(String fileName) {
        if (fileName.contains(File.separator)) {

            int lastSlash = fileName.lastIndexOf(File.separator);
            String lastPart = fileName.substring(lastSlash);

            String finalFileName;
            if (lastPart.contains(".")) {
                finalFileName = fileName.substring(0, lastSlash);
            }
            else {
                finalFileName = fileName;
            }

            Path p = Paths.get(this.plugin.getDataFolder() + File.separator + finalFileName);

            try {
                Files.createDirectories(p);
            } catch (FileAlreadyExistsException e) {
                e.printStackTrace();
            } catch (IOException e) {
                String exceptionMessage = e.getMessage();
                throw new IllegalStateException(String.format(ExceptionConstants.CONFIGURATOR_FAILED_TO_CREATE_DIRECTORIES, fileName, exceptionMessage));
            }
        }
    }

    public Set<String> keys(String section) {
        ConfigurationSection configurationSection = conf.getConfigurationSection(section);
        if (configurationSection == null) {
            throw new IllegalStateException(String.format(ExceptionConstants.CONFIGURATOR_CONFIGURATION_SECTION_DOES_NOT_EXIST, section));
        }
        return configurationSection.getKeys(false);
    }

    public<T> T read(String key, Class<T> clazz) {
        Object o = conf.get(key);
        return clazz.cast(o);
    }

    public boolean exists(String key) {
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
