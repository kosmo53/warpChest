package net.ksm.mcp;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CmdExec implements CommandExecutor {
    private final WarpPhoneBook warpPhoneBook;
    private final FileConfiguration config;

    CmdExec(WarpPhoneBook warpPhoneBook, FileConfiguration fileConfiguration) {
        this.warpPhoneBook = warpPhoneBook;
        this.config = fileConfiguration;
    }

    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            warpPhoneBook.open(p);
            if (args.length > 0 && args[0].equalsIgnoreCase("rl") || args[0].equalsIgnoreCase("reload")){
                if (p.hasPermission("warpchest.chest")) {
                    p.sendMessage(ChatColor.GRAY + "/warpchest");
                }
                if (p.hasPermission("autoplot.set.item")) {
                    p.sendMessage(ChatColor.GRAY + "/warpchest set item");
                }
                if (p.hasPermission("autoplot.set.enchanted")) {
                    p.sendMessage(ChatColor.GRAY + "/warpchest set enchanted");
                }
                if (p.hasPermission("autoplot.set.lore")) {
                    p.sendMessage(ChatColor.GRAY + "/warpchest set lore");
                }
                if (p.hasPermission("autoplot.set.item")) {
                    p.sendMessage(ChatColor.GRAY + "/warpchest set visible");
                }
        }

    }
return false;}
}
