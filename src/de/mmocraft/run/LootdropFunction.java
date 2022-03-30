package de.mmocraft.run;

import de.mmocraft.main.BattleRoyale;
import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.Timer;
import java.util.TimerTask;

public class LootdropFunction {
    static World w = Bukkit.getWorld("world");
    static ArmorStand as;
    static ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
    static SkullMeta meta = (SkullMeta) skull.getItemMeta();
    public static int time = 300;

    public static void drop(Location loc) {
        as = (ArmorStand) w.spawnEntity(loc, EntityType.ARMOR_STAND);
        as.setInvulnerable(true);
        as.setSmall(true);
        as.setArms(true);
        as.setVisible(false);
        meta.setOwner("BeanshapedSinRam");
        meta.setDisplayName("§6Lootdrop Kiste");
        skull.setItemMeta(meta);
        as.setHelmet(skull);
        rotateEntity(as);
        Location part1 = as.getLocation();
        part1.setY(0);
        Location part2 = as.getLocation();
        part2.setY(256);
        new BukkitRunnable() {
        @Override
            public void run() {
                ParticleAPI.drawLine(part1, part2, 0.3);
                if(as.isDead()) {
                    cancel();
                }
            }
        }.runTaskTimer(BattleRoyale.getInstance(), 0L, 10L);
        Bukkit.broadcastMessage("§6§lLOOTDROP! §aIrgendwo auf der Karte wurde ein §6Lootdrop §aabgeworfen! Halte Ausschau nach Hinweisen!");

        final Vector velocity = as.getVelocity();

        new BukkitRunnable() {
            @Override
            public void run() {

                //Setup Lootdrop
                velocity.setY(-0.05);
                as.setVelocity(velocity);
                as.setCustomNameVisible(true);
                as.setCustomName("§6§lLootdrop");
                as.setTicksLived(1);

                //Initialize Ground
                if (as.isOnGround()) {
                    as.setGravity(false);
                    as.setCustomName("§6§l§ke §6§lLootdrop §6§l§ke");
                    as.setCustomNameVisible(true);
                    as.setInvulnerable(true);
                    as.setPersistent(true);
                    startCountdown();
                    LootdropOpener.open = false;
                    Bukkit.broadcastMessage("§6§lLOOTDROP! §aKoordinaten: §e" + Float.parseFloat(String.valueOf(as.getLocation().getX()))
                    + "§7, §e" + Float.parseFloat(String.valueOf(as.getLocation().getY())) + "§7, §e" + Float.parseFloat(String.valueOf(as.getLocation().getZ())));

                    //Antidespawn of new Entity
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            as.setTicksLived(1);
                            if(as.isDead()) {
                                Bukkit.broadcastMessage("§cDer §6Lootdrop §cist im Boden versunken...");
                                cancel();
                            }
                        }
                    }.runTaskTimer(BattleRoyale.getInstance(), 20L, 60L);


                    cancel();
                }
            }
        }.runTaskTimer(BattleRoyale.getInstance(), 0, 3L);
    }


    public static void rotateEntity(ArmorStand e) {
        new BukkitRunnable() {
            double v = 0.02;
            double v1 = 0.02;
            @Override
            public void run() {
                Timer t = new Timer();
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        v += v1;
                    }
                }, 1);
                e.setHeadPose(new EulerAngle(0, v, 0));

                if(e.isDead()) {
                    cancel();
                }
            }
        }.runTaskTimer(BattleRoyale.getInstance(), 0L, 1L);
    }
    public static void startCountdown() {
        new BukkitRunnable() {

            @Override
            public void run() {
                if(as.isDead()) {
                    cancel();
                }
                if(time == 0) {
                    Bukkit.broadcastMessage("§aDer §6Lootdrop §abei §e" + Float.parseFloat(String.valueOf(as.getLocation().getX()))
                    + "§7, §e" + Float.parseFloat(String.valueOf(as.getLocation().getY())) + "§7, §e" + Float.parseFloat(String.valueOf(as.getLocation().getZ())) + " §akann nun geöffnet werden!");
                    LootdropOpener.open = true;
                    cancel();
                }
                    time--;
            }
        }.runTaskTimer(BattleRoyale.getInstance(), 0L, 20L);
    }
}