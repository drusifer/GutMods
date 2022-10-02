package com.suchamod.firstmod;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.logging.Logger;

public class DirtBlocker implements Listener {
    Logger logger = Bukkit.getLogger();

    public DirtBlocker() {
    }

    /**
     * Consumes 1 of special item to reduce damage of 1 to the player.  If the play is out of special item then
     * they take all the damage.
     *
     * @param player
     * @return
     */
    private double getDamageReduced(Player player, Material special_item, double damage_incurred) {
        Map<Integer, ItemStack> leftOver = player.getInventory()
                .removeItem(new ItemStack(special_item, (int) damage_incurred));
        double reduced = damage_incurred;
        if (leftOver.size() > 0) {
            // leftOver contains the items that couldn't be removed (because the player doesn't have enough)
            // so deduct what was removed from the damage_incurred.
            reduced = damage_incurred - leftOver.get(0).getAmount();
        }
        return reduced;
    }

    @EventHandler
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        Entity damagee = event.getEntity();
        Entity damager = event.getDamager();
        if (damagee.getType() == EntityType.PLAYER) {
            double damage = event.getDamage();
            double damageReflected = getDamageReduced((Player) damagee, Material.DIRT, damage);

            logger.info("Original Damage " + damage + " reflected: " + damageReflected);
            event.setDamage(damage - damageReflected);
            // If you were attacked by another play then reflect that damage to the player or mob
            if (damageReflected > 0) {
                ((Player) damagee).playSound(damagee.getLocation(), Sound.BLOCK_ANVIL_HIT, 1f, 0.5f);
                if (damager instanceof Player) {
                    ((Player) damager).damage(damageReflected);
                } else if (damager instanceof Mob) {

                    ((Mob) damager).damage(damageReflected);
                }
                logger.info("Reflected Damage to: " + damager.getName());
            }
        }
    }
}