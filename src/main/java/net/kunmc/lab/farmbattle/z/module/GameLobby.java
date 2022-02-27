package net.kunmc.lab.farmbattle.z.module;

import net.kunmc.lab.farmbattle.z.Z;
import net.kunmc.lab.farmbattle.z.ZModule;

public class GameLobby extends ZModule {
    int t = 0;
    Z z;
    int limit = 0;
    public GameLobby(Z z) {
        super();
        this.z = z;
        this.limit = z.getConfig().getInt("lobby-time");
    }

    @Override
    public void call() {

    }
}
