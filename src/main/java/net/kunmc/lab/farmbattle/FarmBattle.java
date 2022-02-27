package net.kunmc.lab.farmbattle;

import net.kunmc.lab.farmbattle.z.Z;
import org.bukkit.plugin.java.JavaPlugin;

public final class FarmBattle extends JavaPlugin {

    private Z z;

    @Override
    public void onEnable() {
        // Plugin startup logic

        saveDefaultConfig();
        z = new Z(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Z getZ() {
        return z;
    }
}
