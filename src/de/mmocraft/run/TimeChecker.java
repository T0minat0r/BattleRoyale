package de.mmocraft.run;

import de.mmocraft.main.BattleRoyale;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeChecker {
    public static String time() throws ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String current = dtf.format(now);
        String start = "02:00:00";
        String end = "18:00:00"; // = 19:00UHR!!!!!!
        String warn1h = "01:00:00";
        String warn5m = "00:05:00";
        String warn4m = "00:04:00";
        String warn3m = "00:03:00";
        String warn2m = "00:02:00";
        String warn1m = "00:01:00";
        String warn30s = "00:00:30";
        String warn10s = "00:00:10";
        String warn0s = "00:00:01";

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date currenttime = format.parse(current);
        Date starttime = format.parse(start);
        Date endtime = format.parse(end);
        Date dwarn1h = format.parse(warn1h);
        Date dwar5m = format.parse(warn5m);
        Date dwarn4m = format.parse(warn4m);
        Date dwarn3m = format.parse(warn3m);
        Date dwarn2m = format.parse(warn2m);
        Date dwarn1m = format.parse(warn1m);
        Date dwarn30s = format.parse(warn30s);
        Date dwarn10s = format.parse(warn10s);
        Date dwarn0s = format.parse(warn0s);

        long difference = endtime.getTime() - currenttime.getTime();

        if(difference == starttime.getTime()) {
            BattleRoyale.getInstance().getConfig().set("Server.Join.Enabled", true);
            BattleRoyale.getInstance().saveConfig();
            Bukkit.broadcastMessage("§aDer Server ist nun für alle Spieler geöffnet!");
        }
        if (difference == dwarn1h.getTime()) {
            Bukkit.getServer().broadcastMessage("§cDer Server schließt in §e1 Stunde§c!");
        }
        if (difference == dwar5m.getTime()) {
            Bukkit.getServer().broadcastMessage("§cDer Server schließt in §e5 Minuten§c!");
        }
        if (difference == dwarn4m.getTime()) {
            Bukkit.getServer().broadcastMessage("§cDer Server schließt in §e4 Minuten§c!");
        }
        if (difference == dwarn3m.getTime()) {
            Bukkit.getServer().broadcastMessage("§cDer Server schließt in §e3 Minuten§c!");
        }
        if (difference == dwarn2m.getTime()) {
            Bukkit.getServer().broadcastMessage("§cDer Server schließt in §e2 Minuten§c!");
        }
        if (difference == dwarn1m.getTime()) {
            Bukkit.getServer().broadcastMessage("§cDer Server schließt in §eeiner Minute§c!");
        }
        if (difference == dwarn30s.getTime()) {
            Bukkit.getServer().broadcastMessage("§cDer Server schließt in §e30 Sekunden§c!");
            Bukkit.getServer().broadcastMessage("§aBitte stelle sicher das du dich an einem sicheren Ort befindest.");
        }
        if (difference == dwarn10s.getTime()) {
            Bukkit.broadcastMessage("§cDer Server schließt in §e10 Sekunden§c!");
        }
        if (difference == dwarn0s.getTime()) {
            Bukkit.getServer().broadcastMessage("§cDer Server wird nun damit beginnen die Spieler zu kicken! ");
            BattleRoyale.getInstance().getConfig().set("Server.Join.Enabled", false);
            BattleRoyale.getInstance().saveConfig();
            for (Player target : Bukkit.getOnlinePlayers()) {
                if (!BattleRoyale.getInstance().getConfig().getStringList("Operators").contains(target.getDisplayName())) {
                    target.kickPlayer("§cDer Server ist bis morgen §e" + BattleRoyale.getInstance().getConfig().getString("Server.Close") + " Uhr §cgeschlossen! Schaue dort wieder vorbei!");
                }
            }
        }
        return(new SimpleDateFormat("HH:mm:ss").format(difference));
    }
}
