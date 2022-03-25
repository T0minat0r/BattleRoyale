package de.mmocraft.commands;

import de.mmocraft.main.BattleRoyale;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConfigCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage(BattleRoyale.main.prefix + "§cNutze: §7/config reload");
        } else if (args[0].equals("reload")) {
            p.sendMessage(BattleRoyale.main.prefix + "§aReloading Config....");
            BattleRoyale.getInstance().reloadConfig();
        }
        return true;
    }
}
