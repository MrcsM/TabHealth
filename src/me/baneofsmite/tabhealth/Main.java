package me.baneofsmite.tabhealth;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

public class Main extends JavaPlugin { //Leon Is Awesome, Play on Arctic. Also Bane Is Cool, Play on 2 Donkeys

    @Override
    public void onEnable() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getMainScoreboard();

        Objective name;
        Objective tab;

        if (board.getObjective("HealthNamePL") == null) {
            name = board.registerNewObjective("HealthNamePL", "dummy");
        } else {
            name = board.getObjective("HealthNamePL");
        }

        if (board.getObjective("HealthTabPL") == null) {
            tab = board.registerNewObjective("HealthTabPL", "dummy");
        } else {
            tab = board.getObjective("HealthTabPL");
        }

        name.setDisplaySlot(DisplaySlot.BELOW_NAME);
        name.setDisplayName(ChatColor.RED + "❤");
        
        tab.setDisplaySlot(DisplaySlot.PLAYER_LIST);

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                int health = (int) Math.floor((player.getHealth() / 20) * 100);
                
                name.getScore(player.getName()).setScore(health);
                tab.getScore(player.getName()).setScore(health);
            }
        }, 1L, 1L);
    }
}
