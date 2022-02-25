package net.kunmc.lab.farmbattle.z;

import org.bukkit.scheduler.BukkitRunnable;

public class ZThread extends BukkitRunnable {

    private Z instance;

    public ZThread(Z instance) {
        this.instance = instance;
    }

    @Override
    public void run() {
        instance.run();
    }
}
