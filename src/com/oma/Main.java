package com.oma;

import com.oma.commands.*;
import com.oma.events.*;
import com.oma.handlers.BuildmodeHandler;
import com.oma.handlers.GameModeHandler;
import com.oma.handlers.MessageHandler;
import com.oma.miscellaneous.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main main;
    private Configuration config;

    @Override
    public void onEnable() {
        main = this;
        loadEvents();
        loadCommands();
        loadFiles();
    }

    private void loadFiles() {
        config = new Configuration(this, getDataFolder(), "config.yml", "config.yml");
        new BuildmodeHandler();
        new GameModeHandler();
        new MessageHandler(config);
    }

    private void loadCommands() {
        // getCommand("build").setExecutor(new Build());
        getCommand("config").setExecutor(new Config());
        // getCommand("createworld").setExecutor(new CreateWorld());
        getCommand("fly").setExecutor(new Fly());
        getCommand("gma").setExecutor(new Gma());
        getCommand("gmc").setExecutor(new Gmc());
        getCommand("gms").setExecutor(new Gms());
        getCommand("gmsp").setExecutor(new Gmsp());
        getCommand("send").setExecutor(new Send());
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
}