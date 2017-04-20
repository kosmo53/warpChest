package net.ksm.mcp;

import com.earth2me.essentials.Essentials;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BukkitFacade {

    private final Essentials essentials;

    BukkitFacade() {
        this.essentials = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");
    }

    int getWarpCount(){
        return this.essentials.getWarps().getCount();
    }

    List<String> getWarps(){
        return new ArrayList<String>(this.essentials.getWarps().getList());
    }

}
