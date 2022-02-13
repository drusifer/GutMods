package com.suchamod.firstmod;

import org.bukkit.plugin.java.JavaPlugin;

public final class Firstmod extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerMovementListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
