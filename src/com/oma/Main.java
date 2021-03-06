package com.oma;

import com.oma.commands.*;
import com.oma.events.*;
import com.oma.handlers.BuildModeHandler;
import com.oma.handlers.GameModeHandler;
import com.oma.handlers.InventoryHandler;
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

    @Override
    public void onDisable() {

    }

    private void loadFiles() {
        config = new Configuration(this, getDataFolder(), "config.yml", "config.yml");
        new BuildModeHandler();
        new GameModeHandler();
        new InventoryHandler();
        new MessageHandler(config);
    }

    private void loadCommands() {
        getCommand("buildmode").setExecutor(new BuildMode());
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
        getServer().getPluginManager().registerEvents(new AsyncPlayerChat(), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new BlockPlace(), this);
        getServer().getPluginManager().registerEvents(new EntitySpawn(), this);
        getServer().getPluginManager().registerEvents(new PlayerDropItem(), this);
        getServer().getPluginManager().registerEvents(new PlayerGameModeChange(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerPickupItem(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
    }
}