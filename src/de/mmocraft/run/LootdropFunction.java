package de.mmocraft.run;

import de.mmocraft.main.BattleRoyale;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class LootdropFunction {
    static World w = Bukkit.getWorld("world");
    static Player p = Bukkit.getPlayer("NotJan");
    static Material material = Material.RED_SAND;
    static BlockData data = Bukkit.createBlockData(material);
    static Entity e;
    static ArmorStand as;

    public static void drop(Location loc) {
        as = (ArmorStand) w.spawnEntity(loc, EntityType.ARMOR_STAND);
        e = w.spawnFallingBlock(loc, data);
        as.setInvulnerable(true);
        as.setSmall(true);
        as.setArms(true);
        as.setVisible(false);
        as.addPassenger(e);
        Bukkit.broadcastMessage("§6§lLOOTDROP! §aIrgendwo auf der Karte wurde ein §6Lootdrop §aabgeworfen! Halte Ausschau nach Hinweisen!");
        final Vector velocity = e.getVelocity();
        new BukkitRunnable() {
            @Override
            public void run() {
                //Setup Lootdrop
                velocity.setY(-0.08);
                as.setVelocity(velocity);
                e.setVelocity(velocity);
                e.setCustomNameVisible(true);
                e.setCustomName("§6§lLootdrop");
                e.setGlowing(true);
                e.setTicksLived(1);
                as.setTicksLived(1);
                //Initialize Ground
                if (as.isOnGround()) {
                    e.setGravity(false);
                    e.setGlowing(true);
                    e.setCustomName("§6§l§ke §6§lLootdrop §6§l§ke");
                    e.setCustomNameVisible(true);
                    e.setInvulnerable(true);
                    e.setPersistent(true);
                    Bukkit.broadcastMessage("§6§lLOOTDROP! §aKoordinaten: §e" + Float.parseFloat(String.valueOf(as.getLocation().getX()))
                            + "§7, §e" + Float.parseFloat(String.valueOf(as.getLocation().getY())) + "§7, §e" + Float.parseFloat(String.valueOf(as.getLocation().getZ())));
                    //Antidespawn of new Entity
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            as.setTicksLived(1);
                            e.setTicksLived(1);
                            rotateEntity();
                            if(e.isDead()) {
                                as.remove();
                                cancel();
                            }
                        }
                    }.runTaskTimer(BattleRoyale.getInstance(), 20L, 60L);
                    cancel();
                }
            }
        }.runTaskTimer(BattleRoyale.getInstance(), 0, 3L);
    }


    public static void rotateEntity() {
        new BukkitRunnable() {
            @Override
            public void run() {
                //Coming soon!
            }
        }.runTaskTimer(BattleRoyale.getInstance(), 0L, 3L);
    }
}
