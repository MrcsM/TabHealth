package me.baneofsmite.tabhealth;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Iterator;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getMainScoreboard();
        Objective below;
        if (board.getObjective("HealthBelow") == null) {
            below = board.registerNewObjective("HealthBelow", "dummy");
        } else {
            below = board.getObjective("HealthBelow");
        }

        Objective tab;
        if (board.getObjective("HealthTab") == null) {
            tab = board.registerNewObjective("HealthTab", "dummy");
        } else {
            tab = board.getObjective("HealthTab");
        }

        below.setDisplaySlot(DisplaySlot.BELOW_NAME);
        below.setDisplayName(ChatColor.AQUA + "❤");
        tab.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            Iterator var3 = Bukkit.getOnlinePlayers().iterator();

            while (var3.hasNext()) {
                CraftPlayer player = (CraftPlayer) var3.next();
                EntityPlayer entityPlayer = player.getHandle();
                int health = (int)Math.floor((player.getHealth() / 20.0D * 100.0D) + entityPlayer.getAbsorptionHearts() * 5);
                below.getScore(player.getName()).setScore(health);
                tab.getScore(player.getName()).setScore(health);
            }
        }, 0L, 10L);
    }
}
