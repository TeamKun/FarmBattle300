package net.kunmc.lab.farmbattle.z.module;

import net.kunmc.lab.farmbattle.Utils;
import net.kunmc.lab.farmbattle.z.Z;
import net.kunmc.lab.farmbattle.z.ZModule;

public class GamePlaying extends ZModule {
    public GamePlaying(Z z) {
        super();
        z = z;
    }

    int time = 0;
    int time_limit = 400;

    Z z;

    @Override
    public void call() {
        System.out.println("GamePlaying");
        time++;
        if (time > time_limit) {
            time = 0;
            z.setStatus(3);
        }
        // time message
        Utils.infoAll("time: " + time);
    }
}
