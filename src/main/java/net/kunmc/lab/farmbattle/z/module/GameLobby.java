package net.kunmc.lab.farmbattle.z.module;

import lombok.Getter;
import lombok.Setter;
import net.kunmc.lab.farmbattle.Utils;
import net.kunmc.lab.farmbattle.z.Z;
import net.kunmc.lab.farmbattle.z.ZModule;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class GameLobby extends ZModule {
    @Getter @Setter
    int t = 0;
    Z z;
    @Getter @Setter
    int limit = 0;
    @Getter @Setter
    BossBar bar;
    public GameLobby(Z z) {
        super();
        this.z = z;
        this.limit = z.getConfig().getInt("lobby-time");
        this.bar = Bukkit.createBossBar("游戏将在" + limit + "秒后开始", BarColor.BLUE, BarStyle.SOLID);
    }

    @Override
    public void call() {
        if (t >= limit) {
            z.setStatus(2);
            bar.removeAll();
        } else {
            t++;
            int remain = limit - t;
            bar.setTitle("游戏将在" + remain + "秒后开始");
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!bar.getPlayers().contains(p)) {
                    bar.addPlayer(p);
                }
            }
            if (remain % 10 == 0 && remain >= 10) {
                Utils.infoAll("游戏将在" + remain + "秒后开始");
            } else if (remain <= 5) {
                Utils.infoAll("游戏将在" + remain + "秒后开始");
            }
        }
    }
}
