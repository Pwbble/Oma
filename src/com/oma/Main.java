package com.oma;

import com.oma.commands.Gmc;
import com.oma.events.PlayerJoin;
import com.oma.miscellaneous.Config;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main main;
    private Config config;

    @Override
    public void onEnable() {
        main = this;
        loadEvents();
        loadCommands();
        loadFiles();
    }

    private void loadCommands() {
        getCommand("gmc").setExecutor(new Gmc());
    }

    private void loadEvents() {
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
    }

    private void loadFiles() {
        config = new Config(this, getDataFolder(), "config.yml", "config.yml");
    }
}