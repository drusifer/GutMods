package com.suchamod.firstmod;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class SandExploder implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        // Get the player that just moved
        Player p = event.getPlayer();

        // Get the Block right below the player
        Block b = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
        // Create an explosion of power 5 on the player's location
        if (b.getType() == Material.SAND) {

            World w = p.getWorld();
            w.createExplosion(p.getLocation(), 5);
        }

    }

}