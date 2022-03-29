package de.mmocraft.commands;

import de.mmocraft.main.BattleRoyale;
import de.mmocraft.run.LootdropFunction;
import net.minecraft.server.v1_16_R3.Block;
import net.minecraft.server.v1_16_R3.BlockBase;
import net.minecraft.server.v1_16_R3.DoubleBlockFinder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class LootdropCommand implements CommandExecutor {
    static Player p;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        p = (Player) sender;
        World w = Bukkit.getWorld("world");
        Location loc = p.getLocation();
        Material material = Material.RED_SAND;
        BlockData data = Bukkit.createBlockData(material);
        loc.setY(150);
        if(args.length == 0) {
            p.sendMessage("§cNutze: §7/lootdrop spawn");
        } else if(args.length == 1) {
            if (args[0].equals("spawn")) {
                p.sendMessage("§aEntity spawned...");
                p.sendMessage("§cDieser Command ist nur für Testzwecke gedacht und sollte auf keinen Fall während des Events genutzt werden!");
                LootdropFunction.drop(loc);

            } else {
                p.sendMessage("§cNutze: §7/lootdrop spawn");
            }
        }
        else if (args.length > 1) {
            p.sendMessage("§cNutze: §7/lootdrop spawn");
        }
        return true;
    }
}
