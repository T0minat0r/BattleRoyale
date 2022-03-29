package de.mmocraft.commands;

import de.mmocraft.main.BattleRoyale;
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

public class LootdropCommand2 implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        World w = Bukkit.getWorld("world");
        Location loc = p.getLocation();
        Material material = Material.RED_SAND;
        BlockData data = Bukkit.createBlockData(material);
        loc.setY(300);
        if(args.length == 0) {
            p.sendMessage("§cNutze: §7/lootdrop spawn");
        } else if(args.length == 1) {
            if (args[0].equals("spawn")) {
                p.sendMessage("§aEntity spawned...");
                p.sendMessage("§cDieser Command ist nur für Testzwecke gedacht und sollte auf keinen Fall während des Events genutzt werden!");
                //Bukkit.broadcastMessage("§6§lLOOTDROP! §aIrgendwo auf der Karte wurde ein Lootdrop abgeworfen! Halte Ausschau nach Hinweisen!");
                Entity e = w.spawnFallingBlock(loc, data);
                final ArmorStand as = (ArmorStand) w.spawnEntity(loc, EntityType.ARMOR_STAND);
                as.setInvulnerable(true);
                as.setSmall(true);
                as.setVisible(false);
                as.addPassenger(e);
                final Vector velocity = e.getVelocity();
                new BukkitRunnable() {
                    @Override
                    public void run(){
                        velocity.setY(-0.08);
                        as.setVelocity(velocity);
                        e.setVelocity(velocity);
                        e.setGlowing(true);
                        e.setTicksLived(1);
                        as.setTicksLived(1);
                        //Debug
                        // p.sendMessage(e.getVelocity().toString());
                        if(as.isOnGround()) {
                            e.remove();
                            Entity e2 = w.spawnFallingBlock(as.getLocation(), data);
                            e2.setGravity(false);
                            e2.setGlowing(true);
                            e2.setCustomName("§6§l§ke §6§lLootdrop §6§l§ke");
                            e2.setCustomNameVisible(true);
                            e2.setInvulnerable(true);
                            e2.setPersistent(true);
                            Bukkit.broadcastMessage("§6§lLOOTDROP! §aKoordinaten: §e" + Float.parseFloat(String.valueOf(as.getLocation().getX()))
                            + "§7, §e" + Float.parseFloat(String.valueOf(as.getLocation().getY())) + "§7, §e" + Float.parseFloat(String.valueOf(as.getLocation().getZ())));
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    e2.setTicksLived(1);
                                    if(e2.isDead()) {
                                        cancel();
                                    }
                                }
                            }.runTaskTimer(BattleRoyale.getInstance(), 20L, 60L);
                            as.remove();
                            cancel();
                        }
                    }
                }.runTaskTimer(BattleRoyale.getInstance(), 0, 3L);

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
