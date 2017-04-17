package net.ksm.mcp;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private FileManager fileManager;

    public Main() {
        this.fileManager = new FileManager(this);
    }

    public void onEnable() {
        getLogger().info("[WarpChest] Plugin by. K0SM053");
        fileManager.initConfigFileStructure();

        BukkitFacade bukkitFacade = new BukkitFacade();
        FileConfiguration config = getConfig();

        WarpPhoneBook warpPhoneBook = new WarpPhoneBook( bukkitFacade, config);

        warpPhoneBook.initPhoneBook();

        PlayerListener playerListener = new PlayerListener(warpPhoneBook, config);
        Compass compassListener = new Compass();

        getServer().getPluginManager().registerEvents(playerListener, this);
        getServer().getPluginManager().registerEvents(compassListener, this);

        getCommand("teleporty").setExecutor(new CmdExec(warpPhoneBook, config));


        getLogger().info("[WarpChest] Loaded warps count: " + bukkitFacade.getWarpCount());
    }
}