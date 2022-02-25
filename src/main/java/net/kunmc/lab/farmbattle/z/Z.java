package net.kunmc.lab.farmbattle.z;

import lombok.Getter;
import lombok.Setter;
import net.kunmc.lab.farmbattle.FarmBattle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.Configuration;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Z {

    @Getter
    private int status;
    @Getter @Setter
    private int time;
    @Getter
    private int timeLimit;

    @Getter @Setter
    private Team team1;
    @Getter @Setter
    private Team team2;

    @Getter @Setter
    private BossBar bossbar_team1;
    @Getter @Setter
    private BossBar bossbar_team2;
    @Getter @Setter
    private BossBar bossbar_master;

    @Getter @Setter
    private String sbTitle = "タイトル未設定";

    @Getter
    private FarmBattle plugin;

    @Getter
    private Map<Integer, ZModule> modules;
    @Getter
    private BukkitTask thread;
    @Getter
    private Configuration config;

    public Z(FarmBattle farmBattle) {
        this.plugin = farmBattle;
        this.config = plugin.getConfig();

        this.time = 0;
        this.timeLimit = config.getInt("time");
        this.status = 0;

        register();
        // start thread
        thread = new ZThread(this).runTaskTimer(farmBattle, 0, 20);
    }

    public void register() {
        modules = new HashMap<>();
        // register game modules
    }

    public void run() {
        if (modules.containsKey(status)) {
            modules.get(status).call();
        } else {
            status = 0;
            if (modules.containsKey(0)) {
                modules.get(0).call();
            }
        }
    }
}
