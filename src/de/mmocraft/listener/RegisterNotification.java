package de.mmocraft.listener;

import de.mmocraft.board.ScoreboardManager;
import de.mmocraft.main.BattleRoyale;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class RegisterNotification implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(!(BattleRoyale.getInstance().getConfig().getConfigurationSection("PlayerData").contains(e.getPlayer().getDisplayName()))) {
            e.getPlayer().sendMessage(BattleRoyale.main.prefix + "§cDu bist noch nicht beim Event §eregistriert§c!");
            e.getPlayer().sendMessage(BattleRoyale.main.prefix + "§aRegistriere dich mit §e/register§a!");
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
        } else {
            ScoreboardManager.setScoreBoard(e.getPlayer());
            ScoreboardManager.updateScoreBoard(e.getPlayer());
        }
    }
}
