package de.mmocraft.commands;


import de.mmocraft.main.BattleRoyale;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LifeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
            if(args.length == 0) {
                p.sendMessage( BattleRoyale.main.prefix + "§cNutze: §7/life set <Spieler> <Nummer>");
                return false;
            }
            if (args[0].equals("set")) {
                if (args.length == 3) {
                    Player pl = Bukkit.getPlayer(args[1]);
                    if (args[2].contains("-")) {
                        p.sendMessage(BattleRoyale.main.prefix + "§cDie Leben dürfen nicht negativ sein!");
                    } else {
                        if (BattleRoyale.getInstance().getConfig().getConfigurationSection("PlayerData").contains(args[1])) {
                            p.sendMessage(BattleRoyale.main.prefix +"§7Du hast die §cLeben §7von §a" + args[1] + "§7 auf §a" + args[2] + " §7gesetzt!");
                            if(pl != null) {
                                pl.sendMessage(BattleRoyale.main.prefix + "§7Deine §cLeben §7wurden von §a " + p.getDisplayName() + "§7 auf §a" + args[2] + " §7gesetzt!");
                            }
                            BattleRoyale.getInstance().getConfig().set("PlayerData." + args[1] + ".Lifes", args[2]);
                            BattleRoyale.getInstance().saveConfig();
                            return true;
                        } else {
                            p.sendMessage(BattleRoyale.main.prefix + "§cDer Spieler §e" + args[1] +  " §cist nicht in der Config registriert!");
                            return true;
                        }
                    }
                } else {
                    p.sendMessage(BattleRoyale.main.prefix + "§cNutze: §7/life set <Spieler> <Nummer>");
                }
        }else {
                p.sendMessage(BattleRoyale.main.prefix + "§cNutze: §7/life set <Spieler> <Nummer>");
            }
        return true;
    }
}
