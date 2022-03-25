package de.mmocraft.run;

import de.mmocraft.board.ScoreboardManager;
import de.mmocraft.main.BattleRoyale;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.org.apache.maven.artifact.repository.metadata.Plugin;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeChecker {
    public static String time;

    public static void schedule() {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        Date date = new Date(currentTime);
        time = simpleDateFormat.format(date);
    }

    public static void checktime(){
        new BukkitRunnable() {
            @Override
            public void run(){
               if(time.equals(BattleRoyale.getInstance().getConfig().getString("Server.Open"))) {
                   schedule();
               }
            }
        }.runTaskTimer(BattleRoyale.getInstance(), 20, 20);
    }
}
