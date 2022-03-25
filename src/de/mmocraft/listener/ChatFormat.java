package de.mmocraft.listener;

import de.mmocraft.main.BattleRoyale;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormat implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (!(BattleRoyale.getInstance().getConfig().getStringList("Operators").contains(e.getPlayer().getDisplayName()))) {
            e.setFormat(BattleRoyale.getInstance().getConfig().getString("Messages.ChatFormat").replace("%player%", e.getPlayer().getDisplayName()).replace("%message%", e.getMessage()).replace("%life%", "§c" + BattleRoyale.getInstance().getConfig().getString("PlayerData." + e.getPlayer().getDisplayName() + ".Lifes") + "❤"));
        } else {
            e.setFormat(BattleRoyale.getInstance().getConfig().getString("Messages.AdminFormat").replace("%player%", e.getPlayer().getDisplayName()).replace("%message%", e.getMessage()).replace("&", "§").replace("%life%", "§c" + BattleRoyale.getInstance().getConfig().getString("PlayerData." + e.getPlayer().getDisplayName() + ".Lifes") + "❤"));
        }
    }
}
