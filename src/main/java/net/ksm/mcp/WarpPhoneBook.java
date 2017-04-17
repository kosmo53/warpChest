package net.ksm.mcp;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

class WarpPhoneBook {

    private static final String GUI_NAME = "guiName";
    private final BukkitFacade bukkitFaade;
    private final FileConfiguration config;
    private Inventory inventory;

    WarpPhoneBook(BukkitFacade bukkitFaade, FileConfiguration fileConfiguration) {
        this.bukkitFaade = bukkitFaade;
        this.config = fileConfiguration;
    }

    void initPhoneBook() {

        int size = 9 * getWarpHeight();

        this.inventory = Bukkit.createInventory(null, size, config.getString(GUI_NAME));

        List<String> warpList = bukkitFaade.getWarps();
        for (int i = 0; i < warpList.size(); i++) {
            String warp = warpList.get(i);
                setWarpItem(warp, i, inventory);

        }
        System.out.println("[WarpChest] Setting chest size to: " + size + " (" + getWarpHeight() + " rows)");
    }


    private void setWarpItem(String warpName, Integer warpIndex, Inventory i) {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta itemmeta = item.getItemMeta();
        itemmeta.setDisplayName(warpName);
        item.setItemMeta(itemmeta);
        item.setAmount(warpIndex + 1);
        i.setItem(warpIndex, item);
    }
//    private void setWarpItem(String warpName, Integer warpIndex, Inventory i, Material material, List lore) {
//        ItemStack item = new ItemStack(material);
//        ItemMeta itemmeta = item.getItemMeta();
//        itemmeta.setDisplayName(warpName);
//        itemmeta.setLore(lore);
//        item.setItemMeta(itemmeta);
//        item.setAmount(warpIndex + 1);
//        i.setItem(warpIndex, item);
//    }
//    private void setWarpItem(String warpName, Integer warpIndex, Inventory i, Material material) {
//        ItemStack item = new ItemStack(material);
//        ItemMeta itemmeta = item.getItemMeta();
//        itemmeta.setDisplayName(warpName);
//        item.setItemMeta(itemmeta);
//        item.setAmount(warpIndex + 1);
//        i.setItem(warpIndex, item);
//    }

    void open(Player player) {
player.openInventory(inventory);
    }


    private int getWarpHeight() {
        int warpCount = bukkitFaade.getWarpCount();
        if (warpCount <= 9) {
            return 1;
        } else if (warpCount <= 18) {
            return 2;
        } else if (warpCount <= 27) {
            return 3;
        } else if (warpCount <= 36) {
            return 4;
        } else if (warpCount <= 45) {
            return 5;
        } else if (warpCount <= 56) {
            return 6;
        } else {
            throw new RuntimeException("[WarpChest] Warps count to high! Please update plugin!");
        }
    }

    Inventory getInventory() {
        return inventory;
    }
}
