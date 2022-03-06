package net.kunmc.lab.farmbattle.z;

import lombok.Getter;
import lombok.Setter;
import net.kunmc.lab.farmbattle.FarmBattle;
import net.kunmc.lab.farmbattle.z.module.GameEnd;
import net.kunmc.lab.farmbattle.z.module.GameLobby;
import net.kunmc.lab.farmbattle.z.module.GamePlaying;
import net.kunmc.lab.farmbattle.z.module.GameResult;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.Configuration;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Z {

    public static int STATUS_LOBBY = 0;
    public static int STATUS_PLAYING = 1;
    public static int STATUS_RESULT = 2;
    public static int STATUS_END = 3;

    @Getter @Setter
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
        modules.put(STATUS_LOBBY, new GameLobby(this));
        modules.put(STATUS_PLAYING, new GamePlaying(this));
        modules.put(STATUS_RESULT, new GameResult(this));
        modules.put(STATUS_END, new GameEnd(this));

    }

    public void reset() {
        status = 0;
        time = 0;
        if (modules.containsKey(0)) {
            modules.get(0).call();
        }
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
