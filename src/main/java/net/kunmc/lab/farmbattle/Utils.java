package net.kunmc.lab.farmbattle;
// package net.kunmc.lab.%package%;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Team;

import java.util.*;

public class Utils {

    public static List<String> voidList() { return Collections.singletonList(""); }

    // Message
    public static void warning(HumanEntity h, String... m)  { Arrays.stream(m).forEach(s -> { h.sendMessage(ChatColor.YELLOW + "⚠" + s); }); }
    public static void error(HumanEntity h, String... m)    { Arrays.stream(m).forEach(s -> { h.sendMessage(ChatColor.RED + "✖" + s); });    }
    public static void success(HumanEntity h, String... m)  { Arrays.stream(m).forEach(s -> { h.sendMessage(ChatColor.GREEN + "✔" + s); });  }
    public static void info(HumanEntity h, String... m)     { Arrays.stream(m).forEach(s -> { h.sendMessage(ChatColor.AQUA + "☞" + s); });   }
    public static void warningAll(String... m)              { Arrays.stream(m).forEach(s -> { Bukkit.broadcastMessage(ChatColor.YELLOW + "⚠" + s); });  }
    public static void infoAll(String... m)                 { Arrays.stream(m).forEach(s -> { Bukkit.broadcastMessage(ChatColor.RED + "✖" + s); });     }
    public static void errorAll(String... m)                { Arrays.stream(m).forEach(s -> { Bukkit.broadcastMessage(ChatColor.GREEN + "✔" + s); });   }
    public static void successAll(String... m)              { Arrays.stream(m).forEach(s -> { Bukkit.broadcastMessage(ChatColor.AQUA + "☞" + s); });    }
    public static void broadcast(String... m)               { Arrays.stream(m).forEach(Bukkit::broadcastMessage);                                        }

    public static List<String> s(List<String> l) {
        Collections.sort(l);
        return l;
    }

    public static List<String> cl(List<String> l, String a) {
        if (a.length() < 1) return s(l);
        List<String> r = new ArrayList<>();
        for (String s : l) {
            if (s.startsWith(a)) {
                r.add(s);
            }
        }
        return s(r);
    }

    public static Team getTeam(String name) {
        return Bukkit.getScoreboardManager().getMainScoreboard().getTeam(name);
    }

    public static Team createTeam(String name) {
        return Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(name);
    }

    public static void sbAll(String... s) {
        Bukkit.getOnlinePlayers().forEach(p -> { sb(p, s); });
    }

    public static void sb(Player p, String... s) {
        String[] elements = Arrays.copyOf(s, 16);
        if (elements[0] == null) elements[0] = "タイトル未設定";
        if (elements[0].length() > 32) elements[0] = elements[0].substring(0, 32);
        for (int i = 1; i < elements.length; i++)
            if (elements[i] != null) if (elements[i].length() > 40) elements[i] = elements[i].substring(0, 40);
        try {
            p.getScoreboard();
            if (p.getScoreboard() == Bukkit.getScoreboardManager().getMainScoreboard() || p.getScoreboard().getObjectives().size() != 1) {
                p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            }
            if (p.getScoreboard().getObjective(p.getUniqueId().toString().substring(0, 16)) == null) {
                p.getScoreboard().registerNewObjective(p.getUniqueId().toString().substring(0, 16), "dummy");
                Objects.requireNonNull(p.getScoreboard().getObjective(p.getUniqueId().toString().substring(0, 16))).setDisplaySlot(DisplaySlot.SIDEBAR);
            }
            Objects.requireNonNull(p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)).setDisplayName(elements[0]);
            for (int i = 1; i < elements.length; i++)
                if (elements[i] != null)
                    if (Objects.requireNonNull(p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)).getScore(elements[i]).getScore() != 16 - i) {
                        Objects.requireNonNull(p.getScoreboard().getObjective(DisplaySlot.SIDEBAR)).getScore(elements[i]).setScore(16 - i);
                        for (String string : p.getScoreboard().getEntries())
                            if (Objects.requireNonNull(p.getScoreboard().getObjective(p.getUniqueId().toString().substring(0, 16))).getScore(string).getScore() == 16 - i)
                                if (!string.equals(elements[i])) p.getScoreboard().resetScores(string);
                    }
            for (String entry : p.getScoreboard().getEntries()) {
                boolean toErase = true;
                for (String element : elements) {
                    if (element != null && element.equals(entry) && Objects.requireNonNull(p.getScoreboard().getObjective(p.getUniqueId().toString().substring(0, 16))).getScore(entry).getScore() == 16 - Arrays.asList(elements).indexOf(element)) {
                        toErase = false;
                        break;
                    }
                }
                if (toErase) p.getScoreboard().resetScores(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
