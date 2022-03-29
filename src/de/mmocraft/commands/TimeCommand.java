package de.mmocraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (args.length == 0) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            p.sendMessage(dtf.format(now));
        }
        return true;
    }
}
