package net.ksm.mcp;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Compass implements Listener {

    private final ItemStack compass;
    private final ItemMeta itemmeta;
    private final FileConfiguration config;

    Compass(FileConfiguration config) {
        this.config = config;
        compass = new ItemStack(Material.COMPASS);
        itemmeta = compass.getItemMeta();
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        if (config.getBoolean("compassOnJoin"))sendCompass(event.getPlayer());
    }

    private void sendCompass(Player p) {
        if (!p.getInventory().contains(compass)) {
            itemmeta.setDisplayName("Teleporty");
            compass.setItemMeta(itemmeta);
            p.getInventory().addItem(compass);
        }


    }
}
