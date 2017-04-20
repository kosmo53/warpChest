package net.ksm.mcp;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;


import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class FileManager {
    private static YamlConfiguration msg;
    private final JavaPlugin main;

    FileManager(JavaPlugin main) {this.main = main;}
    
    private Logger getLogger(){
        return main.getLogger();
    }

    void initConfigFileStructure() {
        File dataFolder = main.getDataFolder();
        if (!dataFolder.exists()) {
            getLogger().info("Creating WarpChest dir");
            dataFolder.mkdir();
        }
        File configFile = new File(dataFolder, "config.yml");
        if (!configFile.exists()) {
            getLogger().info("Creating config.yml");
            main.saveDefaultConfig();
        }
        getLogger().info("Loading configs intro local plugin config");
        main.reloadConfig();
        getLogger().info("DONE !");
    }


    public void addWarps(List warp) {
    //test

    }

}