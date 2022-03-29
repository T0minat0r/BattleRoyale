package de.mmocraft.listener;

import de.mmocraft.board.ScoreboardManager;
import de.mmocraft.main.BattleRoyale;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.text.ParseException;


public class KickManager implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws ParseException {
        if(BattleRoyale.getInstance().getConfig().getBoolean("Server.Join.Enabled")) {
            if(BattleRoyale.getInstance().getConfig().getBoolean("Server.Register.Enabled")) {
                if(!(BattleRoyale.getInstance().getConfig().getConfigurationSection("PlayerData").contains(e.getPlayer().getDisplayName()))) {
                    e.getPlayer().sendMessage(BattleRoyale.main.prefix + "§cDu bist noch nicht beim Event §eregistriert§c!");
                    e.getPlayer().sendMessage(BattleRoyale.main.prefix + "§aRegistriere dich mit §e/register§a!");
                    e.getPlayer().setGameMode(GameMode.ADVENTURE);
                } else {
                    e.getPlayer().kickPlayer("§aDu bist bereits beim Event registriert! Bitte warte bis es beginnt!");
                }
            } else {
                if(!(BattleRoyale.getInstance().getConfig().getConfigurationSection("PlayerData").contains(e.getPlayer().getDisplayName()))) {
                    e.getPlayer().kickPlayer("§cDu kannst dich aktuell nicht mehr registrieren!");
                } else {
                    ScoreboardManager.setScoreBoard(e.getPlayer());
                    ScoreboardManager.updateScoreBoard(e.getPlayer());
                }
            }
        } else {
            if(!(BattleRoyale.getInstance().getConfig().getStringList("Operators").contains(e.getPlayer().getDisplayName()))) {
                e.getPlayer().kickPlayer("§cDer Server ist aktuell geschlossen! Bitte schaue ab §e17Uhr §cwieder vorbei!");
            } else {
                e.getPlayer().sendMessage("§aDa du ein §cAdmin §abist, wurde dir der Zutritt zum Server gewährt!");
                e.getPlayer().setGameMode(GameMode.CREATIVE);
            }

        }
    }
}
