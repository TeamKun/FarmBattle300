package net.kunmc.lab.farmbattle.z.module;

import net.kunmc.lab.farmbattle.Utils;
import net.kunmc.lab.farmbattle.z.Z;
import net.kunmc.lab.farmbattle.z.ZModule;

public class GameResult extends ZModule {
    public GameResult(Z z) {
        super();
        this.z = z;
    }


    Z z;

    int team1_count;
    int team2_count;

    @Override
    public void call() {
        Utils.infoAll("游戏结束");

        // todo: count team

        if (team1_count > team2_count) {
            Utils.infoAll("team1 win");
        } else {
            Utils.infoAll("team2 win");
        }
        z.setStatus(4);

    }
}
