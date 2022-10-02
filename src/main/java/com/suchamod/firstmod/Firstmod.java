package com.suchamod.firstmod;

import org.bukkit.plugin.java.JavaPlugin;

public final class Firstmod extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new SandExploder(), this);
        getServer().getPluginManager().registerEvents(new DirtBlocker(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
