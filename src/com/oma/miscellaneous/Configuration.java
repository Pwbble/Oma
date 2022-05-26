package com.oma.miscellaneous;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Configuration extends YamlConfiguration {
    // I didn't write this file, so comments containing a tilde will be guesses

    // Declarations
    private File file;
    private JavaPlugin javaPlugin;
    private static Configuration Instance;

    // Constructors
    public Configuration(JavaPlugin javaPlugin, File path, String s) {
        Instance = this;
        this.javaPlugin = javaPlugin;
        setup(path, s);
    }

    public Configuration(JavaPlugin javaPlugin, File directory, String name, String def) {
        Instance = this;
        this.javaPlugin = javaPlugin;
        setup(directory, name, def);
    }

    public static Configuration createInstance() {
        return Instance;
    }

    // Function to ~load the default config.yml
    public void loadDefaults(String def) {
        load(); // Loads the file declared at the beginning of the class
        InputStream is = this.javaPlugin.getResource(def);
        if (is != null) {
            try {
                this.load(new InputStreamReader(is));
            } catch (IOException | InvalidConfigurationException exception) {
                exception.printStackTrace();
            }
        }
        save(); // Saves the file declared at the beginning of the class
    }

    public boolean setup(File path, String s) {
        path.mkdirs(); // Creates a directory at /path/
        file = new File(path, (s.endsWith(".yml")) ? s : s + ".yml");
        // If the config file doesn't already exist
        if (!file.exists()) {
            try {
                file.createNewFile(); // Creates a new empty file
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            return true;
        }
        load(); // Loads the file declared at the beginning of the class
        save(); // Saves the file declared at the beginning of the class
        return false;
    }

    public void setup(File path, String s, String def) {
        // Ran if the config doesn't exist
        if (setup(path, s)) loadDefaults(def);
    }

    // Function used to load the file declared at the beginning of the class
    public void load() {
        try {
            super.load(file);
        } catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }
    }

    // Function used to ~update the config at path to value
    public void update(String path, Object value) {
        load(); // Loads file
        set(path, value); // Sets the specified path to the specified value
        save(); // Saves file
    }

    // Function used to save the file declared at the beginning of the class
    public void save() {
        try {
            save(this.file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}