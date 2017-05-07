package net.ksm.mcp;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private FileManager fileManager;

    public Main() {
        this.fileManager = new FileManager(this);
    }

    public void onEnable() {
        getLogger().info("Plugin by. K0SM053 - https://github.com/kosmo53");
        fileManager.initConfigFileStructure();

        BukkitFacade bukkitFacade = new BukkitFacade();
        FileConfiguration config = getConfig();

        WarpPhoneBook warpPhoneBook = new WarpPhoneBook(bukkitFacade, config);

        warpPhoneBook.initPhoneBook();

        PlayerListener playerListener = new PlayerListener(warpPhoneBook, config);
        Compass compassListener = new Compass(config);

        getServer().getPluginManager().registerEvents(playerListener, this);
        getServer().getPluginManager().registerEvents(compassListener, this);

        getCommand("warpchest").setExecutor(new CmdExec(warpPhoneBook, config, bukkitFacade, config));
    }


}