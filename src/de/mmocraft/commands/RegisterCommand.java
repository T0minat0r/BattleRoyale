package de.mmocraft.commands;

import de.mmocraft.main.BattleRoyale;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegisterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (BattleRoyale.getInstance().getConfig().getBoolean("Server.Register.Enabled")) {
            if (args.length > 0) {
                p.sendMessage(BattleRoyale.main.prefix + "§cNutze: §7/register");
            } else {
                if(!(BattleRoyale.getInstance().getConfig().getConfigurationSection("PlayerData").contains(p.getDisplayName()))) {
                    p.kickPlayer(BattleRoyale.main.prefix + "§aDu hast dich erfolgreich im Event registriert!");
                    BattleRoyale.getInstance().getConfig().getConfigurationSection("PlayerData").createSection(p.getDisplayName());
                    BattleRoyale.getInstance().getConfig().set("PlayerData." + p.getDisplayName(), ".Lifes:");
                    BattleRoyale.getInstance().getConfig().set("PlayerData." + p.getDisplayName() + ".Lifes", '3');
                    BattleRoyale.getInstance().saveConfig();
                } else {
                    p.sendMessage(BattleRoyale.main.prefix + "§cDu bist bereits registriert!");
                }
            }
        }
        return true;
    }
}
