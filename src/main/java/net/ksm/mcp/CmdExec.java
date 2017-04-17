package net.ksm.mcp;

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
        }
        return false;
    }
}
