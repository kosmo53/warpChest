package net.ksm.mcp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public class PlayerListener implements Listener {

    private final WarpPhoneBook warpPhoneBook;
    private final FileConfiguration config;

    PlayerListener(WarpPhoneBook warpPhoneBook, FileConfiguration fileConfiguration) {
        this.warpPhoneBook = warpPhoneBook;
        this.config = fileConfiguration;
    }

    private void tpp(Player p, String w) {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        String command = "warp" + " " + w + " " + p.getName();

        Bukkit.dispatchCommand(console, command);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        if (!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase(config.getString("guiName")))
            return;
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);
        String nam = event.getCurrentItem().getItemMeta().getDisplayName();
        tpp(player, nam);

    }

    @EventHandler
    public void onPlayerInterract(PlayerInteractEvent ev) {
        Action a = ev.getAction();
        ItemStack is = ev.getItem();

        if (a == Action.PHYSICAL || is == null || is.getType() == Material.AIR)
            return;
        if (is.getType() == Material.COMPASS && is.getItemMeta().getDisplayName().equalsIgnoreCase("Teleporty")) {
            ev.getPlayer().openInventory(warpPhoneBook.getInventory());
        }


    }

}
