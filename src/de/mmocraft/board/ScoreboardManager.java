package de.mmocraft.board;

import de.mmocraft.main.BattleRoyale;
import de.mmocraft.run.LootdropFunction;
import de.mmocraft.run.LootdropOpener;
import de.mmocraft.run.TimeChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.text.ParseException;

import static org.bukkit.scoreboard.DisplaySlot.BELOW_NAME;

public class ScoreboardManager {
    public static void setScoreBoard(Player player) throws ParseException {
        //Scoreboard
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective sidebar = board.registerNewObjective("sidebar", "dummy", "§6§lBattleRoyale");
        sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);
        //Lifesystem
        Objective obj = board.registerNewObjective("§c❤","§c❤");
        obj.setDisplaySlot(BELOW_NAME);
        obj.setDisplayName("§c❤");
        obj.getScore(player.getName()).setScore(Integer.parseInt(BattleRoyale.getInstance().getConfig().getString("PlayerData." + player.getDisplayName() + ".Lifes")));
        //Onlinecounter
        Team online = board.registerNewTeam("online");
        online.addEntry(ChatColor.RED + "" + ChatColor.RED);
        online.setPrefix("§8» §7Online: §e" + Bukkit.getOnlinePlayers().size() + "§7/§a" + Bukkit.getMaxPlayers());
        sidebar.getScore(ChatColor.RED + "" + ChatColor.RED).setScore(10);
        //Space
        Score space = sidebar.getScore("");
        space.setScore(1);
        Score space1 = sidebar.getScore("  ");
        space1.setScore(5);
        Score space3 = sidebar.getScore("    ");
        space3.setScore(7);
        Score space4 = sidebar.getScore("     ");
        space4.setScore(9);
        Score space5 = sidebar.getScore("      ");
        space5.setScore(11);
        //Killcounter
        Team kill = board.registerNewTeam("kill");
        kill.addEntry(ChatColor.WHITE + "" + ChatColor.WHITE);
        kill.setPrefix("§8» §7Kills: §c" + player.getStatistic(Statistic.PLAYER_KILLS));
        sidebar.getScore(ChatColor.WHITE + "" + ChatColor.WHITE).setScore(8);
        //Leben
        Team lives = board.registerNewTeam("lives");
        lives.addEntry(ChatColor.BLACK + "" + ChatColor.BLACK);
        lives.setPrefix("§8» §7Leben: §c" + BattleRoyale.getInstance().getConfig().getString("PlayerData." + player.getDisplayName() + ".Lifes") + "§c❤");
        sidebar.getScore(ChatColor.BLACK + "" + ChatColor.BLACK).setScore(6);
        //Eventtag
        Team lootdrop = board.registerNewTeam("lootdrop");
        lootdrop.addEntry(ChatColor.AQUA + "" + ChatColor.AQUA);
        lootdrop.setPrefix("§8» §6Lootdrop: §eN/A");
        sidebar.getScore(ChatColor.AQUA + "" + ChatColor.AQUA).setScore(4);
        //Nextevent
        Team nexevent = board.registerNewTeam("nextevent");
        nexevent.addEntry(ChatColor.YELLOW + ""+ ChatColor.YELLOW);
        nexevent.setPrefix("§8» §aNächstes Event: §e18Uhr");
        sidebar.getScore(ChatColor.YELLOW + "" + ChatColor.YELLOW).setScore(3);
        //Serverclose
        Team serverclose = board.registerNewTeam("serverclose");
        serverclose.addEntry(ChatColor.GREEN + "" + ChatColor.GREEN);
        serverclose.setPrefix("§8» §cServerstopp: §eN/A");
        sidebar.getScore(ChatColor.GREEN + "" + ChatColor.GREEN).setScore(2);
        //IPScore
        Score ip = sidebar.getScore("§9MMOCraft.de");
        ip.setScore(0);

        player.setScoreboard(board);
    }

    public static void updateScoreBoard(Player player) {
        new BukkitRunnable() {
            @Override
            public void run(){
                Scoreboard board = player.getScoreboard();
                if(board.getTeam("online")!= null) {
                    board.getTeam("online").setPrefix("§8» §7Online: §e" + Bukkit.getOnlinePlayers().size() + "§7/§a" + Bukkit.getMaxPlayers());
                }
                if (board.getTeam("kill")!= null) {
                    board.getTeam("kill").setPrefix("§8» §7Kills: §c" + player.getStatistic(Statistic.PLAYER_KILLS));
                }
                if(board.getTeam("lives")!= null) {
                    board.getTeam("lives").setPrefix("§8» §7Leben: §c" + BattleRoyale.getInstance().getConfig().getString("PlayerData." + player.getDisplayName()+ ".Lifes") + "§c❤");
                }
                if(board.getTeam("lootdrop")!= null) {
                    if(LootdropFunction.time == 300) {
                        if (!LootdropOpener.open) {
                            board.getTeam("lootdrop").setPrefix("§8» §6Lootdrop: §eN/A");
                        } else {
                            board.getTeam("lootdrop").setPrefix("§8» §6Lootdrop: §eN/A");
                        }
                    }
                }
                if(board.getTeam("nextevent")!= null) {
                    board.getTeam("nextevent").setPrefix("§8» §aNächstes Event: §e18Uhr");
                }
                if(board.getTeam("serverclose") != null){
                    try {
                        board.getTeam("serverclose").setPrefix("§8» §cServerstopp: §e" + TimeChecker.time());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if(board.getObjective("§c❤") != null) {
                    board.getObjective("§c❤").getScore(player.getName()).setScore(Integer.parseInt(BattleRoyale.getInstance().getConfig().getString("PlayerData." + player.getDisplayName()+ ".Lifes")));
                }
            }
        }.runTaskTimer(BattleRoyale.getInstance(), 0, 20L);
    }
}
