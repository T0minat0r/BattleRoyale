package de.mmocraft.listener;

import de.mmocraft.main.BattleRoyale;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.getEntity().sendMessage(BattleRoyale.main.prefix + "§c§l† §cDu bist gestorben! Dadurch hast du §e1 Leben §cverloren!");
        if (e.getEntity().getType() == EntityType.PLAYER) {
            BattleRoyale.getInstance().getConfig().set("PlayerData." + e.getEntity().getDisplayName() + ".Lifes", Integer.valueOf(BattleRoyale.getInstance().getConfig().getString("PlayerData." + e.getEntity().getDisplayName() + ".Lifes")) -1);
            BattleRoyale.getInstance().saveConfig();
        }
    }
}
