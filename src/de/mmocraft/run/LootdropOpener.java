package de.mmocraft.run;

import de.mmocraft.main.BattleRoyale;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class LootdropOpener implements Listener {
    public static boolean open = false;
    public static String formatMMSS(long secondsCount) {
        int seconds = (int) (secondsCount %60);
        secondsCount -= seconds;
        long minutesCount = secondsCount / 60;
        long minutes = minutesCount % 60;
        minutesCount -= minutes;
        long hoursCount = minutesCount / 60;
        return "" + minutes + ":" + seconds;
    }
    @EventHandler
    public void onInteract (PlayerInteractAtEntityEvent e){
        if (e.getRightClicked().equals(LootdropFunction.as)) {
            if (e.getRightClicked().getName().equals("§6§l§ke §6§lLootdrop §6§l§ke")) {
                if (!open) {
                    if(LootdropFunction.time > 9) {
                        e.getPlayer().sendMessage(BattleRoyale.getInstance().prefix + "§aDer Lootdrop kann erst in §e" + formatMMSS(LootdropFunction.time) + " §ageöffnet werden!");
                        e.setCancelled(true);
                    } else {
                        e.getPlayer().sendMessage(BattleRoyale.getInstance().prefix + "§aDer Lootdrop kann erst in §e0:0" + LootdropFunction.time + " §ageöffnet werden!");
                        e.setCancelled(true);
                    }
                } else {
                    e.getPlayer().sendMessage(BattleRoyale.getInstance().prefix + "§cCOMING SOON!");
                    e.setCancelled(true);
                }
            }
        }
    }
}
