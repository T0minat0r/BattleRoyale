package de.mmocraft.listener;

import de.mmocraft.main.BattleRoyale;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(BattleRoyale.getInstance().getConfig().getBoolean("Messages.Enabled")) {
            if (BattleRoyale.getInstance().getConfig().getBoolean("Messages.PlayerCount.Enabled")) {
                if (!(BattleRoyale.getInstance().getConfig().getStringList("Operators").contains(e.getPlayer().getDisplayName()))) {
                    e.setJoinMessage(BattleRoyale.getInstance().getConfig().getString("Messages.Join").replace("&", "§").replace("%player%", e.getPlayer().getDisplayName())
                            + " " + BattleRoyale.getInstance().getConfig().getString("Messages.PlayerCount.Message").replace("&", "§").replace("%playercount%", Integer.toString(Bukkit.getOnlinePlayers().size())));
                } else {
                    e.setJoinMessage(BattleRoyale.getInstance().getConfig().getString("Messages.Join").replace("&", "§").replace("%player%", "§c§lADMIN §c" + e.getPlayer().getDisplayName())
                            + " " + BattleRoyale.getInstance().getConfig().getString("Messages.PlayerCount.Message").replace("&", "§").replace("%playercount%", Integer.toString(Bukkit.getOnlinePlayers().size())));
                }
            } else {
                e.setJoinMessage(BattleRoyale.getInstance().getConfig().getString("Messages.Join").replace("&", "§").replace("%player%", e.getPlayer().getDisplayName()));
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if(BattleRoyale.getInstance().getConfig().getBoolean("Messages.Enabled")) {
            if (BattleRoyale.getInstance().getConfig().getBoolean("Messages.PlayerCount.Enabled")) {
                if (!(BattleRoyale.getInstance().getConfig().getStringList("Operators").contains(e.getPlayer().getDisplayName()))) {
                    e.setQuitMessage(BattleRoyale.getInstance().getConfig().getString("Messages.Quit").replace("&", "§").replace("%player%", e.getPlayer().getDisplayName())
                            + " " + BattleRoyale.getInstance().getConfig().getString("Messages.PlayerCount.Message").replace("&", "§").replace("%playercount%", Integer.toString(Bukkit.getOnlinePlayers().size() - 1)));
                } else {
                    e.setQuitMessage(BattleRoyale.getInstance().getConfig().getString("Messages.Quit").replace("&", "§").replace("%player%", "§c§lADMIN §c" + e.getPlayer().getDisplayName())
                            + " " + BattleRoyale.getInstance().getConfig().getString("Messages.PlayerCount.Message").replace("&", "§").replace("%playercount%", Integer.toString(Bukkit.getOnlinePlayers().size() - 1)));
                }
            } else {
                e.setQuitMessage(BattleRoyale.getInstance().getConfig().getString("Messages.Quit").replace("&", "§").replace("%player%", e.getPlayer().getDisplayName()));
            }
        }
    }
}
