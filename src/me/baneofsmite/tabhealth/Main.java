package me.baneofsmite.tabhealth;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		if (Bukkit.getScoreboardManager().getMainScoreboard().getObjective("HealthTabPL") == null) {
			Bukkit.getScoreboardManager().getMainScoreboard().registerNewObjective("HealthTabPL", "Dummy");
			Bukkit.getScoreboardManager().getMainScoreboard().getObjective("HealthTabPL")
					.setDisplaySlot(DisplaySlot.PLAYER_LIST);
		}
		if (Bukkit.getScoreboardManager().getMainScoreboard().getObjective("HealthNamePL") == null) {
			Bukkit.getScoreboardManager().getMainScoreboard().registerNewObjective("HealthNamePL", "Dummy")
					.setDisplayName(ChatColor.RED + "❤");
			Bukkit.getScoreboardManager().getMainScoreboard().getObjective("HealthNamePL")
					.setDisplaySlot(DisplaySlot.BELOW_NAME);
		}
		BukkitScheduler scheduler = getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				for (Player player : Bukkit.getServer().getOnlinePlayers()) {
					ScoreboardManager manager = Bukkit.getScoreboardManager();
					Scoreboard board = manager.getMainScoreboard();
					Objective objective = board.getObjective("HealthTabPL");
					Objective objective2 = board.getObjective("HealthNamePL");
					Score score1 = objective.getScore(player.getName());
					Score score2 = objective2.getScore(player.getName());
					score1.setScore((int) Math.floor((player.getHealth() / 20) * 100));
					score2.setScore((int) Math.floor((player.getHealth() / 20) * 100));
				}
			}
		}, 0L, 10L);
	}
}