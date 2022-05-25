package com.oma.miscellaneous;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Configuration extends YamlConfiguration {

    // Declarations
    private File file;
    private JavaPlugin javaPlugin;
    private static Configuration Instance;

    public Configuration(JavaPlugin javaPlugin, File path, String s) {
        Instance = this;
        this.javaPlugin = javaPlugin;
        setup(path, s);
    }

    public Configuration(JavaPlugin javaPlugin, File path, String s, String def) {
        Instance = this;
        this.javaPlugin = javaPlugin;
        setup(path, s, def);
    }

    public static Configuration createInstance() {
        return Instance;
    }

    public void loadDefaults(String def) {
        load();
        InputStream is = this.javaPlugin.getResource(def);
        if (is != null) {
            try {
                this.load(new InputStreamReader(is));
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }
        save();
    }

    public boolean setup(File path, String s) {
        path.mkdirs();
        this.file = new File(path, (s.endsWith(".yml")) ? s : s + ".yml");
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        load();
        save();
        return false;
    }

    public void setup(File path, String s, String def) {
        if (setup(path, s)) loadDefaults(def);
    }

    public void load() {
        try {
            super.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void update(String key, Object value) {
        load();
        set(key, value);
        save();
    }

    public void save() {
        try {
            save(this.file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}