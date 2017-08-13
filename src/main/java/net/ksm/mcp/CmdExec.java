package net.ksm.mcp;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CmdExec implements CommandExecutor {
    private final WarpPhoneBook warpPhoneBook;
    private final FileConfiguration config;

    CmdExec(WarpPhoneBook warpPhoneBook, FileConfiguration fileConfiguration, BukkitFacade bukkitFacade, FileConfiguration config) {
        this.warpPhoneBook = warpPhoneBook;

        this.config = config;
    }

    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length < 1) {
                warpPhoneBook.open(p);
                return true;
            } else if (args.length > 0 && args[0].equalsIgnoreCase("rl") || args[0].equalsIgnoreCase("reload")) {
                if (p.hasPermission("warpchest.reload")) {
                    Main.getInst().reloadConfig();
                }

            } else if (args.length > 0 && args[0].equalsIgnoreCase("help")) {
                if (p.hasPermission("warpchest.chest")) {
                    p.sendMessage(ChatColor.GRAY + "/warpchest");
                }
                if (p.hasPermission("warpchest.set.enchanted")) {
                    p.sendMessage(ChatColor.GRAY + "/warpchest set enchanted [warp] [true/false]");
                }
                if (p.hasPermission("warpchest.set.item")) {
                    p.sendMessage(ChatColor.GRAY + "/warpchest set item [warp] [itemMaterial]");
                }
                if (p.hasPermission("warpchest.set.lore")) {
                    p.sendMessage(ChatColor.GRAY + "/warpchest set lore [warp] [line] [text]");
                }
                if (p.hasPermission("warpchest.set.item")) {
                    p.sendMessage(ChatColor.GRAY + "/warpchest set visible [warp] [true/false]");
                }
                if (p.hasPermission("warpchest.reload")) {
                    p.sendMessage(ChatColor.GRAY + "/warpchest reload");
                }
                p.sendMessage(ChatColor.GRAY + "/warpchest help");
            }
            if (args.length > 0 && args[0].equalsIgnoreCase("set")) {
                if (p.hasPermission("warpchest.set")) {
                    if (args[1].equalsIgnoreCase("item")) {
                        config.set("Items." + args[2] + ".item", args[3]);
                        sender.sendMessage("Ustawiono item " + args[3] + " dla teleportu " + args[2]);
                    }


                } else {
                    sender.sendMessage("Nope, poprosze prawko");
                }

            }

        }
        return false;
    }
}
