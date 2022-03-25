package de.mmocraft.commands;

import de.mmocraft.main.BattleRoyale;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnregisterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(args.length >0) {
            p.sendMessage(BattleRoyale.main.prefix + "§cNutze: §7/unregister");
        } else {
            p.kickPlayer(BattleRoyale.main.prefix + "§aDu hast dich erfolgreich vom Event abgemeldet!");
            BattleRoyale.getInstance().getConfig().set("PlayerData." + p.getDisplayName(), null);
            BattleRoyale.getInstance().getConfig().set("PlayerData." + p.getDisplayName()+ ".Lifes", null);
            BattleRoyale.getInstance().saveConfig();
            BattleRoyale.getInstance().getConfig().set("PlayerData." + p.getDisplayName(), null);
            BattleRoyale.getInstance().saveConfig();
        }
        return true;
    }
}
