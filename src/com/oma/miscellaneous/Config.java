package com.oma.miscellaneous;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Config extends YamlConfiguration {

    public File f;
    public JavaPlugin javaPlugin;

    public Config(JavaPlugin javaPlugin, File path, String s) {
        this.javaPlugin = javaPlugin;
        setup(path, s);
    }

    public Config(JavaPlugin javaPlugin, File path, String s, String def) {
        this.javaPlugin = javaPlugin;
        setup(path, s, def);
    }

    public void loaddefaults(String def) {
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
        this.f = new File(path, (s.endsWith(".yml")) ? s : s + ".yml");
        if (!this.f.exists()) {
            try {
                this.f.createNewFile();
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
        if (setup(path, s)) loaddefaults(def);
    }

    public void load() {
        try {
            super.load(f);
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
            save(this.f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}