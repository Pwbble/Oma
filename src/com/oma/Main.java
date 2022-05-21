package com.oma;

import com.oma.commands.*;
import com.oma.events.*;
import com.oma.handlers.MessageHandler;
import com.oma.miscellaneous.Config;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main main;
    private MessageHandler messageHandler;
    private Config config;

    @Override
    public void onEnable() {
        main = this;
        loadEvents();
        loadCommands();
        loadHandlers();
        loadFiles();
    }

    private void loadCommands() {
        getCommand("fly").setExecutor(new Fly());
        getCommand("gma").setExecutor(new Gma());
        getCommand("gmc").setExecutor(new Gmc());
        getCommand("gms").setExecutor(new Gms());
        getCommand("gmsp").setExecutor(new Gmsp());
        // getCommand("world").setExecutor(new World());
        getCommand("worlds").setExecutor(new Worlds());
    }

    private void loadEvents() {
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new BlockPlace(), this);
        getServer().getPluginManager().registerEvents(new PlayerGameModeChange(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
    }

    private void loadHandlers() {
        messageHandler = new MessageHandler(config);
    }

    private void loadFiles() {
        config = new Config(this, getDataFolder(), "config.yml", "config.yml");
    }
}