package net.ksm.mcp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

class WarpPhoneBook {

    private static final String GUI_NAME = "guiName";
    private final BukkitFacade bukkitFacade;
    private final FileConfiguration config;
    private Inventory inventory;

    WarpPhoneBook(BukkitFacade bukkitFaade, FileConfiguration fileConfiguration) {
        this.bukkitFacade = bukkitFaade;
        this.config = fileConfiguration;
    }
    void initPhoneBook() {
        System.out.println("[warpChest] Loaded warps count: " + bukkitFacade.getWarpCount());
        int size = getWarpHeight();
        System.out.println("[warpChest] Setting chest size to: " + size * 9 + " (" + size + " rows)");
        Integer skippedWarps = 0;
        this.inventory = Bukkit.createInventory(null, size * 9, config.getString(GUI_NAME));
        List<String> warpList = bukkitFacade.getWarps();
        for (int i = 0; i < warpList.size(); i++) {
            String warp = warpList.get(i);
            String itemID = "PAPER";
            Boolean enchant = false;
            Boolean lore = false;
            if (config.getString("Items." + warp + ".item") == null) {
            } else {
                itemID = config.getString("Items." + warp + ".item");
            }
            if (config.getString("Items." + warp + ".enchanted") == null) {
            } else {
                enchant = config.getBoolean("Items." + warp + ".enchanted");
            }
            if (config.getList("Items." + warp + ".lore") == null) {
            } else {
                lore = true;
            }
            if (config.getString("Items." + warp + ".visible") == null || config.getBoolean("Items." + warp + ".visible")) {
                setWarpItem(warp, i - skippedWarps, inventory, itemID, enchant, lore);
            } else {
                skippedWarps = skippedWarps + 1;
            }
        }
    }
    private void setWarpItem(String warpName, Integer warpIndex, Inventory i, String ID, Boolean enchanted, Boolean lore) {
        ItemStack item = new ItemStack(Material.getMaterial(ID));
        ItemMeta itemmeta = item.getItemMeta();
        if (lore){
            ArrayList<String> lore2 = new ArrayList<String>();
            List<String> lorelist = config.getStringList("Items." + warpName + ".lore");
            for(String s : lorelist){
                lore2.add(ChatColor.translateAlternateColorCodes('&', s));
            }
            itemmeta.setLore(lore2);
        }
        if (enchanted) {
            itemmeta.addEnchant(Enchantment.THORNS, 10, true);
            itemmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemmeta.setDisplayName(warpName);
        item.setItemMeta(itemmeta);
        if (config.getBoolean("itemNums")){
        item.setAmount(warpIndex + 1);}
        i.setItem(warpIndex, item);
    }
    void open(Player player) {
player.openInventory(inventory);
    }
    private int getWarpHeight() {
        Integer skippedWarps = 0;
        List<String> warpList = bukkitFacade.getWarps();
        for (int i = 0; i < warpList.size(); i++) {
            String warp = warpList.get(i);
            if (config.getString("Items." + warp + ".visible") == null || config.getBoolean("Items." + warp + ".visible")) {}
            else {skippedWarps = skippedWarps + 1;}}
        System.out.println("[warpChest] Skipped warps: " + skippedWarps);
        Integer fw = bukkitFacade.getWarpCount() - skippedWarps;
        System.out.println("[warpChest] Final result: " + fw);
        int warpCount = bukkitFacade.getWarpCount() - skippedWarps;
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
            throw new RuntimeException("[warpChest] Warps count to high! Please update plugin from https://github.com/kosmo53/warpChest/releases");
        }
    }
    Inventory getInventory() {
        return inventory;
    }
}
